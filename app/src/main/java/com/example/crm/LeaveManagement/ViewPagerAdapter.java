package com.example.crm.LeaveManagement;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	private final ArrayList<Fragment> fragments = new ArrayList<>();

	public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
		super(fm, behavior);
	}

	@NonNull
	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	public void add(Fragment fragment) {
		fragments.add(fragment);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}
}
