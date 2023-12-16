package com.faunog.m08_act04;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    private final SurfaceHolder surfaceHolder;
    private Thread gameThread;
    private boolean isPlaying;
    private final Paint paint;
    private GameLogic gameLogic;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        surfaceHolder = getHolder();
        paint = new Paint();
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
        gameLogic = new GameLogic(getWidth(), getHeight(), getResources());
    }

    private void update() {
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            Canvas canvas = surfaceHolder.lockCanvas();

            canvas.drawColor(Color.BLACK);

            canvas.drawBitmap(gameLogic.getPlayer().getPlayerBitmap(), gameLogic.getPlayer().getX(), gameLogic.getPlayer().getY(), null);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        // Controla la velocidad de actualización del juego (fps)
        try {
            do {Thread.sleep(16);} while (!surfaceHolder.getSurface().isValid());
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
            gameLogic.onTouchEvent(event);
        }
        return true; // Indica que el evento táctil ha sido manejado
    }
}
