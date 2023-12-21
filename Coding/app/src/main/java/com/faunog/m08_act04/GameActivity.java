package com.faunog.m08_act04;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private SoundEffects soundEffects;
    private GameView gameView;
    private TextView playerNameTextView, levelTextView, scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initializeElements();
        setTextViewContents();
    }

    private void initializeElements() {
        soundEffects = SoundEffects.getInstance().setLevelMediaPlayer(this,
                getIntent().getStringExtra("selectedLevel"));
        gameView = findViewById(R.id.gameView);
        playerNameTextView = findViewById(R.id.playerNameTextView);
        levelTextView = findViewById(R.id.levelTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
    }

    private void setTextViewContents() {
        playerNameTextView.setText(getIntent().getStringExtra("playerName"));
        levelTextView.setText(getIntent().getStringExtra("selectedLevel"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
        soundEffects.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
        soundEffects.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundEffects.onDestroy();
    }

    public TextView getPlayerNameTextView() {
        return playerNameTextView;
    }

    public TextView getLevelTextView() {
        return levelTextView;
    }

    public void updateScoreTextViewOnUiThread(final int score) {
        runOnUiThread(() -> updateScoreTextView(score));
    }

    public void updateScoreTextView(int score) {
        String newScore = "SCORE : " + score;
        scoreTextView.setText(newScore);
    }
}