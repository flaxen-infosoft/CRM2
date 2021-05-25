package com.example.crm.HRManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.crm.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class SummeryTestFragment extends Fragment {

    PieChart pieChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v =  inflater.inflate(R.layout.fragment_summery_test, container, false);

       pieChart = v.findViewById(R.id.piechart);

        PieDataSet pieDataSet = new PieDataSet(pieChartDataSet(), "data");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setValueLineColor(R.color.toolbar_color);
        pieDataSet.setValueTextSize(14f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Score\n 12 / 15");
        pieChart.setCenterTextSize(19f);
        pieChart.setCenterTextColor(R.color.grey);
        pieChart.animate();

        return v;

    }

    public ArrayList<PieEntry> pieChartDataSet(){
        ArrayList<PieEntry> dataset = new ArrayList<PieEntry>();

        dataset.add(new PieEntry(0, 15));
        dataset.add(new PieEntry(1, 45));
        dataset.add(new PieEntry(2, 25));
        dataset.add(new PieEntry(3, 54));

        return dataset;

    }
}