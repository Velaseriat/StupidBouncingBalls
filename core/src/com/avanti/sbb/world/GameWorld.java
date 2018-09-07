package com.avanti.sbb.world;

import com.avanti.sbb.StupidBouncingBalls;
import com.avanti.sbb.objects.CircleThings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by Kelly Masuda on 9/4/2018.
 */

public class GameWorld {
    private StupidBouncingBalls gameInstance;
    private Array<CircleThings> circleThingsList;

    private int score;
    private final float TIME_CT_SPAWN = 3f;
    private float deltaTimeTotal;

    private Color color, colorTarget;
    private Texture backgroundImage;


    private OrthographicCamera cam;
    private Vector3 touchDownPos;

    private Random random;



    public GameWorld(StupidBouncingBalls gameInstance) {
        this.gameInstance = gameInstance;
        circleThingsList = new Array<CircleThings>();

        deltaTimeTotal = 0f;

        random = new Random();

        score = 0;

        backgroundImage = new Texture("background.jpg");
        color = Color.BLACK;
        changeColorTarget();

        cam = new OrthographicCamera(Gdx.graphics.getWidth() / 1.5f, Gdx.graphics.getHeight() / 1.5f);
        cam.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0f);
        cam.update();
    }

    private void spawnBouncingBall(int screenX, int screenY, Color color, float range, boolean orientation) {
        circleThingsList.add(new CircleThings((float) screenX, (float) screenY, color, range, orientation));
    }

    public void updateGameLogic(float delta) {

        for (CircleThings circleThings : circleThingsList)
            circleThings.updatePosition(delta);

        deltaTimeTotal += delta;
        if (deltaTimeTotal > TIME_CT_SPAWN) {
            deltaTimeTotal = deltaTimeTotal - TIME_CT_SPAWN;

            changeColorTarget();

            if (circleThingsList.size < 51) {
                Color color = new Color(random.nextFloat() % 1f, random.nextFloat() % 1f, random.nextFloat() % 1f, .75f);
                float range = (float) random.nextInt(800) - 400f;
                boolean orientation = random.nextBoolean();
                int x = random.nextInt(Gdx.graphics.getWidth() - 1);
                int y = random.nextInt(Gdx.graphics.getHeight() - 1);
                spawnBouncingBall(x, y, color, range, orientation);
            }
        }
        color.lerp(colorTarget, delta*2f);
    }

    private void changeColorTarget() {
        colorTarget = new Color(random.nextFloat() % 1f , random.nextFloat() % 1f, random.nextFloat() % 1f, .75f);
    }

    public Array<CircleThings> getCircleThingsList() {
        return circleThingsList;
    }

    public Texture getBackgroundImage() {
        return backgroundImage;
    }

    public void handleRemoveCircle(int screenX, int screenY) {
        //invert the screen horizontally
        //screenY = Gdx.graphics.getHeight() - screenY;

        Vector3 touchPos = getCamera().unproject(new Vector3(screenX, screenY, 0));


        for (CircleThings circleThing : getCircleThingsList()) {
            float x1 = circleThing.getX();
            float x2 = x1 + 50;
            float y1 = circleThing.getY();
            float y2 = y1 + 100;
            if (x1 < touchPos.x && touchPos.x < x2 && y1 < touchPos.y && touchPos.y < y2) {
                calculateScore(circleThing, getBackgroundColor());
                getCircleThingsList().removeValue(circleThing, true);
            }
        }
    }

    public void handleMoveCamera(int screenX, int screenY) {
        Vector3 dragPos = getCamera().unproject(new Vector3(screenX, screenY, 0));
        Vector3 diff = dragPos.cpy().sub(touchDownPos);

        cam.position.sub(diff);
    }

    private void calculateScore(CircleThings circleThing, Color backgroundColor) {
        score += 100 - Math.abs(circleThing.getColor().r - backgroundColor.r)*255;
        score += 100 - Math.abs(circleThing.getColor().g - backgroundColor.g)*255;
        score += 100 - Math.abs(circleThing.getColor().b - backgroundColor.b)*255;
    }

    public void setTouchDownPos(int screenX, int screenY) {
        touchDownPos = getCamera().unproject(new Vector3(screenX, screenY, 0));
    }

    public int getScore() {
        return score;
    }

    public Color getBackgroundColor() {
        return color;
    }

    public Camera getCamera() {
        return cam;
    }
}
