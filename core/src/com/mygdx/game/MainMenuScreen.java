/**
 *  Welcome screen
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MainMenuScreen implements Screen {

    final MyGdxGame game;
    Texture backgroundImg;
    Texture frogImg;

    /**
     * constructor setting textures
     * @param game
     */
    public MainMenuScreen(final MyGdxGame game) {
        backgroundImg = new Texture(Gdx.files.internal("background.jpg"));
        frogImg = new Texture(Gdx.files.internal("frog.png"));
        this.game = game;
    }

    @Override
    public void show() {

    }

    /**
     * drawing all
     * @param data
     */
    @Override
    public void render(float data) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(backgroundImg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.draw(frogImg, Gdx.graphics.getWidth()/2, 500, 200, 132);
//        game.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.font.draw(game.batch, "Welcome to the Game!", 100, 400);
        game.font.draw(game.batch, "Tap anywhere to begin", 100, 300);
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
