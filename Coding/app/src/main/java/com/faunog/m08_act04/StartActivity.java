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


public class StartActivity extends AppCompatActivity {

    private SoundEffects soundEffects;
    private EditText editTextPlayerName;
    private RadioGroup radioGroupLevels;
    private Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initializeElements();
        setButtonStart();
    }

    private void initializeElements() {
        soundEffects = SoundEffects.getInstance().setBasicMediaPlayer(this, R.raw.start);
        editTextPlayerName = findViewById(R.id.editTextPlayerName);
        editTextPlayerName.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
        Animation blinkAnimation = AnimationUtils.loadAnimation(this, R.anim.edittext_blink);
        editTextPlayerName.startAnimation(blinkAnimation);
        radioGroupLevels = findViewById(R.id.radioGroupLevels);
        buttonStart = findViewById(R.id.buttonStart);
    }

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
