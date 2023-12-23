package com.faunog.m08_act04;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Clase que representa al jugador en el juego.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @version 1.0
 * @since 23/12/2023
 */
public class Player {

    private float x;
    private final float y;
    private final int size;
    private Bitmap bitmap;
    private final GameLogic gameLogic;

    /**
     * Constructor de la clase Player.
     *
     * @param gameLogic Instancia de GameLogic asociada al jugador.
     * @param resources Recursos para cargar la imagen del jugador.
     */
    public Player(GameLogic gameLogic, Resources resources) {
        this.gameLogic = gameLogic;
        size = gameLogic.getScreenWidth() / 10;
        x = (gameLogic.getScreenWidth() - size) / 2f;
        y = gameLogic.getScreenHeight() - size;

        bitmap = BitmapFactory.decodeResource(resources, R.drawable.space_ship);
        bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, size, size);
    }

    /**
     * Obtiene la posición X del jugador.
     *
     * @return Posición X del jugador.
     */
    public float getX() {
        return x;
    }

    /**
     * Establece la posición X del jugador, asegurándose de que esté dentro de los límites de la pantalla.
     *
     * @param x Nueva posición X del jugador.
     */
    public void setX(float x) {
        float halfSize = size / 2f;
        this.x = Math.max(halfSize, Math.min(x, gameLogic.getScreenWidth() - halfSize)) - halfSize;
    }

    /**
     * Obtiene la posición Y constante del jugador.
     *
     * @return Posición Y constante del jugador.
     */
    public float getY() {
        return y;
    }

    /**
     * Obtiene la imagen del jugador.
     *
     * @return Imagen del jugador.
     */
    public Bitmap getBitmap() {
        return bitmap;
    }

    /**
     * Obtiene el tamaño del jugador.
     *
     * @return Tamaño del jugador.
     */
    public int getSize() {
        return size;
    }
}
