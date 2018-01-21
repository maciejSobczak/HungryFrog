package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by msobczak on 14.01.2018.
 */

public class Bush extends BasicActor {
    public static final String IMAGE_PATH = "bush1.png";
    public static final Float WIDTH = 500f;  // patrz float
    public static final Float HEIGHT = 283f;

    public Bush(Float posX, Float posY) {
        super(IMAGE_PATH, WIDTH, HEIGHT, posX, posY);
    }
}
