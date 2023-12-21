package com.faunog.m08_act04;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    private final SurfaceHolder surfaceHolder;
    private Thread gameThread;
    private boolean isPlaying;
    private GameLogic gameLogic;
    private Bitmap backgroundBitmap;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        surfaceHolder = getHolder();
    }

    @Override
    public void run() {
        control(); // Espera que el surface esté creado
        startGameLogic();
        while (isPlaying) {
            update();  // Actualiza la lógica del juego
            draw();    // Dibuja en el lienzo
            control(); // Controla la velocidad de actualización
        }
    }

    private void startGameLogic() {
        gameLogic = new GameLogic(getWidth(), getHeight(), getResources(), getContext());
        initializeBackground();
    }

    private void initializeBackground() {
        String level = ((GameActivity) getContext()).getLevelTextView().getText().toString();
        int backgroundResource = level.equalsIgnoreCase("Hard")
                ? R.drawable.background_level_hardy : R.drawable.background_level_easy;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap originalBackground = BitmapFactory.decodeResource(getResources(), backgroundResource, options);

        float aspectRatio = (float) originalBackground.getWidth() / (float) originalBackground.getHeight();
        int proportionalHeight = Math.round(getWidth() / aspectRatio);

        if (proportionalHeight < getHeight()) {
            proportionalHeight = getHeight();
            int proportionalWidth = Math.round(getHeight() * aspectRatio);
            backgroundBitmap = Bitmap.createScaledBitmap(originalBackground, proportionalWidth, proportionalHeight, true);
        } else {
            backgroundBitmap = originalBackground;
        }
    }

    private void update() {
        gameLogic.update();
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            Canvas canvas = surfaceHolder.lockCanvas();

            canvas.drawColor(Color.BLACK);
            canvas.drawBitmap(backgroundBitmap, 0, 0, null);

            canvas.drawBitmap(gameLogic.getPlayer().getBitmap(),
                    gameLogic.getPlayer().getX(), gameLogic.getPlayer().getY(), null);

            for (Enemy enemy : gameLogic.getEnemies()) {
                canvas.drawBitmap(enemy.getBitmap(),
                        enemy.getX(), enemy.getY(), null);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    /**
     * @noinspection BusyWait
     */
    private void control() {
        // Controla la velocidad de actualización del juego (fps)
        try {
            do {
                Thread.sleep(16);
            } while (!surfaceHolder.getSurface().isValid());
            // Aproximadamente 60 fps
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        isPlaying = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        isPlaying = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gameLogic != null) {
            gameLogic.onPlayerTouchEvent(event);
        }
        return true; // Indica que el evento táctil ha sido manejado
    }


}
