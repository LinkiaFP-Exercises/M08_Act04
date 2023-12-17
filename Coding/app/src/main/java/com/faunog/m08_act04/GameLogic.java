package com.faunog.m08_act04;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private final Player player;
    private final List<Obstacle> obstacles;
    private final int screenWidth, screenHeight;
    private final Resources resources;
    private final Context context;
    private long lastObstacleTime;
    private int score;

    public GameLogic(int screenWidth, int screenHeight, Resources resources, Context context) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.resources = resources;
        this.context = context;
        player = new Player(this, resources);
        obstacles = new ArrayList<>();
        score = 0;
    }

    public void update() {
        if (obstacles.size() < 20) {
            long currentTime = System.currentTimeMillis();

            long obstacleCreationInterval = 2000;
            if (currentTime - lastObstacleTime > obstacleCreationInterval) {
                obstacles.add(new Obstacle(this, resources));
                lastObstacleTime = currentTime;
            }
        }

        for (Obstacle obstacle : obstacles) {
            if (obstacle.updateSendTrueIfRestart()) {
                score++;
                Log.d("SCORE", String.valueOf(getScore()));
            }
        }

        checkCollisions();
    }

    private void checkCollisions() {
        for (Obstacle obstacle : getObstacles()) {
            if (isCollision(player, obstacle)) {
                handleCollision();
            }
        }
    }

    private boolean isCollision(Player player, Obstacle obstacle) {
        // Coordenadas del área de intersección
        int xStart = (int) Math.max(player.getX(), obstacle.getX());
        int xEnd = (int) Math.min(player.getX() + player.getSize(),
                obstacle.getX() + obstacle.getSize());
        int yStart = (int) Math.max(player.getY(), obstacle.getY());
        int yEnd = (int) Math.min(player.getY() + player.getSize(),
                obstacle.getY() + obstacle.getSize());

        // Verificar la colisión píxel por píxel en el área de intersección
        for (int x = xStart; x < xEnd; x++) {
            for (int y = yStart; y < yEnd; y++) {
                int playerPixel = getPixel(player.getBitmap(),
                        x - (int) player.getX(), y - (int) player.getY());
                int obstaclePixel = getPixel(obstacle.getBitmap(),
                        x - (int) obstacle.getX(), y - (int) obstacle.getY());

                // Si ambos píxeles no son transparentes, hay colisión
                if ((playerPixel >> 24) != 0 && (obstaclePixel >> 24) != 0) {
                    return true;
                }
            }
        }
        // No hay colisión encontrada
        return false;
    }

    private int getPixel(Bitmap bitmap, int x, int y) {
        if (x >= 0 && x < bitmap.getWidth() && y >= 0 && y < bitmap.getHeight()) {
            return bitmap.getPixel(x, y);
        } else {
            return 0; // Fuera de los límites de la imagen
        }
    }

    private void handleCollision() {
        Activity currentActivity = (Activity) context;
        Intent intent = new Intent(context, OverActivity.class);
        context.startActivity(intent);
        currentActivity.finish();
    }

    public void onTouchEvent(MotionEvent event) {
        if (player != null) {
            float touchX = event.getX();
            float touchY = event.getY();

            if (touchX >= player.getX() && touchX <= player.getX() + player.getSize()
                    && touchY >= player.getY() && touchY <= player.getY() + player.getSize()) {
                player.setX(event.getX());
            }
        }
    }

    public int getScore() {
        return score;
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

