package com.example.crm.HRManagement;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.crm.CustomToast;
import com.example.crm.Model.QuestionPaper;
import com.example.crm.Model.QuestionPaperAdapter;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestPaperActivity extends AppCompatActivity implements View.OnClickListener {

	MaterialButton next, prev, verify;
	ViewPager pager;
	QuestionPaperAdapter qpa;
	String ID;
	QuestionPaper qp;
	TextView name, time;
	CountDownTimer cdt;
	Fragment aptiFrag, logicFrag, techFrag, litFrag;
	ViewPagerAdpater pagerAdpater;
	TabLayout tabLayout;
	ConstraintLayout main, sub;
	TextInputLayout pass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_paper);
		Uri data = getIntent().getData();
		List<String> params = data.getPathSegments();
		ID = params.get(0);

		verify = findViewById(R.id.verify);
		main = findViewById(R.id.main);
		sub = findViewById(R.id.sub);
		pass = findViewById(R.id.pass);

		verify.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (pass.getEditText().getText().toString().equals("123")) {
					sub.setVisibility(View.GONE);
					main.setVisibility(View.VISIBLE);
					init();
				} else {
					pass.setError("Invalid password");
				}
			}
		});


	}

	private void init() {
		name = findViewById(R.id.name);
		time = findViewById(R.id.time);
		tabLayout = findViewById(R.id.tab_layout);

		name.setText("Candidate ID: " + ID);

		pagerAdpater = new ViewPagerAdpater(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
		cdt = new CountDownTimer(600000, 1000) {

			public void onTick(long millisUntilFinished) {
				int sec = (int) (millisUntilFinished / 1000);
				String smin = "" + (int) (sec / 60);
				String ssec = "" + (int) (sec % 60);
				if (smin.length() == 1)
					smin = "0" + smin;
				if (ssec.length() == 1)
					ssec = "0" + ssec;
				time.setText("Time remaining: " + smin + ":" + ssec);
			}

			public void onFinish() {
				time.setText("Time over :(");
				CustomToast.makeText(TestPaperActivity.this, "Time over", 0, Color.GREEN);
			}
		};


		pager = findViewById(R.id.viewPager);
		next = findViewById(R.id.next);
		prev = findViewById(R.id.prev);

		next.setOnClickListener(this);
		prev.setOnClickListener(this);

		pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				handle(position);
			}

			@Override
			public void onPageSelected(int position) {
				handle(position);
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		prepareQuestionPaper();
	}

	private void prepareQuestionPaper() {

		qp = new QuestionPaper();
		qp.setUid("" + ID);
		try {
			qp.init();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		processQuestionPaper(qp);
	}

	public void processQuestionPaper(QuestionPaper questionPaper) {

		aptiFrag = new AptitudeTestFragment(questionPaper.getAptitiueQuestions());
		logicFrag = new LogicTestFragment(questionPaper.getLogicalQuestions());
		techFrag = new TechTestFragment(questionPaper.getTechnicalQuestions());
		litFrag = new LiteratureTestFragment(questionPaper.getLiteratureQuestions());

		pagerAdpater.add(aptiFrag, "Aptitude");
		pagerAdpater.add(logicFrag, "Logical Reasoning");
		pagerAdpater.add(techFrag, "Technical");
		pagerAdpater.add(litFrag, "Literature");
		pager.setAdapter(pagerAdpater);
		tabLayout.setupWithViewPager(pager);

		cdt.start();
	}

	public void getQuestionPaper() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		RetroInterface ri = retrofit.create(RetroInterface.class);
		Call<QuestionPaper> qpaper = ri.getQuestionPaper();
		qpaper.enqueue(new Callback<QuestionPaper>() {
			@Override
			public void onResponse(Call<QuestionPaper> call, Response<QuestionPaper> response) {
				qp = response.body();
				processQuestionPaper(qp);
			}

			@Override
			public void onFailure(Call<QuestionPaper> call, Throwable t) {
				Snackbar.make(findViewById(android.R.id.content), "Failed to load question paper", BaseTransientBottomBar.LENGTH_SHORT).show();
			}
		});

	}

	private void handle(int position) {
		switch (position) {
			case 0:
				next.setText("Next");
				prev.setVisibility(View.GONE);
				break;
			case 1:
				next.setText("Next");
				prev.setVisibility(View.VISIBLE);
				break;
			case 2:
				next.setText("Next");
				break;
			case 3:
				next.setText("Submit");
				break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.next:
				int position = pager.getCurrentItem();
				if (position == 3) {
					Intent i = new Intent(TestPaperActivity.this, TestResponsesRecorder.class);
					startActivity(i);
					finish();
				} else {
					pager.setCurrentItem(position + 1, true);
				}
				break;
			case R.id.prev:
				int pos = pager.getCurrentItem();
				if (pos != 0) {
					pager.setCurrentItem(pos - 1);
				}
				break;
		}
	}

	public class ViewPagerAdpater extends FragmentPagerAdapter {
		ArrayList<Fragment> fragments;
		ArrayList<String> stringArrayList;

		public ViewPagerAdpater(@NonNull FragmentManager fm, int behavior) {
			super(fm, behavior);
			fragments = new ArrayList<>();
			stringArrayList = new ArrayList<>();
		}

		@NonNull
		@Override
		public Fragment getItem(int position) {
			return fragments.get(position);
		}

		public void add(Fragment fragment, String title) {
			fragments.add(fragment);
			stringArrayList.add(title);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}

		@Nullable
		@Override
		public CharSequence getPageTitle(int position) {
			return stringArrayList.get(position);
		}
	}
}



        /*
        To instantiate GripRadioGroup

        GridRadioGroup grg = new GridRadioGroup(this, R.id.gridrg);

		grg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(@Nullable RadioGroup group, int checkedId) {
				switch (checkedId) {
					case R.id.op1:
						break;
					case R.id.op2:
						break;
					case R.id.op3:
						break;
					case R.id.op4:
						break;
				}
			}
		});

         */