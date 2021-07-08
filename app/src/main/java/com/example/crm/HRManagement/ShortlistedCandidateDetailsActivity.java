package com.example.crm.HRManagement;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.crm.CustomProgressAlert;
import com.example.crm.CustomToast;
import com.example.crm.Model.Candidate;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShortlistedCandidateDetailsActivity extends AppCompatActivity {

	TabLayout tabLayout;
	ViewPager viewPager;
	MainAdapter adapter;
	ArrayList<Candidate> shortlistedCandidates, firstrcleared, secondrcleared, onhold, blacklisted, rejected;
	CardView cardView;
	EditText search;
	FirstRoundFragment firstRoundFragment;
	SecondRoundFragment secondRoundFragment;
	RejectedFragment rejectedFragment;
	OnHoldFragment onHoldFragment;
	BlackListedFragment blackListedFragment;
	ProgressDialog loading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shortlisted_candidate_details);

		loading = CustomProgressAlert.make(this, "Loading...");
		loading.show();
		retriveShortlistedCandidates();
		cardView = findViewById(R.id.card1);
		tabLayout = findViewById(R.id.tab_layout);
		viewPager = findViewById(R.id.view_pager);
		search = findViewById(R.id.search);

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

		search.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				ArrayList<Candidate> temp = new ArrayList<>();
				ArrayList<Candidate> par;
				switch (viewPager.getCurrentItem()) {
					case 0:
						par = firstrcleared;
						break;
					case 1:
						par = secondrcleared;
						break;
					case 2:
						par = rejected;
						break;
					case 3:
						par = onhold;
						break;
					case 4:
						par = blacklisted;
						break;
					default:
						throw new IllegalStateException("Unexpected value: " + viewPager.getCurrentItem());
				}

				for (Candidate c : par) {
					if (c.getName().contains(s))
						temp.add(c);
				}
				switch (viewPager.getCurrentItem()) {
					case 0:
						if (s.toString().length() == 0)
							firstRoundFragment.setTempList(firstrcleared);
						else
							firstRoundFragment.setTempList(temp);
						break;
					case 1:
						if (s.toString().length() == 0)
							secondRoundFragment.setTempList(secondrcleared);
						else
							secondRoundFragment.setTempList(temp);
						break;
					case 2:
						if (s.toString().length() == 0)
							rejectedFragment.setTempList(rejected);
						else
							rejectedFragment.setTempList(temp);
						break;
					case 3:
						if (s.toString().length() == 0)
							onHoldFragment.setTempList(onhold);
						else
							onHoldFragment.setTempList(temp);
						break;
					case 4:
						if (s.toString().length() == 0)
							blackListedFragment.setTempList(blacklisted);
						else
							blackListedFragment.setTempList(temp);
						break;
				}
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});


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
		loading.hide();
		firstrcleared = new ArrayList<>();
		secondrcleared = new ArrayList<>();
		onhold = new ArrayList<>();
		blacklisted = new ArrayList<>();
		rejected = new ArrayList<>();

		for (Candidate c : shortlistedCandidates) {
			switch (c.getStatus()) {
				case "Second Round Cleared":
					secondrcleared.add(c);
					break;
				case "On Hold":
					onhold.add(c);
					break;
				case "Rejected":
					rejected.add(c);
					break;
				case "Blacklisted":
					blacklisted.add(c);
					break;
				default:
					firstrcleared.add(c);
					break;
			}
		}


		firstRoundFragment.setShortlistedCandidates(firstrcleared);
		secondRoundFragment.setShortlistedCandidates(secondrcleared);
		onHoldFragment.setShortlistedCandidates(onhold);
		rejectedFragment.setShortlistedCandidates(rejected);
		blackListedFragment.setShortlistedCandidates(blacklisted);

	}

	public void createDialog(Candidate candidate) {
		View v = LayoutInflater.from(this).inflate(R.layout.shortlist_dialog_layout, null, false);
		TextView promote = v.findViewById(R.id.promote);
		TextView title = v.findViewById(R.id.title);
		TextView blacklist = v.findViewById(R.id.blacklist);
		TextView onhold = v.findViewById(R.id.hold);
		TextView reject = v.findViewById(R.id.reject);


		MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(this);
		final int[] currSelection = {-1};

		title.setText("Confirm action for " + candidate.getName() + "?");
		View.OnClickListener listener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
					case R.id.promote:
						promote.setBackgroundColor(Color.parseColor("#E0F7FA"));
						blacklist.setBackgroundColor(Color.TRANSPARENT);
						onhold.setBackgroundColor(Color.TRANSPARENT);
						reject.setBackgroundColor(Color.TRANSPARENT);
						currSelection[0] = 0;
						break;
					case R.id.blacklist:
						promote.setBackgroundColor(Color.TRANSPARENT);
						blacklist.setBackgroundColor(Color.parseColor("#E0F7FA"));
						onhold.setBackgroundColor(Color.TRANSPARENT);
						reject.setBackgroundColor(Color.TRANSPARENT);
						currSelection[0] = 1;
						break;
					case R.id.hold:
						promote.setBackgroundColor(Color.TRANSPARENT);
						blacklist.setBackgroundColor(Color.TRANSPARENT);
						onhold.setBackgroundColor(Color.parseColor("#E0F7FA"));
						reject.setBackgroundColor(Color.TRANSPARENT);
						currSelection[0] = 2;
						break;
					case R.id.reject:
						promote.setBackgroundColor(Color.TRANSPARENT);
						blacklist.setBackgroundColor(Color.TRANSPARENT);
						onhold.setBackgroundColor(Color.TRANSPARENT);
						reject.setBackgroundColor(Color.parseColor("#E0F7FA"));
						currSelection[0] = 3;
						break;
				}
			}
		};

		promote.setOnClickListener(listener);
		blacklist.setOnClickListener(listener);
		onhold.setOnClickListener(listener);
		reject.setOnClickListener(listener);

		dialog.setView(v);
		dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				int prev = -1;
				switch (candidate.getStatus()) {
					case "Second Round Cleared":
						prev = 1;
						break;
					case "Blacklisted":
						prev = 4;
						break;
					case "On Hold":
						prev = 3;
						break;
					case "Rejected":
						prev = 2;
						break;
					default:
						prev = 0;
				}
				switch (currSelection[0]) {
					case 0:
						if (prev == 1) {
							showDialogToConfirm(candidate);
						} else {
							candidate.setStatus("Second Round Cleared");
							updateCandidateStatus(candidate, prev, 1);
						}
						break;
					case 1:
						candidate.setStatus("Blacklisted");
						updateCandidateStatus(candidate, prev, 4);
						break;
					case 2:
						candidate.setStatus("On Hold");
						updateCandidateStatus(candidate, prev, 3);
						break;
					case 3:
						candidate.setStatus("Rejected");
						updateCandidateStatus(candidate, prev, 2);
						break;
					default:
						break;
				}
			}
		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).show();


	}

	public void updateCandidateStatus(Candidate candidate, int from, int to) {
		Gson gson = new Gson();
		Candidate nc = new Candidate();
		nc.setId(candidate.getId());
		nc.setStatus(candidate.getStatus());
		RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
		Call<Candidate> call = ri.updateShortlisedCandidate(candidate);
		call.enqueue(new Callback<Candidate>() {
			@Override
			public void onResponse(Call<Candidate> call, Response<Candidate> response) {
				//from = 0 for 1st rnd, 1 for 2nd rnd, 2 for rejected, 3 for hold, 4 blacklisted
				if (response.isSuccessful()) {
					switch (from) {
						case 0:
							firstrcleared.remove(candidate);
							firstRoundFragment.refreshData();
							break;
						case 1:
							secondrcleared.remove(candidate);
							secondRoundFragment.refreshData();
							break;
						case 2:
							rejected.remove(candidate);
							rejectedFragment.refreshData();
							break;
						case 3:
							onhold.remove(candidate);
							onHoldFragment.refreshData();
							break;
						case 4:
							blacklisted.remove(candidate);
							blackListedFragment.refreshData();
					}

					switch (to) {
						case 0:
							firstrcleared.add(candidate);
							firstRoundFragment.refreshData();
							break;
						case 1:
							secondrcleared.add(candidate);
							secondRoundFragment.refreshData();
							break;
						case 2:
							rejected.add(candidate);
							rejectedFragment.refreshData();
							break;
						case 3:
							onhold.add(candidate);
							onHoldFragment.refreshData();
							break;
						case 4:
							blacklisted.add(candidate);
							blackListedFragment.refreshData();
					}
					viewPager.setCurrentItem(to, true);
				}
			}

			@Override
			public void onFailure(Call<Candidate> call, Throwable t) {
				CustomToast.makeText(ShortlistedCandidateDetailsActivity.this, "Failed to update status :(", 0, Color.RED);
			}
		});
	}

	private void showDialogToConfirm(Candidate candidate) {
		new MaterialAlertDialogBuilder(this).setTitle("Confirm wanted to add " + candidate.getName() + " in preferential?")
				.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						addCandidateToPrefrential(candidate);
					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();
	}

	private void addCandidateToPrefrential(Candidate candidate) {
		candidate.setStatus("Preferential");

		RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
		Call<JsonObject> call = ri.preferCandidate(candidate);

		call.enqueue(new Callback<JsonObject>() {
			@Override
			public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
				if (response.isSuccessful()) {

					Intent i = new Intent(ShortlistedCandidateDetailsActivity.this, PreferenticalActivity.class);
					startActivity(i);
					finish();
				} else {
					CustomToast.makeText(ShortlistedCandidateDetailsActivity.this, "Failed to update status :(", 0, Color.RED);
				}
			}

			@Override
			public void onFailure(Call<JsonObject> call, Throwable t) {
				CustomToast.makeText(ShortlistedCandidateDetailsActivity.this, "Failed to update status :(", 0, Color.RED);
			}
		});


	}

	public void updateRemark(Candidate candidate, String remark, int pos) {
		candidate.setRemarks(candidate.getRemarks() + "\n\n" + remark);
		RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
		Call<Candidate> call = ri.updateShortlisedCandidate(candidate);
		call.enqueue(new Callback<Candidate>() {
			@Override
			public void onResponse(@NotNull Call<Candidate> call, Response<Candidate> response) {
				if (response.isSuccessful()) {
					Log.e("123", "success");
					shortlistedCandidates.get(pos).setRemarks(shortlistedCandidates.get(pos).getRemarks() + "\n\n" + remark);
				} else {
					CustomToast.makeText(ShortlistedCandidateDetailsActivity.this, "Failed to update", 0, Color.RED);
				}
			}

			@Override
			public void onFailure(@NotNull Call<Candidate> call, @NotNull Throwable t) {
				CustomToast.makeText(ShortlistedCandidateDetailsActivity.this, "Failed to update", 0, Color.RED);
			}
		});

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