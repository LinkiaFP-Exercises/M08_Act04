package com.faunog.m08_act04;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class SoundEffects implements MediaPlayer.OnErrorListener {

    private MediaPlayer mediaPlayer;

    public static SoundEffects getInstance() {
        return new SoundEffects();
    }

    public void onResume() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public void onPause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

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

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.d(this.getClass().toString(), "onError: MediaPlayer=" + mp.toString() + "\n WHAT=" + what + "\n EXTRA=" + extra);
        return false;
    }
}
