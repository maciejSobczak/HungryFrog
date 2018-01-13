package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.Iterator;

public class GameScreen implements Screen {

    final MyGdxGame game;

    SpriteBatch batch;
    Texture backgroundImg;
    Texture frogImg;
    Texture bushImg;
    Rectangle frog;
    Array<Bush> bushes;
    String[][] bugs;
    Array<Bug> visibleBugs;
    long lastBugTime;
    long lastBushTime;
    int eatenBugs = 0;
    int bushesPassed = 0;
    int coordinatesCounter = 0;
    String[] coordinates;

//    FileHandle path = Gdx.files.internal("path.txt");

    public GameScreen(final MyGdxGame game) {
        this.game = game;
//        path.writeString("", false);
        //====================
//        String s = path.readString();
//        coordinates = s.split(",");
//        game.font.draw(game.batch, s, 50, 100);
        //=====================
        backgroundImg = new Texture(Gdx.files.internal("grassland.jpg"));
        frogImg = new Texture(Gdx.files.internal("frog.png"));
        bugs = new String[][] {
                {"butterfly", "beatiful, little peace of shit that lives only two days and then die", "butterfly.png", "72", "74"},
                {"fly", "this annoying thing eats animal shit and then sits on your dinner plate so you can try some too", "fly.png", "93", "80"}
        };
        batch = new SpriteBatch();
        frog = new Rectangle();
        frog.x = Gdx.graphics.getWidth()/2 - 91/2;
        frog.y = 20;
        frog.width = 200;
        frog.height = 132;
        visibleBugs = new Array<Bug>();
        bushes = new Array<Bush>();
//        bushImg = new Texture(Gdx.files.internal("bush1.png"));  // użyte w klasie Bush
        spawnBugs();
    }

    public void spawnBugs () {
        String[] bugParams = bugs[MathUtils.random(0, bugs.length - 1)];
        Bug bug = new Bug(bugParams);
        bug.x = MathUtils.random(0, Gdx.graphics.getWidth());
        bug.y = Gdx.graphics.getHeight();
        visibleBugs.add(bug);
        lastBugTime = TimeUtils.nanoTime();
    }

    public void spawnBushes () { // replace random with coordinates from file
        bushes.add(new Bush());
        lastBushTime = TimeUtils.nanoTime();
        /*if(bushesPassed < 15) {
            trackPath();
            bushes.add(new Rectangle(MathUtils.random(0, Gdx.graphics.getWidth()), Gdx.graphics.getHeight(), 500, 283));
            lastBushTime = TimeUtils.nanoTime();
        }
//        else {
//            game.setScreen(new MainMenuScreen(game));
            int horizontal;
            do {
                horizontal = MathUtils.random(0, Gdx.graphics.getWidth()); // koordynaty powinny być typu float albo double bo to ułamki
            } while (horizontal > Integer.parseInt(coordinates[coordinatesCounter]) && horizontal < Integer.parseInt(coordinates[coordinatesCounter]) + 100);
    //        Iterator<String> iter = coordinates.iterator();
            bushes.add(new Rectangle(horizontal, Gdx.graphics.getHeight(), 500, 283));
            lastBushTime = TimeUtils.nanoTime();
            if(coordinatesCounter < coordinates.length) {
                coordinatesCounter++;
            }
//        }
        bushesPassed++;*/
    }

    /*public void trackPath () {
        String frogPos = frog.x/Gdx.graphics.getWidth() + ",\n";
        path.writeString(frogPos, true);
    }*/

    public void updateScene() {
        if(Gdx.input.isTouched()) {
            frog.x = Gdx.input.getX() - frog.width/2;
            frog.y = Gdx.graphics.getHeight() - Gdx.input.getY() - frog.height/2;
        }

        /*if(bushesPassed == 15) {
            String s = path.readString();
            coordinates = s.split(",");
            game.font.draw(game.batch, s, 50, 100);
        }*/
        if(TimeUtils.nanoTime() - lastBushTime > 200000000) spawnBushes();
        if(TimeUtils.nanoTime() - lastBugTime > 900000000) spawnBugs();

        Iterator<Bug> iter = visibleBugs.iterator();
        while(iter.hasNext()) {
            Bug bug = iter.next();
            bug.y -= 400 * Gdx.graphics.getDeltaTime();
            if(bug.y + bug.height < 0) iter.remove();
            if(bug.overlaps(frog)) {
                eatenBugs++;
                iter.remove();
            }
        }
        Iterator<Bush> bushIter = bushes.iterator();
        while(bushIter.hasNext()) {
            Rectangle bush = bushIter.next();
            bush.y -= 350 * Gdx.graphics.getDeltaTime();
//            if(bush.y + bush.height < 0) bushIter.remove();  // nie czyta bush.height  czemu???
            if(bush.y + 500 < 0) bushIter.remove();
        }
    }

    public void renderScene() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);	//  R,G,B,Y
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(backgroundImg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.font.draw(game.batch, "Bugs eaten: " + eatenBugs, 100, Gdx.graphics.getHeight() - 10);
        game.batch.draw(frogImg, frog.x, frog.y);
        for(Bug bug: visibleBugs) {
            game.batch.draw(bug.image, bug.x, bug.y);
        }
        for(Bush bush: bushes) {
            game.batch.draw(bush.image, bush.x, bush.y);
        }
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
        frogImg.dispose();
        for(Bug bug: visibleBugs) bug.image.dispose();
        bushImg.dispose();
    }
}

class Bug extends Rectangle {
    public String type;
    public String description;
    public Texture image;
    public Integer width;
    public Integer height;

    public Bug (String[] bugArgs) {
        this.type = bugArgs[0];
        this.description = bugArgs[1];
        this.image = new Texture(Gdx.files.internal(bugArgs[2]));
        this.width = Integer.parseInt(bugArgs[3]);
        this.height = Integer.parseInt(bugArgs[4]);
    }

    //INNY WARIANT KONSTRUKTORA
    /*public Bug (String type, String info, String img_file, String width, String height) {
        this.type = type;
        this.description = info;
        this.image = new Texture(Gdx.files.internal(img_file));
        this.width = Integer.parseInt(width);
        this.height = Integer.parseInt(height);
    }*/
}

class Bush extends Rectangle {
    public Texture image = new Texture(Gdx.files.internal("bush1.png"));
    public Integer width = 500;
    public Integer height = 283;

    public Bush () {
        this.x = MathUtils.random(0, Gdx.graphics.getWidth());
        this.y = Gdx.graphics.getHeight();
        //        bushes.add(new Rectangle(MathUtils.random(0, Gdx.graphics.getWidth()), Gdx.graphics.getHeight(), 500, 283));
    }
}
