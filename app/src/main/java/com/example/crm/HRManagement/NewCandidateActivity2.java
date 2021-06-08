package com.example.crm.HRManagement;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.CustomToast;
import com.example.crm.Model.Candidate;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewCandidateActivity2 extends AppCompatActivity {

	List<Candidate> candidates;
	NewCandidate2Adapter adapter;
	RecyclerView rv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_candidate2);
		rv = findViewById(R.id.recyclerView);

		fetchCandidates();

	}

	public void fetchCandidates() {
		RetroInterface retroInterface = Retrofi.initretro().create(RetroInterface.class);

		Call<List<Candidate>> call = retroInterface.getcandidate();
		call.enqueue(new Callback<List<Candidate>>() {
			@Override
			public void onResponse(Call<List<Candidate>> call, Response<List<Candidate>> response) {
				if (!response.isSuccessful()) {
					CustomToast.makeText(NewCandidateActivity2.this, "Failed to fetch", 0, Color.parseColor("#32CD32"));
				} else {
					candidates = response.body();
					adapter = new NewCandidate2Adapter(NewCandidateActivity2.this, candidates);
					rv.setLayoutManager(new LinearLayoutManager(NewCandidateActivity2.this));
					rv.setAdapter(adapter);
				}
			}

			@Override
			public void onFailure(Call<List<Candidate>> call, Throwable t) {
				CustomToast.makeText(NewCandidateActivity2.this, "Failed to fetch", 0, Color.parseColor("#32CD32"));
			}
		});

	}


}