package com.faunog.m08_act04;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;


/**
 * Actividad que representa la pantalla de inicio del juego.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @version 1.0
 * @since 23/12/2023
 */
public class StartActivity extends AppCompatActivity {

    private SoundEffects soundEffects;
    private EditText editTextPlayerName;
    private RadioGroup radioGroupLevels;
    private Button buttonStart;

    /**
     * Método llamado cuando se crea la actividad.
     *
     * @param savedInstanceState Estado anterior de la actividad, si está disponible.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initializeElements();
        setButtonStart();
    }

    /**
     * Inicializa los elementos de la actividad.
     */
    private void initializeElements() {
        soundEffects = SoundEffects.getInstance().setBasicMediaPlayer(this, R.raw.start);
        radioGroupLevels = findViewById(R.id.radioGroupLevels);
        buttonStart = findViewById(R.id.buttonStart);

        editTextPlayerName = findViewById(R.id.editTextPlayerName);
        editTextPlayerName.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
        Animation blinkAnimation = AnimationUtils.loadAnimation(this, R.anim.edittext_blink);
        editTextPlayerName.startAnimation(blinkAnimation);
    }

    /**
     * Configura el evento clic para el botón de inicio.
     */
    private void setButtonStart() {
        buttonStart.setOnClickListener(view -> {
            Intent intent = new Intent(StartActivity.this, GameActivity.class);

            String playerName = editTextPlayerName.getText().toString();
            intent.putExtra("playerName", playerName);

            RadioButton selectedRadioButton = findViewById(radioGroupLevels.getCheckedRadioButtonId());
            String selectedLevel = selectedRadioButton.getText().toString();
            intent.putExtra("selectedLevel", selectedLevel);

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
