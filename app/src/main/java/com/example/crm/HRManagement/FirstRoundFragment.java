package com.example.crm.HRManagement;

import android.content.Intent;
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

public class FirstRoundFragment extends Fragment {
	ArrayList<Candidate> shortlistedCandidates;
	RecyclerView rv = null;
	FirstFragmentAdapter firstFragmentAdapter;
	View v;

	public FirstRoundFragment() {
		// Required empty public constructor
	}

	public void setShortlistedCandidates(ArrayList<Candidate> shortlistedCandidates) {
		this.shortlistedCandidates = shortlistedCandidates;

		firstFragmentAdapter = new FirstFragmentAdapter();
		rv.setAdapter(firstFragmentAdapter);
		rv.setLayoutManager(new LinearLayoutManager(getContext()));
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		if (v == null)
			v = inflater.inflate(R.layout.fragment_first_round, container, false);
		if (rv == null)
			rv = v.findViewById(R.id.recyclerView);
		return v;
	}

	public class FirstFragmentListViewHolder extends RecyclerView.ViewHolder {
		TextView name, appliedfor, date;
		ImageView call;

		public FirstFragmentListViewHolder(@NonNull View itemView) {
			super(itemView);
			name = itemView.findViewById(R.id.name);
			appliedfor = itemView.findViewById(R.id.applied_for);
			date = itemView.findViewById(R.id.date);
			call = itemView.findViewById(R.id.call);


			call.setOnClickListener(v -> {
				int pos = getAdapterPosition();
				Uri u = Uri.parse("tel:" + shortlistedCandidates.get(pos).getPhone());
				Intent i = new Intent(Intent.ACTION_DIAL, u);
				startActivity(i);
			});

		}
	}

	public class FirstFragmentAdapter extends RecyclerView.Adapter<FirstFragmentListViewHolder> {

		@NonNull
		@Override
		public FirstFragmentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View v = LayoutInflater.from(FirstRoundFragment.this.getContext()).inflate(R.layout.round_fragment_list_item, parent, false);
			return new FirstFragmentListViewHolder(v);
		}

		@Override
		public void onBindViewHolder(@NonNull FirstFragmentListViewHolder holder, int position) {
			Candidate candidate = shortlistedCandidates.get(position);
			holder.name.setText(candidate.getName());
			holder.appliedfor.setText("Applied for: " + candidate.getApplied_for());
			holder.date.setText("Date: " + candidate.getDateof_interview());
		}

		@Override
		public int getItemCount() {
			return shortlistedCandidates.size();
		}
	}
}