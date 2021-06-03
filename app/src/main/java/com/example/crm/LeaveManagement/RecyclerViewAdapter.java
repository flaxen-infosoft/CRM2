package com.example.crm.LeaveManagement;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import com.example.crm.Model.LeaveItem;
import com.example.crm.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.LeaveItemHolder> {

	Context context;
	ArrayList<LeaveItem> items;

	public RecyclerViewAdapter(Context context, ArrayList<LeaveItem> items) {
		this.context = context;
		this.items = items;
	}

	@NonNull
	@Override
	public LeaveItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(context).inflate(R.layout.leave_list_item, parent, false);
		return new LeaveItemHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull LeaveItemHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	public class LeaveItemHolder extends RecyclerView.ViewHolder {
		ViewGroup details;
		View expand;
		ConstraintLayout container;

		public LeaveItemHolder(@NonNull View itemView) {
			super(itemView);
			details = itemView.findViewById(R.id.details);
			expand = itemView.findViewById(R.id.expand);
			container = itemView.findViewById(R.id.container);

			expand.setOnClickListener(v -> {

				if (details.getVisibility() == View.GONE) {
					expand.setRotation(180f);
					Transition transition = new Slide(Gravity.BOTTOM);
					transition.setDuration(300);
					transition.addTarget(details);
					TransitionManager.beginDelayedTransition(container, transition);
					details.setVisibility(View.VISIBLE);
				} else {
					expand.setRotation(0f);
					details.setVisibility(View.GONE);
				}
			});

		}
	}

}
