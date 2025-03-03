package com.miniproject.metromate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HolidayList extends AppCompatActivity {
    Button back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.holiday_list);
        back = findViewById(R.id.back_btn2);
        back.setOnClickListener(v -> finish());


    }
}






