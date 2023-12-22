package com.faunog.m08_act04;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Enemy {

    private float x, y;
    private int speed;
    private final int size;
    private final String level;
    private Bitmap bitmap;
    private final GameLogic gameLogic;

    public Enemy(GameLogic gameLogic, Resources resources, String level) {
        this.gameLogic = gameLogic;
        size = gameLogic.getScreenWidth() / 20;
        x = getRandomXPosition();
        y = -size;
        speed = 5;
        this.level = level;
        setBitmap(resources);
    }

    private void setBitmap(Resources resources) {
        int backgroundResource = (level.equalsIgnoreCase("Hard")
                && ThreadLocalRandom.current().nextBoolean())
                ? R.drawable.obstaculo : R.drawable.enemy;
        bitmap = BitmapFactory.decodeResource(resources, backgroundResource);
        bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, size, size);
    }

    private float getRandomXPosition() {
        return (float) (ThreadLocalRandom.current().nextDouble() * (gameLogic.getScreenWidth() - size));
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
