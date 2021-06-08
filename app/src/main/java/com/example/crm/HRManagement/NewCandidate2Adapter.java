package com.example.crm.HRManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Model.Candidate;
import com.example.crm.R;

import java.util.ArrayList;
import java.util.List;

public class NewCandidate2Adapter extends RecyclerView.Adapter<NewCandidate2Adapter.CandidateHolder> {
	Context context;
	List<Candidate> candidates;

	public NewCandidate2Adapter(Context context, List<Candidate> candidates) {
		this.context = context;
		this.candidates = candidates;
	}

	@NonNull
	@Override
	public CandidateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(context).inflate(R.layout.item_new_candidate_2, parent, false);
		return new CandidateHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull CandidateHolder holder, int position) {
		holder.name.setText(candidates.get(position).getName());
		holder.date.setText("Date of Interview: " + candidates.get(position).getDateof_interview());
		holder.applied_for.setText("Applied for: " + candidates.get(position).getDesignation());
	}

	@Override
	public int getItemCount() {
		return candidates.size();
	}

	public class CandidateHolder extends RecyclerView.ViewHolder {

		TextView name, date, applied_for, link;

		public CandidateHolder(@NonNull View itemView) {
			super(itemView);
			name = itemView.findViewById(R.id.name);
			date = itemView.findViewById(R.id.date);
			applied_for = itemView.findViewById(R.id.applied_for);
			link = itemView.findViewById(R.id.link);

		}
	}
}
