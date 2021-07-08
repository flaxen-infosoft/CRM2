package com.example.crm.HRManagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Model.Candidate;
import com.example.crm.Model.Remark;
import com.example.crm.R;
import com.example.crm.SPOps;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Date;

public class SecondRoundFragment extends Fragment {
	ArrayList<Candidate> shortlistedCandidates;
	RecyclerView rv = null;
	SFragmentAdapter firstFragmentAdapter;
	View v;

	public SecondRoundFragment() {
		// Required empty public constructor
	}

	public void setShortlistedCandidates(ArrayList<Candidate> shortlistedCandidates) {
		this.shortlistedCandidates = new ArrayList<>();
		this.shortlistedCandidates.addAll(shortlistedCandidates);
		firstFragmentAdapter = new SFragmentAdapter();
		rv.setAdapter(firstFragmentAdapter);
		rv.setLayoutManager(new LinearLayoutManager(getContext()));
	}

	public void setTempList(ArrayList<Candidate> temp) {
		shortlistedCandidates.clear();
		shortlistedCandidates.addAll(temp);
		firstFragmentAdapter.notifyDataSetChanged();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		if (v == null) {
			v = inflater.inflate(R.layout.fragment_second_round, container, false);
			rv = v.findViewById(R.id.recyclerView);
		}
		return v;
	}

	public void refreshData() {
		if (firstFragmentAdapter != null)
			firstFragmentAdapter.notifyDataSetChanged();
	}

	public class SFragmentListViewHolder extends RecyclerView.ViewHolder {
		TextView name, appliedfor, date, round, resume, testreport, remark;
		ImageView call;

		public SFragmentListViewHolder(@NonNull View itemView) {
			super(itemView);
			name = itemView.findViewById(R.id.name);
			appliedfor = itemView.findViewById(R.id.applied_for);
			date = itemView.findViewById(R.id.date);
			call = itemView.findViewById(R.id.call);
			round = itemView.findViewById(R.id.round);
			resume = itemView.findViewById(R.id.resumebt);
			testreport = itemView.findViewById(R.id.testreport);
			remark = itemView.findViewById(R.id.remark);


			call.setOnClickListener(v -> {
				int pos = getAdapterPosition();
				Uri u = Uri.parse("tel:" + shortlistedCandidates.get(pos).getPhone());
				Intent i = new Intent(Intent.ACTION_DIAL, u);
				startActivity(i);
			});
			round.setOnClickListener(v -> {
				int pos = getAdapterPosition();
				((ShortlistedCandidateDetailsActivity) getActivity()).createDialog(shortlistedCandidates.get(pos));
			});
			resume.setOnClickListener(v -> {
				Intent i = new Intent(getContext(), PdfViewerActivity.class);
				i.putExtra("pdfurl", shortlistedCandidates.get(getAdapterPosition()).getResume());
				startActivity(i);
			});

			remark.setOnClickListener(v -> {
				int pos = getAdapterPosition();
				View view = LayoutInflater.from(getContext()).inflate(R.layout.remarks_dialog, null, false);
				TextView textView = ((TextView) view.findViewById(R.id.remarktxt));
				textView.setText(shortlistedCandidates.get(pos).getRemarks());
				textView.setScroller(new Scroller(getContext()));
				textView.setVerticalScrollBarEnabled(true);
				textView.setMovementMethod(new ScrollingMovementMethod());

				new MaterialAlertDialogBuilder(getContext())
						.setTitle("Remarks for " + shortlistedCandidates.get(pos).getName())
						.setView(view)
						.setCancelable(false)
						.setPositiveButton("Add remark", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
								View d = LayoutInflater.from(getContext()).inflate(R.layout.add_remark_dialog, null, false);
								new MaterialAlertDialogBuilder(getContext())
										.setView(d)
										.setTitle("Add Remark")
										.setPositiveButton("Done", new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface dialog, int which) {
												String remark = ((TextInputLayout) d.findViewById(R.id.et)).getEditText().getText().toString();
												Remark r = new Remark();
												r.setAuthor(SPOps.getLoggedInUserName(getContext()));
												r.setText(remark);
												r.setDate(new Date());
												dialog.dismiss();
												((ShortlistedCandidateDetailsActivity) getActivity()).updateRemark(shortlistedCandidates.get(pos), r);
											}
										}).setCancelable(false)
										.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface dialog, int which) {
												dialog.dismiss();
											}
										}).show();

							}
						}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();
			});

		}
	}

	public class SFragmentAdapter extends RecyclerView.Adapter<SFragmentListViewHolder> {

		@NonNull
		@Override
		public SFragmentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View v = LayoutInflater.from(SecondRoundFragment.this.getContext()).inflate(R.layout.round_fragment_list_item, parent, false);
			return new SFragmentListViewHolder(v);
		}

		@Override
		public void onBindViewHolder(@NonNull SFragmentListViewHolder holder, int position) {
			Candidate candidate = shortlistedCandidates.get(position);
			holder.name.setText(candidate.getName());
			holder.appliedfor.setText("Applied for: " + candidate.getApplied_for());
			holder.date.setText("Date: " + candidate.getDateof_interview());
			holder.round.setText("2nd Round Cleared");

		}

		@Override
		public int getItemCount() {
			return shortlistedCandidates.size();
		}
	}
}