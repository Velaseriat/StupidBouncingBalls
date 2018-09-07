package com.avanti.sbb.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Kelly Masuda on 9/4/2018.
 */

public class CircleThings {


    private Color color;
    private Vector2 pos, pos1, pos2;
    private boolean dir;
    private Texture circleTexture;


    public CircleThings(float x, float y, Color color, float range, boolean orientation) {
        pos = new Vector2(x, y);
        pos1 = pos.cpy();
        pos2 = pos.cpy();

        this.color = color;

        if (orientation)
            pos2.x = pos2.x + range;
        else
            pos2.y = pos2.y + range;


        circleTexture = new Texture("circle.png");
    }

    public void updatePosition(float delta) {
        if (dir) {
            if (Vector2.dst(pos.x, pos.y, pos2.x, pos2.y) > 10f) {
                pos.lerp(pos2, delta * 4f);
            } else {
                dir = !dir;
            }
        } else {
            if (Vector2.dst(pos.x, pos.y, pos1.x, pos1.y) > 10f) {
                pos.lerp(pos1, delta * 4f);
            } else {
                dir = !dir;
            }
        }

    }

    public void render(Batch batch) {
        batch.setColor(color);
        batch.draw(circleTexture, pos.x, pos.y);
    }

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    public Color getColor(){
        return color;
    }
}
