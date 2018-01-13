package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;

public class MainMenuScreen implements Screen {

    final MyGdxGame game;

    FileHandle bushPath = Gdx.files.internal("path.txt");
    String path = bushPath.readString();
    // implemented class Screen does not provide any 'create' method so we create a constructor
    // the parameter passed into the constructor is an instance of our MyGdxGame class so we can call upon its methods and fields
    public MainMenuScreen(final MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float data) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        if(path.isEmpty()) {
            game.font.draw(game.batch, "Welcome to the Game!", 300, 350);
            game.font.draw(game.batch, "Tap anywhere to begin", 300, 300);
        } else {
            game.font.draw(game.batch, path, 500,Gdx.graphics.getHeight()-10);
        }
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
