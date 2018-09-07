package com.avanti.sbb.screens;

import com.avanti.sbb.StupidBouncingBalls;
import com.avanti.sbb.world.GameWorld;
import com.avanti.sbb.world.GameWorldRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by Kelly Masuda on 9/4/2018.
 */

public class GameScreen extends ButtonScreenAdapter implements InputProcessor {

    private GameWorld gameWorld;
    private GameWorldRenderer gameWorldRenderer;

    private BitmapFont scoreboardBitmapFont;


    public GameScreen(StupidBouncingBalls gameInstance) {
        super(gameInstance);

        //pass in game instance for settings, game level, etc
        gameWorld = new GameWorld(gameInstance);
        gameWorldRenderer = new GameWorldRenderer(gameWorld);

        scoreboardBitmapFont = new BitmapFont();
    }

    @Override
    protected void initializeButtons() {

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //grab a pen
        batch.begin();

        gameWorldRenderer.render(batch);
        gameWorld.updateGameLogic(delta);

        scoreboardBitmapFont.draw(batch, "Score: " + gameWorld.getScore(),100, 100);


        gameWorld.getCamera().update();
        batch.setProjectionMatrix(gameWorld.getCamera().combined);


        batch.end();
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //for dragging calculations
        gameWorld.setTouchDownPos(screenX, screenY);

        //to actually remove circle. separate task from setTouchDownPos
        gameWorld.handleRemoveCircle(screenX, screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //touchDownPos gives us point 1. this gives us point 2.
        gameWorld.handleMoveCamera(screenX, screenY);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
