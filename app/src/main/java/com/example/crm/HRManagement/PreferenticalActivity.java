package com.example.crm.HRManagement;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.CustomToast;
import com.example.crm.Model.Candidate;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreferenticalActivity extends AppCompatActivity {

	ArrayList<Candidate> candidates;
	PreferentialCandidateAdapter adapter;
	RecyclerView rv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preferentical);
		rv = findViewById(R.id.recyclerView);
	}

	public void getPreferentialCandidate() {

		RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);

		Call<ArrayList<Candidate>> call = ri.getPreferCandidate();
		call.enqueue(new Callback<ArrayList<Candidate>>() {
			@Override
			public void onResponse(Call<ArrayList<Candidate>> call, Response<ArrayList<Candidate>> response) {
				if (response.isSuccessful()) {
					candidates = response.body();
					updateUI();
				} else {
					CustomToast.makeText(PreferenticalActivity.this, "Server Error :(", 0, Color.RED);
				}
			}

			@Override
			public void onFailure(Call<ArrayList<Candidate>> call, Throwable t) {
				CustomToast.makeText(PreferenticalActivity.this, "Error: " + t.getMessage(), 0, Color.RED);
			}
		});


	}

	private void updateUI() {

		adapter = new PreferentialCandidateAdapter();
		rv.setLayoutManager(new LinearLayoutManager(PreferenticalActivity.this));
		CustomToast.makeText(PreferenticalActivity.this, "Server Error :(", 0, Color.RED);

	}

	public class PreferentialCandidateViewHolder extends RecyclerView.ViewHolder {

		public PreferentialCandidateViewHolder(@NonNull View itemView) {
			super(itemView);
		}
	}

	public class PreferentialCandidateAdapter extends RecyclerView.Adapter<PreferentialCandidateViewHolder> {

		@NonNull
		@Override
		public PreferentialCandidateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View v = LayoutInflater.from(PreferenticalActivity.this).inflate(R.layout.preferential_candidate_list_item, parent, false);
			return new PreferentialCandidateViewHolder(v);
		}

		@Override
		public void onBindViewHolder(@NonNull PreferentialCandidateViewHolder holder, int position) {

		}

		@Override
		public int getItemCount() {
			return candidates.size();
		}
	}
}