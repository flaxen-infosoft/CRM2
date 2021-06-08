package com.example.crm.HRManagement;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.crm.CustomToast;
import com.example.crm.Model.Candidate;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShortlistedCandidateDetailsActivity extends AppCompatActivity {

	TabLayout tabLayout;
	ViewPager viewPager;
	MainAdapter adapter;
	ArrayList<Candidate> shortlistedCandidates;
	CardView cardView;
	FirstRoundFragment firstRoundFragment;
	SecondRoundFragment secondRoundFragment;
	RejectedFragment rejectedFragment;
	OnHoldFragment onHoldFragment;
	BlackListedFragment blackListedFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shortlisted_candidate_details);

		retriveShortlistedCandidates();
		cardView = findViewById(R.id.card1);
		tabLayout = findViewById(R.id.tab_layout);
		viewPager = findViewById(R.id.view_pager);

		firstRoundFragment = new FirstRoundFragment();
		secondRoundFragment = new SecondRoundFragment();
		rejectedFragment = new RejectedFragment();
		onHoldFragment = new OnHoldFragment();
		blackListedFragment = new BlackListedFragment();

		adapter = new MainAdapter(getSupportFragmentManager());
		adapter.AddFragment(firstRoundFragment, "1st Round");
		adapter.AddFragment(secondRoundFragment, "2nd Round");
		adapter.AddFragment(rejectedFragment, "Rejected");
		adapter.AddFragment(onHoldFragment, "On Hold");
		adapter.AddFragment(blackListedFragment, "BlackListed");

		viewPager.setAdapter(adapter);

		tabLayout.setupWithViewPager(viewPager);

	}

	private void retriveShortlistedCandidates() {
		RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
		Call<ArrayList<Candidate>> call = ri.getAllShortlistedCandidates();
		call.enqueue(new Callback<ArrayList<Candidate>>() {
			@Override
			public void onResponse(Call<ArrayList<Candidate>> call, Response<ArrayList<Candidate>> response) {
				if (response.isSuccessful()) {
					shortlistedCandidates = response.body();
					if (shortlistedCandidates == null)
						shortlistedCandidates = new ArrayList<>();
					updateUI();

				} else {
					CustomToast.makeText(ShortlistedCandidateDetailsActivity.this, "Failed to load results", 0, Color.RED);
				}
			}

			@Override
			public void onFailure(Call<ArrayList<Candidate>> call, Throwable t) {
				CustomToast.makeText(ShortlistedCandidateDetailsActivity.this, "Internal error occured :(", 0, Color.RED);
			}
		});


	}

	private void updateUI() {
		firstRoundFragment.setShortlistedCandidates(shortlistedCandidates);
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