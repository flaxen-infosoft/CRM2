package com.example.crm.HRManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.crm.R;

public class NewCandidateActivity extends AppCompatActivity {

    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_candidate);

        cardView = findViewById(R.id.card1);

        cardView.setOnClickListener(v -> startActivity(new Intent(NewCandidateActivity.this, CandidateRemark.class)));
    }
}