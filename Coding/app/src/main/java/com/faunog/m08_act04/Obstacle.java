package com.faunog.m08_act04;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Obstacle {

    private float x, y; // Posición del obstáculo
    private int speed; // Velocidad del obstáculo
    private final int size;
    private Bitmap bitmap;
    private final GameLogic gameLogic;

    public Obstacle(GameLogic gameLogic, Resources resources) {
        this.gameLogic = gameLogic;
        size = gameLogic.getScreenWidth() / 20;
        x = getRandomXPosition();
        y = -size;
        speed = 5;

        bitmap = BitmapFactory.decodeResource(resources, R.drawable.enemy);
        bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, size, size);
    }

    private float getRandomXPosition() {
        return (float) (Math.random() * (gameLogic.getScreenWidth() - size));
    }

    public boolean updateSendTrueIfRestart() {
        // Actualizar la posición en función de la velocidad
        y += speed;

        // Verificar si el obstáculo ha cruzado la pantalla
        if (y > gameLogic.getScreenHeight()) {
            // Reiniciar en la parte superior y aumentar la velocidad
            x = getRandomXPosition();
            y = -size;
            speed += 1; // Puedes ajustar la cantidad en la que aumenta la velocidad
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
