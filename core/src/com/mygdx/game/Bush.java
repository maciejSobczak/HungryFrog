package com.mygdx.game;

/**
 * specific bush object class extending general actor class
 * set basic parameters
 * Created by msobczak on 14.01.2018.
 */

public class Bush extends BasicActor {
    public static final String IMAGE_PATH = "bush1.png";
    public static final Float WIDTH = 500f;
    public static final Float HEIGHT = 283f;

    public Bush(Float posX, Float posY) {
        super(IMAGE_PATH, WIDTH, HEIGHT, posX, posY);
    }
}
