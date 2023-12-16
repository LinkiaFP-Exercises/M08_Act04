package com.faunog.m08_act04;

import android.content.res.Resources;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private Player player;
    private List<Obstacle> obstacles;
    private final int screenWidth, screenHeight;

    public GameLogic(int screenWidth, int screenHeight, Resources resources) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        player = new Player(screenWidth, screenHeight, resources, this);
        obstacles = new ArrayList<>();
    }

    // Método para actualizar la lógica del juego en cada fotograma
    public void update() {
        // Actualiza la posición y lógica de los obstáculos
        for (Obstacle obstacle : obstacles) {
            obstacle.update();
        }

        // Verifica colisiones entre el jugador y los obstáculos
        checkCollisions();
    }

    private void checkCollisions() {
    }

    // Métodos para controlar las interacciones del jugador (por ejemplo, toques en la pantalla)
    public void onTouchEvent(MotionEvent event) {
        if (player != null) {
            float touchX = event.getX();
            float touchY = event.getY();

            // Verificar si el toque está dentro de la zona de la nave
            if (touchX >= player.getX() && touchX <= player.getX() + player.getSize()
                    && touchY >= player.getY() && touchY <= player.getY() + player.getSize()) {
                // El toque ocurrió dentro de la nave, puedes permitir el movimiento
                player.setX(event.getX());
            }
        }
    }

    // Métodos para obtener información sobre el estado del juego (puntuación, nivel, etc.)
    public int getScore() {
        // Implementa la lógica para calcular y devolver la puntuación del jugador
        return 0;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}

