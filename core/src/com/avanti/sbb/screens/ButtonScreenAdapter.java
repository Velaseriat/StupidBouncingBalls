package com.avanti.sbb.screens;

import com.avanti.sbb.StupidBouncingBalls;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Kelly Masuda on 9/4/2018.
 */

public abstract class ButtonScreenAdapter implements Screen {

    protected Stage buttonStage;
    protected StupidBouncingBalls gameInstance;
    protected InputMultiplexer inputMultiplexer;
    protected SpriteBatch batch;

    public ButtonScreenAdapter(StupidBouncingBalls gameInstance) {
        this.gameInstance   = gameInstance;
        buttonStage         = new Stage();
        inputMultiplexer    = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);

        batch = new SpriteBatch();
    }

    protected abstract void initializeButtons();

    @Override
    public void dispose() {
        buttonStage.dispose();
    }
}
