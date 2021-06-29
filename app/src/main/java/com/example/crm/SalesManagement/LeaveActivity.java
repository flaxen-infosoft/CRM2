package com.example.crm.SalesManagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.crm.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class LeaveActivity extends AppCompatActivity {

	TabLayout tabLayout;
	ViewPager viewPager;
	MainAdapter adapter;
	LeaveApprovedFragment approved;
	LeavePendingFragment pending;
	LeaveDeclinedFragment declined;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leave);


		//TODO Fetch leaves where employee id = id of logged in employee

		tabLayout = findViewById(R.id.tab_layout);
		viewPager = findViewById(R.id.view_pager);
		approved = new LeaveApprovedFragment();
		pending = new LeavePendingFragment();
		declined = new LeaveDeclinedFragment();

		adapter = new MainAdapter(getSupportFragmentManager());
		adapter.AddFragment(pending, "Pending leaves");
		adapter.AddFragment(approved, "Approved leaves");
		adapter.AddFragment(declined, "Declined leaves");

		viewPager.setAdapter(adapter);

		tabLayout.setupWithViewPager(viewPager);
	}

	private class MainAdapter extends FragmentPagerAdapter {

		ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
		ArrayList<String> stringArrayList = new ArrayList<>();

		public MainAdapter(@NonNull FragmentManager fm) {
			super(fm);
		}

		public void AddFragment(Fragment fragment, String s) {
			fragmentArrayList.add(fragment);
			stringArrayList.add(s);
		}

		@NonNull
		@Override
		public Fragment getItem(int position) {
			return fragmentArrayList.get(position);
		}

		@Override
		public int getCount() {
			return fragmentArrayList.size();
		}

		@Nullable
		@Override
		public CharSequence getPageTitle(int position) {

			return stringArrayList.get(position);
		}
	}
}