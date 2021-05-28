package com.example.crm.AttendanceManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.crm.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class AttendenceGraphActivity extends AppCompatActivity {

    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_graph);

        lineChart = findViewById(R.id.lineChart);
        LineDataSet  lineDataSet = new LineDataSet(lineChartDataSet(), "data set");
        ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
        iLineDataSets.add(lineDataSet);

        LineData lineData = new LineData(iLineDataSets);
        lineChart.setData(lineData);
        lineChart.invalidate();

        lineDataSet.setColor(Color.BLUE);
//        lineDataSet.setCircleColor(Color.GREEN);
        lineDataSet.setLineWidth(7);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setCircleHoleRadius(10);

    }

    public ArrayList<Entry> lineChartDataSet(){
        ArrayList<Entry> dataset = new ArrayList<Entry>();

        dataset.add(new Entry(0, 15));
        dataset.add(new Entry(1, 45));
        dataset.add(new Entry(2, 25));
        dataset.add(new Entry(3, 54));
        dataset.add(new Entry(4, 90));
        dataset.add(new Entry(5, 75));
        dataset.add(new Entry(6, 90));

        return dataset;

    }
}