package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by msobczak on 14.01.2018.
 */

public class Window extends Dialog {

    private Stage stage;

    public Window(String title, Skin skin, String windowStyleName, Stage stage) {
        super(title, skin, windowStyleName);
        this.stage = stage;
        this.button("Option 1", 1L);
        this.button("Option 2", 2L);
    }

    @Override
    protected void result(Object object) {
        System.out.println("Option: " + object);
        Timer.schedule(new Timer.Task()
        {

            @Override
            public void run()
            {
                Window.this.show(stage);    // skierowanie na window
            }
        }, 1);
    }
}
