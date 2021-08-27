package com.example.crm.HRManagement;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.CustomToast;
import com.example.crm.Model.Report;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportingActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting);
        recyclerView = findViewById(R.id.reportrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(ReportingActivity.this));
        RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
        Call<List<Report>> call = ri.getReports();
        call.enqueue(new Callback<List<Report>>() {
            @Override
            public void onResponse(Call<List<Report>> call, Response<List<Report>> response) {
                if (response.isSuccessful()) {
                    List<Report> reportList = response.body();
                    ReportsAdapter reportsAdapter = new ReportsAdapter(ReportingActivity.this, reportList);
                    recyclerView.setAdapter(reportsAdapter);
                } else {
                    CustomToast.makeText(ReportingActivity.this, "Failed " + response.code(), 1, Color.RED);
                }
            }

            @Override
            public void onFailure(Call<List<Report>> call, Throwable t) {
                CustomToast.makeText(ReportingActivity.this, "Failed " + t.getMessage(), 1, Color.RED);

            }
        });
    }
}