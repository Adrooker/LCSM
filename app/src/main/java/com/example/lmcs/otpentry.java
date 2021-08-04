package com.example.lmcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class otpentry extends AppCompatActivity {

    Button confotp,retloup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpentry);

        confotp = findViewById(R.id.otpb1);
        retloup = findViewById(R.id.otpres);

        confotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(otpentry.this,renterpass.class);
                startActivity(intent);
            }
        });
        retloup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(otpentry.this,otpentry.class);
                startActivity(intent);
            }
        });
    }
}