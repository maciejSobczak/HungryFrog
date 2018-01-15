package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by msobczak on 14.01.2018.
 */

public class BasicActor extends Actor {

    Texture image;
    Rectangle collisionBox;

    public BasicActor(String img_file, Float width, Float height, Float posX, Float posY) {
            this.image = new Texture(Gdx.files.internal(img_file));
            this.setBounds(posX, posY, width, height);
            collisionBox = new Rectangle(posX, posY, width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.image, this.getX(), this.getY());
    }

    public boolean collidesWith(BasicActor other) {
        return this.collisionBox.overlaps(other.collisionBox);
    }

    protected void updateCollisionBox() {
        this.collisionBox.x = this.getX();
        this.collisionBox.y = this.getY();
    }

    @Override
    public void act(float delta) {}
}
