package com.faunog.m08_act04;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * Clase que gestiona los efectos de sonido en la aplicación.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @version 1.0
 * @since 23/12/2023
 */
public class SoundEffects implements MediaPlayer.OnErrorListener {

    private MediaPlayer mediaPlayer;

    /**
     * Obtiene una instancia única de la clase SoundEffects.
     *
     * @return Instancia única de SoundEffects.
     */
    public static SoundEffects getInstance() {
        return new SoundEffects();
    }

    /**
     * Reanuda la reproducción del sonido si se encuentra pausado.
     */
    public void onResume() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    /**
     * Pausa la reproducción del sonido si está reproduciéndose.
     */
    public void onPause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    /**
     * Libera los recursos asociados al sonido al destruir la instancia.
     */
    public void onDestroy() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    /**
     * Configura un reproductor de medios básico con la música de fondo especificada.
     *
     * @param context            Contexto de la aplicación.
     * @param backgroundResource Recurso de la música de fondo.
     * @return Instancia de SoundEffects configurada con el reproductor básico.
     */
    public SoundEffects setBasicMediaPlayer(Context context, int backgroundResource) {
        try {
            mediaPlayer = MediaPlayer.create(context, backgroundResource);
            mediaPlayer.setLooping(true);
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.start();
        } catch (Exception e) {
            Log.d(context.getClass().toString(), "Error in setMediaPlayer()\n" + e.getMessage());
        }
        return this;
    }

    /**
     * Configura un reproductor de medios con la música de fondo según el nivel especificado.
     *
     * @param context Contexto de la aplicación.
     * @param level   Nivel de dificultad del juego ("Easy" o "Hard").
     * @return Instancia de SoundEffects configurada con el reproductor según el nivel.
     */
    public SoundEffects setLevelMediaPlayer(Context context, String level) {
        try {
            int backgroundResource = level.equalsIgnoreCase("Hard") ? R.raw.game_hard : R.raw.game_easy;
            mediaPlayer = MediaPlayer.create(context, backgroundResource);
            mediaPlayer.setLooping(true);
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.start();
        } catch (Exception e) {
            Log.d(context.getClass().toString(), "Error in setMediaPlayer()\n" + e.getMessage());
        }
        return this;
    }

    /**
     * Maneja errores en la reproducción del MediaPlayer.
     *
     * @param mp    MediaPlayer que generó el error.
     * @param what  Tipo de error.
     * @param extra Información adicional sobre el error.
     * @return Verdadero si se maneja el error, falso en caso contrario.
     */
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.d(this.getClass().toString(), "onError: MediaPlayer=" + mp.toString() + "\n WHAT=" + what + "\n EXTRA=" + extra);
        return false;
    }
}
