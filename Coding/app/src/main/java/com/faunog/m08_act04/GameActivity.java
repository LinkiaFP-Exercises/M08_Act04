package com.faunog.m08_act04;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Actividad que representa la pantalla de juego.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @version 1.0
 * @since 23/12/2023
 */
public class GameActivity extends AppCompatActivity {

    private SoundEffects soundEffects;
    private GameView gameView;
    private TextView playerNameTextView, levelTextView, scoreTextView;

    /**
     * Método llamado cuando se crea la actividad.
     *
     * @param savedInstanceState Estado anterior de la actividad, si está disponible.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initializeElements();
        setTextViewContents();
    }

    /**
     * Inicializa los elementos de la actividad.
     */
    private void initializeElements() {
        soundEffects = SoundEffects.getInstance().setLevelMediaPlayer(this,
                getIntent().getStringExtra("selectedLevel"));
        gameView = findViewById(R.id.gameView);
        playerNameTextView = findViewById(R.id.playerNameTextView);
        levelTextView = findViewById(R.id.levelTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
    }

    /**
     * Configura el contenido de los TextView con información del intent.
     */
    private void setTextViewContents() {
        String playerName = getIntent().getStringExtra("playerName");
        playerName = (playerName != null && !playerName.isEmpty()) ? playerName : "Unknown Player";
        playerNameTextView.setText(playerName);

        String selectedLevel = getIntent().getStringExtra("selectedLevel");
        levelTextView.setText("LEVEL: " + selectedLevel);
    }

    /**
     * Método llamado cuando la actividad pasa a primer plano.
     */
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
        soundEffects.onResume();
    }

    /**
     * Método llamado cuando la actividad pasa a segundo plano.
     */
    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
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

    /**
     * Obtiene el TextView asociado al nombre del jugador.
     *
     * @return TextView asociado al nombre del jugador.
     */
    public TextView getPlayerNameTextView() {
        return playerNameTextView;
    }

    /**
     * Obtiene el TextView asociado al nivel de dificultad.
     *
     * @return TextView asociado al nivel de dificultad.
     */
    public TextView getLevelTextView() {
        return levelTextView;
    }

    /**
     * Actualiza el TextView del puntaje en el hilo de la interfaz de usuario.
     *
     * @param score Puntaje a mostrar en el TextView.
     */
    public void updateScoreTextViewOnUiThread(final int score) {
        runOnUiThread(() -> updateScoreTextView(score));
    }

    /**
     * Actualiza el TextView del puntaje.
     *
     * @param score Puntaje a mostrar en el TextView.
     */
    public void updateScoreTextView(int score) {
        String newScore = "SCORE : " + score;
        scoreTextView.setText(newScore);
    }
}