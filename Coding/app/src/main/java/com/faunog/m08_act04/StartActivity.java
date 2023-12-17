package com.faunog.m08_act04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    private EditText editTextPlayerName;
    private RadioGroup radioGroupLevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        editTextPlayerName = findViewById(R.id.editTextPlayerName);
        radioGroupLevels = findViewById(R.id.radioGroupLevels);
        Button buttonStart = findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(view -> {
            String playerName = editTextPlayerName.getText().toString();

            // Obtener el RadioButton seleccionado en el grupo
            RadioButton selectedRadioButton = findViewById(radioGroupLevels.getCheckedRadioButtonId());
            String selectedLevel = selectedRadioButton.getText().toString();

            // Pass data to the game activity (GameActivity)
            Intent intent = new Intent(StartActivity.this, GameActivity.class);
            intent.putExtra("playerName", playerName);
            intent.putExtra("selectedLevel", selectedLevel);
            startActivity(intent);
            finish();
        });
    }
}

