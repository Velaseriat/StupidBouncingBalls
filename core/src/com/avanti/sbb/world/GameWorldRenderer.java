package com.avanti.sbb.world;

import com.avanti.sbb.objects.CircleThings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by Kelly Masuda on 9/4/2018.
 */

public class GameWorldRenderer {
    private GameWorld gameWorld;

    public GameWorldRenderer(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public void render(Batch batch) {

        batch.setColor(gameWorld.getBackgroundColor());
        batch.draw(gameWorld.getBackgroundImage(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.setColor(Color.WHITE);

        for (CircleThings circleThings : gameWorld.getCircleThingsList()) {
            circleThings.render(batch);
        }
    }
}
