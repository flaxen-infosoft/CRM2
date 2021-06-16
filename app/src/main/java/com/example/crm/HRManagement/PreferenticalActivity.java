package com.example.crm.HRManagement;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.CustomToast;
import com.example.crm.Model.Candidate;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;
import com.google.gson.Gson;

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
		getPreferentialCandidate();
	}

	public void getPreferentialCandidate() {

		RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);

		Call<ArrayList<Candidate>> call = ri.getPreferCandidate();
		call.enqueue(new Callback<ArrayList<Candidate>>() {
			@Override
			public void onResponse(Call<ArrayList<Candidate>> call, Response<ArrayList<Candidate>> response) {
				if (response.isSuccessful()) {
					candidates = response.body();
					Gson gson = new Gson();
					Log.e("123", gson.toJson(candidates));
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
		rv.setAdapter(adapter);

	}

	public class PreferentialCandidateViewHolder extends RecyclerView.ViewHolder {

		TextView name, applied_for, date, confirmationmail;

		public PreferentialCandidateViewHolder(@NonNull View itemView) {
			super(itemView);
			name = itemView.findViewById(R.id.name);
			applied_for = itemView.findViewById(R.id.applied_for);
			date = itemView.findViewById(R.id.date);
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

			holder.name.setText(candidates.get(position).getName());
			holder.applied_for.setText(candidates.get(position).getApplied_for());
			holder.date.setText(candidates.get(position).getDateof_interview());
		}

		@Override
		public int getItemCount() {
			return candidates.size();
		}
	}
}