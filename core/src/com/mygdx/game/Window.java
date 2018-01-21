package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;

import javax.swing.text.html.ImageView;

/**
 * Created by msobczak on 14.01.2018.
 */

public class Window extends Dialog {

    private Stage stage;
    private Texture bugImage;

    public Window(String title, Skin skin, Stage stage) {
        super(title, skin);
        this.stage = stage;
//        this.button("Option 1", 1L);
        this.setScale(2f);

//        bug.setX(new Float(0.5 * Gdx.graphics.getWidth() + 400 - bug.getWidth()));
//        bug.setY(new Float(0.5 * Gdx.graphics.getHeight() + 300 - bug.getHeight()));
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
