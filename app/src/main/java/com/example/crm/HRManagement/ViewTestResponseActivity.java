package com.example.crm.HRManagement;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crm.Model.Candidate;
import com.example.crm.Model.TestResponse;
import com.example.crm.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Arrays;

public class ViewTestResponseActivity extends AppCompatActivity {

	TestResponse testResponse;
	Candidate candidate;
	BarChart mChart1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_test_response);

		String candidateID = getIntent().getExtras().getString("candidateID");
		mChart1 = findViewById(R.id.barchart);

		getTestReponse(candidateID);

	}

	private void getTestReponse(String candidateID) {
		//TODO fetch

		testResponse = new TestResponse();
		testResponse.setCorrectAns(new ArrayList<>(Arrays.asList(9, 7, 10, 5)));
		testResponse.setTimeRequired(5 * 60 * 1000);
		testResponse.setRemark("Found switching between apps.\nAttempt to copy question.");
		updadeUI();
	}

	private void updadeUI() {

		mChart1.getDescription().setEnabled(false);
		mChart1.setPinchZoom(false);

		mChart1.setDrawGridBackground(false);
		mChart1.setDrawBarShadow(false);

		mChart1.setDrawValueAboveBar(false);
		mChart1.setHighlightFullBarEnabled(false);

		YAxis leftAxis = mChart1.getAxisLeft();
		leftAxis.setDrawGridLines(false);
		leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
		mChart1.getAxisRight().setEnabled(false);

		XAxis xLabels = mChart1.getXAxis();
		xLabels.setPosition(XAxis.XAxisPosition.TOP);

		// chart.setDrawXLabels(false);
		// chart.setDrawYLabels(false);

		Legend l = mChart1.getLegend();
		l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
		l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
		l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
		l.setDrawInside(false);
		l.setFormSize(8f);
		l.setFormToTextSpace(4f);
		l.setXEntrySpace(6f);

		ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

		for (int i = 0; i < 4; i++) {
			float val1 = testResponse.getCorrectAns().get(i);
			float val2 = 10 - val1;
			yVals1.add(new BarEntry(i, new float[]{val1, val2}));
		}

		BarDataSet set1;

		if (mChart1.getData() != null &&
				mChart1.getData().getDataSetCount() > 0) {
			set1 = (BarDataSet) mChart1.getData().getDataSetByIndex(0);
			set1.setValues(yVals1);
			mChart1.getData().notifyDataChanged();
			mChart1.notifyDataSetChanged();
		} else {
			set1 = new BarDataSet(yVals1, "");
			set1.setDrawIcons(false);
			set1.setColors(Color.parseColor("#C820AE36"), Color.parseColor("#C7FF0000"));
			set1.setStackLabels(new String[]{"Correct", "Wrong"});

			ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
			dataSets.add(set1);

			BarData data = new BarData(dataSets);
			data.setValueTextColor(Color.WHITE);

			String[] xAxisLables = new String[]{"Aptitude", "Logical", "Technical", "Literature"};

			mChart1.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLables));
			mChart1.getXAxis().setGranularityEnabled(true);
			mChart1.getXAxis().setGranularity(1);
			mChart1.getXAxis().setLabelRotationAngle(45f);
			mChart1.getXAxis().setDrawGridLines(false);

			mChart1.setData(data);

			mChart1.setFitBars(true);
			mChart1.invalidate();
		}

	}
}