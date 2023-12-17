package com.faunog.m08_act04;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    private GameView gameView;
    private TextView playerNameTextView, levelTextView, scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameView = findViewById(R.id.gameView);
        playerNameTextView = findViewById(R.id.playerNameTextView);
        levelTextView = findViewById(R.id.levelTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        playerNameTextView.setText(getIntent().getStringExtra("playerName"));
        levelTextView.setText(getIntent().getStringExtra("selectedLevel"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
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