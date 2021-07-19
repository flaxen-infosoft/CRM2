package com.example.crm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;

public class Clientdetails extends AppCompatActivity {
    private CardView androidapp;
    private CardView website;
    private CardView webapp;
    private CardView digitalmarketing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_deatails);
      androidapp =findViewById(R.id.client_androidapp);
        webapp =findViewById(R.id.client_webapp);
        website =findViewById(R.id.client_website);
        digitalmarketing =findViewById(R.id.digital_marketing);
      androidapp.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

          }
      });
        webapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        digitalmarketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}