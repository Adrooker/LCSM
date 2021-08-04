package com.example.lmcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class renterpass extends AppCompatActivity {

    Button confotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renterpass);

        confotp = findViewById(R.id.rectr);

        confotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(renterpass.this,Login.class);
                startActivity(intent);
            }
        });

    }
}