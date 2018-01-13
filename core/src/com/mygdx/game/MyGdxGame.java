package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game { //Game abstract class provides an implementation of ApplicationListener class

    public SpriteBatch batch;
    public BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch(); // renders textures onto the screen
        font = new BitmapFont();    // renders text, and is used along with SpriteBatch
        font.getData().setScale(5f);
        // Setting the Screen of the Game to a MainMenuScreen object with a MyGdxGame instance at first and only parameter
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}