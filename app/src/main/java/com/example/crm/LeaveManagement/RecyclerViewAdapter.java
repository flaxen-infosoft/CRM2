package com.example.crm.LeaveManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
		View v = LayoutInflater.from(context).inflate(R.layout.leave_list_item, null, false);
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
		public LeaveItemHolder(@NonNull View itemView) {
			super(itemView);
		}
	}

}
