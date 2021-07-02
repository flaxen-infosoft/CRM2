package com.example.crm.LeaveManagement;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.crm.CustomToast;
import com.example.crm.Model.LeaveItem;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveManagementActivity extends AppCompatActivity {


	ViewPager viewPager;
	ViewPagerAdapter adapter;
	ArrayList<LeaveItem> leaves;
	LeaveManagementFragment1 frag1;
	LeaveManagementFragment2 frag2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leave_management);

		viewPager = findViewById(R.id.viewPager);
		adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
		frag1 = new LeaveManagementFragment1();
		frag2 = new LeaveManagementFragment2();
		adapter.add(frag1);
		adapter.add(frag2);

		viewPager.setAdapter(adapter);

		getLeaves();

	}

	private void getLeaves() {

		RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
		Call<ArrayList<LeaveItem>> call = ri.getLeaves();
		call.enqueue(new Callback<ArrayList<LeaveItem>>() {
			@Override
			public void onResponse(Call<ArrayList<LeaveItem>> call, Response<ArrayList<LeaveItem>> response) {
				if (response.isSuccessful()) {
					leaves = response.body();
					updateUI();

				} else
					CustomToast.makeText(LeaveManagementActivity.this, "Failed to load", 0, Color.RED);
			}

			@Override
			public void onFailure(Call<ArrayList<LeaveItem>> call, Throwable t) {
				CustomToast.makeText(LeaveManagementActivity.this, "Failed to load", 0, Color.RED);
			}
		});

	}

	private void updateUI() {
		Gson gson = new Gson();
		frag1.setLeaveItems(leaves);
	}

	public void updateLeave(boolean approved, LeaveItem item) {

		if (approved)
			item.setStatus("Approved");
		else item.setStatus("Rejected");

		RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
		Call<LeaveItem> call = ri.updateLeave(item);
		call.enqueue(new Callback<LeaveItem>() {
			@Override
			public void onResponse(Call<LeaveItem> call, Response<LeaveItem> response) {
				if (response.isSuccessful()) {
					CustomToast.makeText(LeaveManagementActivity.this, "Status updated", 0, Color.GREEN);
				} else {
					CustomToast.makeText(LeaveManagementActivity.this, "Failed to update status", 0, Color.RED);
				}
			}

			@Override
			public void onFailure(Call<LeaveItem> call, Throwable t) {
				CustomToast.makeText(LeaveManagementActivity.this, "Failed to update status", 0, Color.RED);
			}
		});

	}
}