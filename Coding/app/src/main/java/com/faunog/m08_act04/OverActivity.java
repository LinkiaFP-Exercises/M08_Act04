package com.faunog.m08_act04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OverActivity extends AppCompatActivity {

    private SoundEffects soundEffects;
    private TextView textViewPlayerName, textViewScore;
    private Button buttonRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over);
        initializeElements();
        setTextViewContents();
        setButtonStart();
    }

    private void initializeElements() {
        soundEffects = SoundEffects.getInstance().setBasicMediaPlayer(this, R.raw.over);
        textViewPlayerName = findViewById(R.id.textViewPlayerName);
        textViewScore = findViewById(R.id.textViewScore);
        buttonRestart = findViewById(R.id.buttonRestart);
    }

    private void setTextViewContents() {
        String playerName = getIntent().getStringExtra("PLAYER_NAME");
        textViewPlayerName.setText(playerName);

        int score = getIntent().getIntExtra("SCORE", 0);
        String pontuation = "PUNTOS: " + score;
        textViewScore.setText(pontuation);
    }

    private void setButtonStart() {
        buttonRestart.setOnClickListener(v -> {
            Intent intent = new Intent(OverActivity.this, StartActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        soundEffects.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        soundEffects.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundEffects.onDestroy();
    }
}
