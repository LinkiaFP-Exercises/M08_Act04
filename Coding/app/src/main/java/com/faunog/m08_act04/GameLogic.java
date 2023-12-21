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
    private final List<Enemy> enemies;
    private final int screenWidth, screenHeight;
    private final Resources resources;
    private final Context context;
    private long lastObstacleTime;
    private int score;
    private final String playerName, level;

    public GameLogic(int screenWidth, int screenHeight, Resources resources, Context context) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.resources = resources;
        this.context = context;
        player = new Player(this, resources);
        this.playerName = ((GameActivity) context).getPlayerNameTextView().getText().toString();
        this.level = ((GameActivity) context).getLevelTextView().getText().toString();
        enemies = new ArrayList<>();
        score = 0;
    }

    public void update() {
        int numberOfObstacles = level.equalsIgnoreCase("Hard") ? 30 : 15;
        if (enemies.size() < numberOfObstacles) {
            long currentTime = System.currentTimeMillis();

            long obstacleCreationInterval = level.equalsIgnoreCase("Hard") ? 1500 : 2000;
            if (currentTime - lastObstacleTime > obstacleCreationInterval) {
                enemies.add(new Enemy(this, resources, level));
                lastObstacleTime = currentTime;
            }
        }

        for (Enemy enemy : enemies) {
            if (enemy.updateSendTrueIfRestart()) {
                increaseScore();
            }
        }

        checkCollisions();
    }

    private void increaseScore() {
        score++;
        ((GameActivity) context).updateScoreTextViewOnUiThread(score);
    }

    private void checkCollisions() {
        for (Enemy enemy : getEnemies()) {
            if (isCollision(player, enemy)) {
                handleCollision();
            }
        }
    }

    private boolean isCollision(Player player, Enemy enemy) {
        // Porcentaje de reducción (ajustar según sea necesario)
        float reductionPercentage = 0.15f;

        // Calcular el margen basado en el tamaño de la imagen y el porcentaje de reducción
        float reductionX = enemy.getSize() * reductionPercentage;
        float reductionY = enemy.getSize() * reductionPercentage;

        // Coordenadas del área de intersección con margen reducido
        int xStart = (int) Math.max(player.getX(), enemy.getX() + reductionX);
        int xEnd = (int) Math.min(player.getX() + player.getSize(),
                enemy.getX() + enemy.getSize() - reductionX);
        int yStart = (int) Math.max(player.getY(), enemy.getY() + reductionY);
        int yEnd = (int) Math.min(player.getY() + player.getSize(),
                enemy.getY() + enemy.getSize() - reductionY);

        // Verificar la colisión píxel por píxel en el área de intersección
        for (int x = xStart; x < xEnd; x++) {
            for (int y = yStart; y < yEnd; y++) {
                int playerPixel = getPixel(player.getBitmap(),
                        x - (int) player.getX(), y - (int) player.getY());
                int obstaclePixel = getPixel(enemy.getBitmap(),
                        x - (int) enemy.getX(), y - (int) enemy.getY());

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
        intent.putExtra("PLAYER_NAME", playerName);
        intent.putExtra("SCORE", score);
        context.startActivity(intent);
        currentActivity.finish();
    }

    public void onPlayerTouchEvent(MotionEvent event) {
        if (player != null) {
            float touchX = event.getX();
            float touchY = event.getY();

            if (touchX >= player.getX() && touchX <= player.getX() + player.getSize()
                    && touchY >= player.getY() && touchY <= player.getY() + player.getSize()) {
                player.setX(event.getX());
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}

