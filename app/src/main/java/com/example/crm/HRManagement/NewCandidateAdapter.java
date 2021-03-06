package com.example.crm.HRManagement;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Model.Candidate;
import com.example.crm.R;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.List;

;

public class NewCandidateAdapter extends RecyclerView.Adapter<NewCandidateAdapter.ViewHolder> {

	Context context;
	List<Candidate> candidateList;

	public NewCandidateAdapter(Context context, List<Candidate> candidateList) {
		this.context = context;
		this.candidateList = candidateList;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		View v = layoutInflater.inflate(R.layout.candidatecard, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		Candidate candidate = candidateList.get(position);
		holder.expandableRelativeLayout.collapse();
		holder.name.setText(candidate.getName());
		holder.designation.setText(candidate.getDesignation());
		holder.date.setText(candidate.getDateof_interview());
		holder.updatestatus.setOnClickListener(view -> {
			holder.expandableRelativeLayout.toggle();
		});
		holder.resumebt.setOnClickListener(view -> {
			Intent intent = new Intent(context, PdfViewerActivity.class);
			intent.putExtra("pdfurl", candidate.getResume());
			context.startActivity(intent);
		});
		holder.call.setOnClickListener(view -> {
			Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + candidate.getPhone()));
			context.startActivity(intent);
		});
		holder.itemView.setOnClickListener(view -> {
			Intent intent = new Intent(context, CandidateRemark.class);
			intent.putExtra("id", candidate.getId());
			context.startActivity(intent);
		});
		holder.assignedBy.setText("Assigned by: " + candidate.getAssignedBy());
	}

	@Override
	public int getItemCount() {
		return candidateList.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		TextView name, designation, resumebt, date, updatestatus, assignedBy;
		ImageView call;
		Button interested, notinterested;
		ExpandableRelativeLayout expandableRelativeLayout;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			name = itemView.findViewById(R.id.tv_card_candidatename);
			designation = itemView.findViewById(R.id.tv_card_candidatedesignation);
			date = itemView.findViewById(R.id.tv_card_candidatedate);
			resumebt = itemView.findViewById(R.id.resumebt);
			call = itemView.findViewById(R.id.callimage);
			updatestatus = itemView.findViewById(R.id.updateStatusofcandidate);
			interested = itemView.findViewById(R.id.interestedbt);
			notinterested = itemView.findViewById(R.id.notinterestedbt);
			expandableRelativeLayout = itemView.findViewById(R.id.admin_expandableLayout);
			assignedBy = itemView.findViewById(R.id.assignedBy);
		}
	}
}
