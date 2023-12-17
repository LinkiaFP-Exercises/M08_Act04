package com.faunog.m08_act04;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Player {

    private float x;
    private final float y;
    private final int size;
    private Bitmap bitmap;
    private final GameLogic gameLogic;

    public Player(GameLogic gameLogic, Resources resources) {
        this.gameLogic = gameLogic;
        size = gameLogic.getScreenWidth() / 10;
        x = (gameLogic.getScreenWidth() - size) / 2f;
        y = gameLogic.getScreenHeight() - size;

        bitmap = BitmapFactory.decodeResource(resources, R.drawable.space_ship);
        bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, size, size);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        float halfSize = size / 2f;
        this.x = Math.max(halfSize, Math.min(x, gameLogic.getScreenWidth() - halfSize)) - halfSize;
    }

    public float getY() {
        return y;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getSize() {
        return size;
    }
}
