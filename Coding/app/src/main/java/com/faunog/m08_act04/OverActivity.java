package com.faunog.m08_act04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Actividad que representa la pantalla de fin de juego.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @version 1.0
 * @since 23/12/2023
 */
public class OverActivity extends AppCompatActivity {

    private SoundEffects soundEffects;
    private TextView textViewPlayerName, textViewScore;
    private Button buttonRestart;

    /**
     * Método llamado cuando se crea la actividad.
     *
     * @param savedInstanceState Estado anterior de la actividad, si está disponible.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over);
        initializeElements();
        setTextViewContents();
        setButtonStart();
    }

    /**
     * Inicializa los elementos de la actividad.
     */
    private void initializeElements() {
        soundEffects = SoundEffects.getInstance().setBasicMediaPlayer(this, R.raw.over);
        textViewPlayerName = findViewById(R.id.textViewPlayerName);
        textViewScore = findViewById(R.id.textViewScore);
        buttonRestart = findViewById(R.id.buttonRestart);
    }

    /**
     * Configura el contenido de los TextView con información del intent.
     */
    private void setTextViewContents() {
        String playerName = getIntent().getStringExtra("PLAYER_NAME");
        textViewPlayerName.setText(playerName);

        int score = getIntent().getIntExtra("SCORE", 0);
        String pontuation = "PUNTOS: " + score;
        textViewScore.setText(pontuation);
    }

    /**
     * Configura el evento clic para el botón de reinicio.
     */
    private void setButtonStart() {
        buttonRestart.setOnClickListener(v -> {
            Intent intent = new Intent(OverActivity.this, StartActivity.class);
            startActivity(intent);
            finish();
        });
    }

    /**
     * Método llamado cuando la actividad pasa a primer plano.
     */
    @Override
    protected void onResume() {
        super.onResume();
        soundEffects.onResume();
    }

    /**
     * Método llamado cuando la actividad pasa a segundo plano.
     */
    @Override
    protected void onPause() {
        super.onPause();
        soundEffects.onPause();
    }

    /**
     * Método llamado cuando la actividad se destruye.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundEffects.onDestroy();
    }
}
