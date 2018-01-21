package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * general parent class for bushes bugs and character
 * Created by msobczak on 14.01.2018.
 */

public class BasicActor extends Actor {

    Texture image;
    Rectangle collisionBox;

    /**
     * constructor
     * set basic parameters for actor
     * @param img_file
     * @param width
     * @param height
     * @param posX
     * @param posY
     */
    public BasicActor(String img_file, Float width, Float height, Float posX, Float posY) {
            this.image = new Texture(Gdx.files.internal(img_file));
            this.setBounds(posX, posY, width, height);
            collisionBox = new Rectangle(posX, posY, width, height);
    }

    /**
     * override drawing method for this specific usage
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.image, this.getX(), this.getY());
    }

    /**
     * check for collision between two objects
     * @param other
     * @return
     */
    public boolean collidesWith(BasicActor other) {
        return this.collisionBox.overlaps(other.collisionBox);
    }

    /**
     * set parameters to check collision
     */
    protected void updateCollisionBox() {
        this.collisionBox.x = this.getX();
        this.collisionBox.y = this.getY();
    }

    @Override
    public void act(float delta) {}
}
