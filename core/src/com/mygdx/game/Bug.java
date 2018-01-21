package com.mygdx.game;

/**
 * specific bug object class extending general actor class
 * set basic parameters mostly from enum data
 * Created by msobczak on 14.01.2018.
 */

public class Bug extends BasicActor {
    public String bugSpecies;
    public String description;
    public boolean dangerous;

    public Bug (BugSpecies species, Float posX, Float posY) {
        super(species.filePath, species.width, species.height, posX, posY);
        this.bugSpecies = species.species;
        this.description = species.description;
        this.dangerous = species.dangerous;
    }
}
