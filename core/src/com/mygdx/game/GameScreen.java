/**
 * main screen where all game action happens
 */
package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.Iterator;

public class GameScreen implements Screen {

    final MyGdxGame game;
    Stage stage;

    SpriteBatch batch;
    Texture backgroundImg;
    Player frog;
    Array<Bush> bushes;
    BugSpecies[] bugTypes;
    Array<Bug> visibleBugs;
    long lastBugTime;
    long lastBushTime;
    int eatenBugs = 0;
    int healthPoints = 5;
    Boolean isRunning = true;
    Skin skin;
    Window window = null;
    Array<String> seenBugs;

    /**
     * constructor with all initial settings
     * @param game
     */
    public GameScreen(final MyGdxGame game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        backgroundImg = new Texture(Gdx.files.internal("background.jpg"));
        bugTypes = new BugSpecies[] {BugSpecies.FLY, BugSpecies.BUTTERFLY, BugSpecies.BLACK_WIDOW, BugSpecies.BUMBLEBEE, BugSpecies.WOLF_SPIDER};
        batch = new SpriteBatch();

        visibleBugs = new Array<Bug>();
        bushes = new Array<Bush>();
        seenBugs = new Array<String>();
        skin = generateSkin();
        createPlayer();
    }

    public Skin generateSkin() {
        Skin skin = new Skin(Gdx.files.internal("shadeui/uiskin.json"));
        return skin;
    }

    /**
     * create main caracter object with all features
     */
    public void createPlayer() {
        String frogImg = "frogOver.png";
        Float x = new Float(Gdx.graphics.getWidth()/2 - 91/2);
        Float y = 20f;
        Float width = 200f;
        Float height = 132f;
        frog = new Player(frogImg, width, height, x ,y);
        stage.addActor(frog);
    }

    /**
     * create multiple objects and store in array
     */
    public void spawnBugs() {
        Float x = new Float(MathUtils.random(0, Gdx.graphics.getWidth()));
        Float y = new Float(Gdx.graphics.getHeight());
        BugSpecies bugParams = bugTypes[MathUtils.random(0, bugTypes.length - 1)];
        Bug bug = new Bug(bugParams, x ,y);
        visibleBugs.add(bug);
        stage.addActor(bug);
        lastBugTime = TimeUtils.nanoTime();
    }

    /**
     * create multiple bush objects and store in array
     */
    public void spawnBushes () {
        Float x = new Float(MathUtils.random(0, Gdx.graphics.getWidth()));
        Float y = new Float(Gdx.graphics.getHeight());
        Bush bush = new Bush(x, y);
        bushes.add(bush);
        stage.addActor(bush);
        lastBushTime = TimeUtils.nanoTime();
    }

    /**
     * popup window with information about bug species
     * @param bug
     */
    public void generateWindow(Bug bug) {
        isRunning = false;
        window = new Window(bug.bugSpecies, skin, stage);
        Label text = new Label(bug.description, skin);
        Image bugImg = new Image(bug.image);
        window.getContentTable().add(bugImg);
        window.add(text);

        window.show(stage);
    }

    /**
     * update, stop and start action, also switch to another screen (game over)
     */
    public void updateScene() {
        if(isRunning) {
            updateActors();
            frog.toFront();
            if(healthPoints <= 0) {
                game.setScreen(new GameOver(game, eatenBugs));
            }
        }
        else {
            if(window != null && Gdx.input.isTouched()) {
                isRunning = true;
                window.remove();
            }
        }
    }

    /**
     * all movement and collisions handled for each object
     */
    public void updateActors() {
        if(Gdx.input.isTouched()) {
            frog.setX(Gdx.input.getX() - frog.getWidth()/2);
            frog.setY(Gdx.graphics.getHeight() - Gdx.input.getY() - frog.getHeight()/2);
            frog.updateCollisionBox();
        }

        if(TimeUtils.nanoTime() - lastBugTime > 500000000) spawnBugs();
        if(TimeUtils.nanoTime() - lastBushTime > 500000000) spawnBushes();

        Iterator<Bug> iter = visibleBugs.iterator();
        while(iter.hasNext()) {
            Bug bug = iter.next();
            bug.toBack();
            bug.setY(bug.getY() - 400 * Gdx.graphics.getDeltaTime());
            if(bug.getY() + bug.getHeight()*2 < 0) iter.remove();
            if(bug.collidesWith(frog)) {
                if(!seenBugs.contains(bug.bugSpecies, true)) {
                    generateWindow(bug);
                    seenBugs.add(bug.bugSpecies);
                } else if(bug.dangerous) {
                    healthPoints--;
                }
                eatenBugs++;
                iter.remove();
                bug.remove();
            }
            bug.updateCollisionBox();
        }
        Iterator<Bush> bushIter = bushes.iterator();
        while(bushIter.hasNext()) {
            Bush bush = bushIter.next();
            bush.setY(bush.getY() - 350 * Gdx.graphics.getDeltaTime());
            if(bush.getY() + bush.getHeight() < 0) bushIter.remove();
            bush.updateCollisionBox();
        }
    }

    /**
     * draw the game
     */
    public void renderScene() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);	//  R,G,B,Y
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(backgroundImg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();
        stage.draw();
        game.batch.begin();
        game.font.draw(game.batch, "Bugs eaten: " + eatenBugs, 100, Gdx.graphics.getHeight() - 10);
        game.font.draw(game.batch, "HP: " + healthPoints, Gdx.graphics.getWidth()-300, Gdx.graphics.getHeight() - 10);
        game.batch.end();
    }

    @Override
    public void show() {

    }

    public void render (float delta) {
        updateScene();
        renderScene();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose () {
        batch.dispose();
        backgroundImg.dispose();
        for(Bug bug: visibleBugs) bug.image.dispose();
        for(Bush bush: bushes) bush.image.dispose();
    }
}
