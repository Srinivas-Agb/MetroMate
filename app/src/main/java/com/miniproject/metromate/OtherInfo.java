package com.miniproject.metromate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OtherInfo extends AppCompatActivity {

    Button back,offers,helpline,offlwebsite,tokens,feedback,developers1;
    TextView t1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_info);

        t1=findViewById(R.id.otherInfoText);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform your desired actions here
                developers1.setVisibility(View.VISIBLE);
            }
        });

        back = findViewById(R.id.back_btn16);
        back.setOnClickListener(v -> finish());

        offers = findViewById(R.id.offers);
        helpline = findViewById(R.id.helpline);
        offlwebsite = findViewById(R.id.offlwebsite);
        tokens = findViewById(R.id.tokens);
        feedback =findViewById(R.id.feedback);
        developers1 = findViewById(R.id.developers);

        developers1.setVisibility(View.INVISIBLE);
        developers1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtherInfo.this,AboutDevelopers.class));
            }
        });


        tokens.setOnClickListener(arg0 -> startActivity(new Intent(OtherInfo.this, TokensAndSmartcard.class)));
        offlwebsite.setOnClickListener(arg0 -> startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.ltmetro.com/"))));
        feedback.setOnClickListener(arg0 -> startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://docs.google.com/forms/d/e" +
                        "/1FAIpQLSc3SjSeEAopbXM2TnWHwP15hwU7b1DfraXK16ANJdqdPLcPcw/viewform?usp=sf_link"))));

        offers.setOnClickListener(arg0->startActivity(new Intent(OtherInfo.this, Offers.class)));
        //developers.setOnClickListener(arg0->startActivity(new Intent(OtherInfo.this, AboutDev.class)));

        helpline.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:04023332555"));
            startActivity(intent);
        });
    }
}
