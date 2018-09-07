package com.avanti.sbb.screens;

import com.avanti.sbb.StupidBouncingBalls;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Kelly Masuda on 9/4/2018.
 */

public class SplashScreen extends ButtonScreenAdapter {
    private Texture logo;
    private Sprite fadingSprite;

    private final float FADE_IN_TIME = 2;
    private float timeCounter;

    public SplashScreen(StupidBouncingBalls gameInstance) {
        super(gameInstance);
    }

    @Override
    protected void initializeButtons() {

    }

    @Override
    public void show() {
        logo = new Texture(Gdx.files.internal("logo.png"));
        fadingSprite = new Sprite(logo);
        fadingSprite.setBounds(Gdx.graphics.getWidth()/2 - logo.getWidth()/2, Gdx.graphics.getHeight()/2 - logo.getHeight()/2, logo.getWidth(), logo.getHeight());

        timeCounter = 0;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //grab a pen
        batch.begin();

        //fades into the logo over two seconds
        fadingSprite.draw(batch, timeCounter <= FADE_IN_TIME ? timeCounter/FADE_IN_TIME : 1);

        //add time slices
        timeCounter += delta;

        //after two more seconds, go to menu screen
        if (timeCounter > FADE_IN_TIME + 2) {
            gameInstance.setScreen(new MenuScreen(gameInstance));
            dispose();
        }

        //put the pen away
        batch.end();

        //if there are any buttons, do your job and then draw yourself
        buttonStage.act();
        buttonStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        //reset all the buttons and reinitialize them
        if (buttonStage == null)
            buttonStage = new Stage();
        buttonStage.clear();
        initializeButtons();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
