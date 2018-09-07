package com.avanti.sbb.screens;

import com.avanti.sbb.StupidBouncingBalls;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Kelly Masuda on 9/4/2018.
 */

public class MenuScreen extends ButtonScreenAdapter {
    
    private TextureAtlas atlas;
    private Skin buttonSkin;
    private ButtonStyle style;


    public MenuScreen(StupidBouncingBalls gameInstance) {
        super(gameInstance);
    }

    @Override
    protected void initializeButtons() {
        initializePlayButton();
    }

    private void initializePlayButton() {
        atlas       = new TextureAtlas(Gdx.files.internal("playButton.atlas"));
        buttonSkin  = new Skin(atlas);
        style       = new ButtonStyle();
        style.up    = buttonSkin.getDrawable("buttonUnpressed");
        style.down  = buttonSkin.getDrawable("buttonPressed");

        Button startButton = new Button(style);

        //tell the button what to do when these two events occur
        startButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gameInstance.setScreen(new GameScreen(gameInstance));
                dispose();
            }
        });

        startButton.setX(Gdx.graphics.getWidth()/2 - startButton.getWidth()/2);
        startButton.setY(Gdx.graphics.getHeight()/2 - startButton.getHeight()/2);

        //add actors to the stage
        buttonStage.addActor(startButton);

        //very important! or else you wont get any input handling at all.
        inputMultiplexer.addProcessor(buttonStage);
    }


    @Override
    public void show() {
        initializeButtons();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        buttonStage.act(delta);
        buttonStage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
