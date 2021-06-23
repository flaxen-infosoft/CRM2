package com.example.crm.LeaveManagement;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
	UpdateStatusListeners usl;


	public RecyclerViewAdapter(Context context, ArrayList<LeaveItem> items, UpdateStatusListeners usl) {
		this.context = context;
		this.items = items;
		this.usl = usl;
	}

	@NonNull
	@Override
	public LeaveItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(context).inflate(R.layout.leave_list_item, parent, false);
		return new LeaveItemHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull LeaveItemHolder holder, int position) {
		LeaveItem item = items.get(position);
		holder.name.setText("");
		holder.sub.setText(item.getTitle());
		holder.body.setText(item.getBody());
		holder.dateandtime.setText(item.getDate());
		holder.leavetype.setText(item.getLeavetype());
		setStatus(holder, item);

	}

	public void setStatus(LeaveItemHolder holder, LeaveItem item) {
		if (item.getDeptHr().equals("Approved"))
			holder.depthr1.setColorFilter(context.getResources().getColor(R.color.green));
		else if (item.getDeptHr().equals("Rejected"))
			holder.depthr1.setColorFilter(context.getResources().getColor(R.color.red));
		else holder.depthr1.setColorFilter(context.getResources().getColor(R.color.grey));

		if (item.getDeptHr1().equals("Approved"))
			holder.depthr2.setColorFilter(context.getResources().getColor(R.color.green));
		else if (item.getDeptHr1().equals("Rejected"))
			holder.depthr1.setColorFilter(context.getResources().getColor(R.color.red));
		else holder.depthr2.setColorFilter(context.getResources().getColor(R.color.grey));

		if (item.getTeamLead().equals("Approved"))
			holder.teamlead.setColorFilter(context.getResources().getColor(R.color.green));
		else if (item.getTeamLead().equals("Rejected"))
			holder.depthr1.setColorFilter(context.getResources().getColor(R.color.red));
		else holder.teamlead.setColorFilter(context.getResources().getColor(R.color.grey));

		if (item.getTeamMember().equals("Approved"))
			holder.teammember.setColorFilter(context.getResources().getColor(R.color.green));
		else if (item.getTeamMember().equals("Rejected"))
			holder.depthr1.setColorFilter(context.getResources().getColor(R.color.red));
		else holder.teammember.setColorFilter(context.getResources().getColor(R.color.grey));

	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	public class LeaveItemHolder extends RecyclerView.ViewHolder {
		ViewGroup details;
		View expand;
		ConstraintLayout container;
		TextView name, sub, body, dateandtime, leavetype;
		ImageView depthr1, depthr2, teamlead, teammember;
		Button accept, reject;

		public LeaveItemHolder(@NonNull View itemView) {
			super(itemView);
			details = itemView.findViewById(R.id.details);
			expand = itemView.findViewById(R.id.expand);
			container = itemView.findViewById(R.id.container);
			name = itemView.findViewById(R.id.name);
			sub = itemView.findViewById(R.id.sub);
			body = itemView.findViewById(R.id.body);
			dateandtime = itemView.findViewById(R.id.dateandtime);
			leavetype = itemView.findViewById(R.id.leavetype);
			depthr1 = itemView.findViewById(R.id.depthr1);
			depthr2 = itemView.findViewById(R.id.depthr2);
			teamlead = itemView.findViewById(R.id.teamlead);
			teammember = itemView.findViewById(R.id.teammember);
			accept = itemView.findViewById(R.id.accept);
			reject = itemView.findViewById(R.id.reject);

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

			accept.setOnClickListener(v -> {
				usl.approve(items.get(getAdapterPosition()));
			});

			accept.setOnClickListener(v -> {
				usl.reject(items.get(getAdapterPosition()));
			});
		}

	}

}
