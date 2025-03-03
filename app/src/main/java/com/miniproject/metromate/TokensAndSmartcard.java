package com.miniproject.metromate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TokensAndSmartcard extends AppCompatActivity {
    Button back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tokens_and_smartcards);

        back = findViewById(R.id.back_btn22);
        back.setOnClickListener(v -> finish());
    }
}
