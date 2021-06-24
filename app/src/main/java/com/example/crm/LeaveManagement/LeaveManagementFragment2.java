package com.example.crm.LeaveManagement;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.CustomToast;
import com.example.crm.Model.LeaveItem;
import com.example.crm.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class LeaveManagementFragment2 extends Fragment {

	ArrayList<LeaveItem> leaves, adapterLeaves;
	View v;
	RecyclerView recyclerView;
	LeaveAdapter adapter;
	CalendarView calendarView;

	public LeaveManagementFragment2() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new LeaveAdapter();
		leaves = new ArrayList<>();
		adapterLeaves = new ArrayList<>();

	}

	public void setLeaveItems(ArrayList<LeaveItem> leaves) {
		this.leaves = leaves;
		adapterLeaves.clear();
		adapterLeaves.addAll(leaves);
		adapter.notifyDataSetChanged();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		if (v == null) {
			v = inflater.inflate(R.layout.fragment_leave_management2, container, false);
			recyclerView = v.findViewById(R.id.recyclerView);
			recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
			recyclerView.setAdapter(adapter);
			calendarView = v.findViewById(R.id.leaveCalender);

			calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
				@Override
				public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
					ArrayList<LeaveItem> tempList = new ArrayList<>();
					adapterLeaves.clear();

					Calendar c = Calendar.getInstance();
					c.set(Calendar.DATE, dayOfMonth);
					c.set(Calendar.MONTH, month);
					c.set(Calendar.YEAR, year);
					c.set(Calendar.HOUR_OF_DAY, 0);
					c.set(Calendar.MINUTE, 0);

					Date date = c.getTime();
					try {
						for (LeaveItem l : leaves) {
							Date tdate = new SimpleDateFormat("dd MMM yyyy HH:mm").parse(l.getDate());
							Calendar _c = Calendar.getInstance();
							_c.setTime(tdate);
							_c.add(Calendar.DATE, l.getDuration());
							if (date.after(tdate) && date.before(_c.getTime())) {
								adapterLeaves.add(l);
							}
						}
					} catch (ParseException e) {
						CustomToast.makeText(getContext(), "Error occured while parsing", 0, Color.RED);
						e.printStackTrace();
					}

					adapter.notifyDataSetChanged();
				}
			});
		}

		return v;
	}

	public class LeaveHolder extends RecyclerView.ViewHolder {

		TextView name, dept, days;

		public LeaveHolder(@NonNull View itemView) {
			super(itemView);
			name = itemView.findViewById(R.id.name);
			dept = itemView.findViewById(R.id.dept);
			days = itemView.findViewById(R.id.days);
		}
	}

	public class LeaveAdapter extends RecyclerView.Adapter<LeaveHolder> {

		@NonNull
		@Override
		public LeaveHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View v = LayoutInflater.from(getContext()).inflate(R.layout.leave_list_item_2, parent, false);
			return new LeaveHolder(v);
		}

		@Override
		public void onBindViewHolder(@NonNull LeaveHolder holder, int position) {
			//holder.name.setText(leaves.get(position).getCandidateName());
			//holder.dept.setText(leaves.get(position).getCandidateDept());
			holder.days.setText("" + adapterLeaves.get(position).getDuration());
		}

		@Override
		public int getItemCount() {
			return adapterLeaves.size();
		}
	}

}