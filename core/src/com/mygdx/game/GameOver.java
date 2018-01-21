package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class GameOver implements Screen {

    final MyGdxGame game;
    Texture backgroundImg;
    Texture frogImg;
    int bugsEaten;
    // implemented class Screen does not provide any 'create' method so we create a constructor
    // the parameter passed into the constructor is an instance of our MyGdxGame class so we can call upon its methods and fields
    public GameOver(final MyGdxGame game, int bugsEaten) {
        backgroundImg = new Texture(Gdx.files.internal("background.jpg"));
        frogImg = new Texture(Gdx.files.internal("frog.png"));
        this.game = game;
        this.bugsEaten = bugsEaten;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float data) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(backgroundImg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.draw(frogImg, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 200, 132);
        game.font.draw(game.batch, "You just ate 5 poisonous bugs!", 300, 400);
        game.font.draw(game.batch, "Overall score: " + bugsEaten, 300, 300);
        game.font.draw(game.batch, "Tap anywhere to start over", 300, 100);
        game.batch.end();

        if(Gdx.input.isTouched()) {
            // Set the game screen and dispose the instance of menu screen
            game.setScreen(new GameScreen(game));
            dispose();
        }
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
    public void dispose() {

    }
}
