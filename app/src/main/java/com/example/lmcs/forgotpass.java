package com.example.lmcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class forgotpass extends AppCompatActivity {

    Button callotp,retsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);

        callotp = findViewById(R.id.otpb);
        retsignup = findViewById(R.id.otpres);


        callotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(forgotpass.this,otpentry.class);
                startActivity(intent);
            }
        });
        retsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(forgotpass.this,signup.class);
                startActivity(intent);
            }
        });
    }
}