package com.faunog.m08_act04;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Obstacle {

    private float x, y; // Posición del obstáculo
    private int speed; // Velocidad del obstáculo
    private final int size;
    private final String level;
    private Bitmap bitmap;
    private final GameLogic gameLogic;

    public Obstacle(GameLogic gameLogic, Resources resources, String level) {
        this.gameLogic = gameLogic;
        size = gameLogic.getScreenWidth() / 20;
        x = getRandomXPosition();
        y = -size;
        speed = 5;
        this.level = level;
        setBitmap(resources);
    }

    private void setBitmap(Resources resources) {
        if (level.equalsIgnoreCase("Hard")) {
            int backgroundResource = new Random().nextBoolean()
                    ? R.drawable.enemy : R.drawable.obstaculo;
            bitmap = BitmapFactory.decodeResource(resources, backgroundResource);
        } else {
            bitmap = BitmapFactory.decodeResource(resources, R.drawable.enemy);
        }
        bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, size, size);
    }

    private float getRandomXPosition() {
        return (float) (Math.random() * (gameLogic.getScreenWidth() - size));
    }

    public boolean updateSendTrueIfRestart() {
        y += speed;

        if (y > gameLogic.getScreenHeight()) {
            x = getRandomXPosition();
            y = -size;
            speed += level.equalsIgnoreCase("Hard") ? 2 : 1;
            return true;
        }
        return false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
