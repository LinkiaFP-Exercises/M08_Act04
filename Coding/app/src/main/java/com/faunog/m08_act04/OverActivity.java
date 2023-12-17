package com.faunog.m08_act04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over);

        TextView textViewPlayerName = findViewById(R.id.textViewPlayerName);
        TextView textViewScore = findViewById(R.id.textViewScore);
        Button buttonRestart = findViewById(R.id.buttonRestart);

        String playerName = getIntent().getStringExtra("PLAYER_NAME");
        int score = getIntent().getIntExtra("SCORE", 0);

        textViewPlayerName.setText(playerName);
        textViewScore.setText("PUNTOS: " + String.valueOf(score));

        buttonRestart.setOnClickListener(v -> {
            Intent intent = new Intent(OverActivity.this, StartActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
