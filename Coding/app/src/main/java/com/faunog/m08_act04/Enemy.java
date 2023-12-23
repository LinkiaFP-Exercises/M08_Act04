package com.faunog.m08_act04;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase que representa a un enemigo en el juego.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @version 1.0
 * @since 23/12/2023
 */
public class Enemy {

    private float x, y;
    private int speed;
    private final int size;
    private final String level;
    private Bitmap bitmap;
    private final GameLogic gameLogic;

    /**
     * Constructor de la clase Enemy.
     *
     * @param gameLogic  Instancia de GameLogic asociada al enemigo.
     * @param resources  Recursos para cargar la imagen del enemigo.
     * @param level      Nivel de dificultad del juego ("Easy" o "Hard").
     */
    public Enemy(GameLogic gameLogic, Resources resources, String level) {
        this.gameLogic = gameLogic;
        size = gameLogic.getScreenWidth() / 20;
        x = getRandomXPosition();
        y = -size;
        speed = 5;
        this.level = level;
        setBitmap(resources);
    }

    /**
     * Configura la imagen del enemigo en base al nivel de dificultad.
     *
     * @param resources Recursos para cargar la imagen del enemigo.
     */
    private void setBitmap(Resources resources) {
        int backgroundResource = (level.equalsIgnoreCase("Hard")
                                    && ThreadLocalRandom.current().nextBoolean())
                                        ? R.drawable.obstaculo : R.drawable.enemy;
        bitmap = BitmapFactory.decodeResource(resources, backgroundResource);
        bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, size, size);
    }

    /**
     * Genera una posición X aleatoria para el enemigo.
     *
     * @return Posición X aleatoria.
     */
    private float getRandomXPosition() {
        return (float) (ThreadLocalRandom.current().nextDouble() * (gameLogic.getScreenWidth() - size));
    }

    /**
     * Actualiza la posición del enemigo y comprueba si debe reiniciarse.
     *
     * @return Verdadero si el enemigo debe reiniciarse, falso en caso contrario.
     */
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

    /**
     * Obtiene la posición X del enemigo.
     *
     * @return Posición X del enemigo.
     */
    public float getX() {
        return x;
    }

    /**
     * Obtiene la posición Y del enemigo.
     *
     * @return Posición Y del enemigo.
     */
    public float getY() {
        return y;
    }

    /**
     * Obtiene el tamaño del enemigo.
     *
     * @return Tamaño del enemigo.
     */
    public int getSize() {
        return size;
    }

    /**
     * Obtiene la imagen del enemigo.
     *
     * @return Imagen del enemigo.
     */
    public Bitmap getBitmap() {
        return bitmap;
    }
}
