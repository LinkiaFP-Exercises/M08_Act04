package com.faunog.m08_act04;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Player {

    private float x;
    private final float y;
    private final int size;
    private Bitmap playerBitmap;
    private final GameLogic gameLogic;

    public Player(int screenWidth, int screenHeight, Resources resources, GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        size = screenWidth / 10;
        x = (screenWidth - size) / 2f;
        y = screenHeight - size;

        playerBitmap = BitmapFactory.decodeResource(resources, R.drawable.space_ship);
        playerBitmap = Bitmap.createScaledBitmap(playerBitmap, size, size, true);
        playerBitmap = Bitmap.createBitmap(playerBitmap, 0, 0, size, size);

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

    public Bitmap getPlayerBitmap() {
        return playerBitmap;
    }

    public int getSize() {
        return size;
    }
}
