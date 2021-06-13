package com.example.crm.HRManagement;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Model.Candidate;
import com.example.crm.R;

import java.util.ArrayList;

public class BlackListedFragment extends Fragment {
	ArrayList<Candidate> shortlistedCandidates;
	RecyclerView rv = null;
	SFragmentAdapter firstFragmentAdapter;
	View v;

	public BlackListedFragment() {
		// Required empty public constructor
	}

	public void setShortlistedCandidates(ArrayList<Candidate> shortlistedCandidates) {
		this.shortlistedCandidates = shortlistedCandidates;

		firstFragmentAdapter = new SFragmentAdapter();

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		if (v == null) {
			v = inflater.inflate(R.layout.fragment_black_listed, container, false);
			rv = v.findViewById(R.id.recyclerView);
			rv.setAdapter(firstFragmentAdapter);
			rv.setLayoutManager(new LinearLayoutManager(getContext()));
		}
		return v;
	}

	public void refreshData() {
		if (firstFragmentAdapter != null)
			firstFragmentAdapter.notifyDataSetChanged();
	}

	public class SFragmentListViewHolder extends RecyclerView.ViewHolder {
		TextView name, appliedfor, date, round, title;
		ImageView call;

		public SFragmentListViewHolder(@NonNull View itemView) {
			super(itemView);
			name = itemView.findViewById(R.id.name);
			appliedfor = itemView.findViewById(R.id.applied_for);
			date = itemView.findViewById(R.id.date);
			call = itemView.findViewById(R.id.call);
			round = itemView.findViewById(R.id.round);
			title = itemView.findViewById(R.id.title);


			call.setOnClickListener(v -> {
				int pos = getAdapterPosition();
				Uri u = Uri.parse("tel:" + shortlistedCandidates.get(pos).getPhone());
				Intent i = new Intent(Intent.ACTION_DIAL, u);
				startActivity(i);
			});
			round.setOnClickListener(v -> {
				Log.e("123", "pressed");
				int pos = getAdapterPosition();
				((ShortlistedCandidateDetailsActivity) getActivity()).createDialog(shortlistedCandidates.get(pos));
			});

		}
	}

	public class SFragmentAdapter extends RecyclerView.Adapter<SFragmentListViewHolder> {

		@NonNull
		@Override
		public SFragmentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View v = LayoutInflater.from(BlackListedFragment.this.getContext()).inflate(R.layout.round_fragment_list_item, parent, false);
			return new SFragmentListViewHolder(v);
		}

		@Override
		public void onBindViewHolder(@NonNull SFragmentListViewHolder holder, int position) {
			Candidate candidate = shortlistedCandidates.get(position);
			holder.name.setText(candidate.getName());
			holder.appliedfor.setText("Applied for: " + candidate.getApplied_for());
			holder.date.setText("Date: " + candidate.getDateof_interview());
			holder.round.setTextColor(Color.BLACK);
			holder.round.setText("Blacklisted");

		}

		@Override
		public int getItemCount() {
			return shortlistedCandidates.size();
		}
	}
}