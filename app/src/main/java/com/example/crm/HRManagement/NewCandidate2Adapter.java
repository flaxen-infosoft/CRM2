package com.example.crm.HRManagement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Constants;
import com.example.crm.Model.Candidate;
import com.example.crm.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class NewCandidate2Adapter extends RecyclerView.Adapter<NewCandidate2Adapter.CandidateHolder> {
	Context context;
	List<Candidate> candidates;
	GeneralInterface gi;

	public NewCandidate2Adapter(Context context, List<Candidate> candidates, GeneralInterface gi) {
		this.context = context;
		this.candidates = candidates;
		this.gi = gi;
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

		TextView name, date, applied_for, link, updateStatus;


		public CandidateHolder(@NonNull View itemView) {
			super(itemView);
			name = itemView.findViewById(R.id.name);
			date = itemView.findViewById(R.id.date);
			applied_for = itemView.findViewById(R.id.applied_for);
			link = itemView.findViewById(R.id.link);
			updateStatus = itemView.findViewById(R.id.updateStatus);

			updateStatus.setOnClickListener(v -> {
				int pos = getAdapterPosition();
				new MaterialAlertDialogBuilder(context).setTitle("Shortlist " + candidates.get(pos).getName() + "?")
						.setNeutralButton("Cancel", (dialog, which) -> {
							dialog.dismiss();
						})
						.setPositiveButton("Shortlist", (dialog, which) -> {
							gi.onShortlistCandidate(candidates.get(pos));
						}).show();

			});
			link.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int pos = getAdapterPosition();
					Intent intent = new Intent(Intent.ACTION_SEND);
					intent.setType("text/html");
					intent.putExtra(Intent.EXTRA_EMAIL, candidates.get(pos).getPid());
					intent.putExtra(Intent.EXTRA_SUBJECT, Constants.email_subject);
					intent.putExtra(Intent.EXTRA_TEXT, Constants.body + "http://com.example.crm/" + candidates.get(pos).getId());
					context.startActivity(Intent.createChooser(intent, "Send Email"));
					//((NewCandidateActivity2)context).sendEmail(candidates.get(pos));

				}
			});


		}
	}
}
