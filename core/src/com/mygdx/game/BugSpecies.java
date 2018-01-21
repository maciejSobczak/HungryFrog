package com.mygdx.game;

import com.badlogic.gdx.Screen;

/**
 * data about every species
 * Created by msobczak on 14.01.2018.
 */

public enum BugSpecies {

    BUTTERFLY("butterfly", "Beatiful, little creature\nthat lives only two days in such form.n\n Let it live", "butterfly.png", 72f, 74f, false),
    FLY("fly", "Is not dangerous\nbut can spread some disease\nif you lack a luck", "fly.png", 93f, 80f, false),
    BUMBLEBEE("bumblebee", "Even though their sting isn't lethal,\n it can cause some pain.\n Remember that they sting only in self defence!", "bumblebee.png", 93f, 80f, true),
    WOLF_SPIDER("wolf spider", "Even though the Wolf Spider is poisonous,\nits venom is not lethal.\nThis spider is not known to be aggressive;\nhowever, they will bite " +
            "\nif they feel like they are in harm or danger.\nThey also move extremely fast\nwhen they are disturbed", "wolfspider.png", 93f, 80f, true),
    BLACK_WIDOW("black widow", "15 times stronger venom than a rattlesnake's.\nIn humans, bites produce muscle aches, nausea,\nand a paralysis of the diaphragm." +
            "\nBites can be fatal to children od elderly", "black-widow-clipart-9.png", 100f, 80f, true);


    public String species;
    public String description;
    public String filePath;
    public Float width;
    public Float height;
    public boolean dangerous;

    BugSpecies(String species, String description, String filePath, Float width, Float height, boolean dangerous) {
        this.species = species;
        this.description = description;
        this.filePath = filePath;
        this.width = width;
        this.height = height;
        this.dangerous = dangerous;
    }
}
