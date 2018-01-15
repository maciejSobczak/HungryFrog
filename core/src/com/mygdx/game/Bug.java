package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by msobczak on 14.01.2018.
 */

public class Bug extends BasicActor {
    public String bugSpecies;
    public String description;

    public Bug (String bugSpecies, String info, String img_file, Float width, Float height, Float posX, Float posY) {
        super(img_file, width, height, posX, posY);
        this.bugSpecies = bugSpecies;
        this.description = info;
    }

    public Bug (BugSpecies species, Float posX, Float posY) {
        super(species.filePath, species.width, species.height, posX, posY);
        this.bugSpecies = species.species;
        this.description = species.description;
    }
}
