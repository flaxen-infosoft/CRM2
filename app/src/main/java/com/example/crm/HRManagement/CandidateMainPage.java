package com.example.crm.HRManagement;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.crm.R;

public class CandidateMainPage extends AppCompatActivity {

	CardView register, shortlisted, onlinetest, documentation, manage, new_register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_candidate_main_page);

		register = findViewById(R.id.candidate_register);
		shortlisted = findViewById(R.id.shortlisted);
		onlinetest = findViewById(R.id.onlinetest);
		documentation = findViewById(R.id.preferantioal);
		manage = findViewById(R.id.manage);
		new_register = findViewById(R.id.new_register);

		new_register.setOnClickListener(v -> {
			Intent intent = new Intent(CandidateMainPage.this, NewCandidateActivity.class);
			startActivity(intent);
		});

		documentation.setOnClickListener(v -> {
			Intent intent = new Intent(CandidateMainPage.this, PreferenticalActivity.class);
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
		manage.setOnClickListener(v -> {
			Intent intent = new Intent(CandidateMainPage.this, NewCandidateActivity2.class);
			startActivity(intent);
		});
	}
}