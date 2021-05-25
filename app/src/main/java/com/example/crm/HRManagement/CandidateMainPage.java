package com.example.crm.HRManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.example.crm.EmployeeManagement.EmployeeDashboardActivity;
import com.example.crm.R;

public class CandidateMainPage extends AppCompatActivity {

    CardView register, shortlisted , onlinetest, documentation, meeting, new_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_main_page);

        register = findViewById(R.id.candidate_register);
        shortlisted = findViewById(R.id.shortlisted);
        onlinetest = findViewById(R.id.onlinetest);
        documentation = findViewById(R.id.documentation);
        meeting = findViewById(R.id.meeting);
        new_register = findViewById(R.id.new_register);

        new_register.setOnClickListener(v -> {
            Intent intent = new Intent(CandidateMainPage.this, NewCandidateActivity.class);
            startActivity(intent);
        });

        documentation.setOnClickListener(v -> {
            Intent intent = new Intent(CandidateMainPage.this, DocumentationActivity.class);
            startActivity(intent);
        });

        shortlisted.setOnClickListener(v -> {
            Intent intent = new Intent(CandidateMainPage.this, ShortlistedCandidateDetailsActivity.class);
            startActivity(intent);
        });

        register.setOnClickListener(v -> {
            Intent intent = new Intent(CandidateMainPage.this, CandidateRegistration.class);
            startActivity(intent);
        });

        onlinetest.setOnClickListener(v -> {
            Intent intent = new Intent(CandidateMainPage.this, TestRespoondActivity.class);
            startActivity(intent);
        });
    }
}