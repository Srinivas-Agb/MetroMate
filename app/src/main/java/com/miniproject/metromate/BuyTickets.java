package com.miniproject.metromate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class BuyTickets extends AppCompatActivity{

    Button back;
    CardView paytm,phonepe,whatsapp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_tickets);

        back = findViewById(R.id.back_btn3);
        back.setOnClickListener(v -> finish());


        paytm = findViewById(R.id.cardpaytm);
        paytm.setOnClickListener(arg0 -> startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://paytm.com/metro-card-recharge"))));

        phonepe = findViewById(R.id.cardPhonepe);
        phonepe.setOnClickListener(arg1 -> startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://phon.pe/HYDERABADMETRO "))));

        whatsapp = findViewById(R.id.cardWhatsapp);
        whatsapp.setOnClickListener(arg2 -> startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://wa.me/+918341146468"))));
    }

}








