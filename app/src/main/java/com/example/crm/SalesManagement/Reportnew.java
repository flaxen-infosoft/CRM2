package com.example.crm.SalesManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.R;
import com.example.crm.RecyclerviewadapterReport;
import com.example.crm.ReportFabnew;
import com.example.crm.Retro.Apicontrollerflaxen;
import com.google.gson.JsonArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Reportnew extends AppCompatActivity {
       JsonArray j;
       ArrayList<Callsinfo> arr;
       RecyclerView recyclerView;
       RecyclerviewadapterReport recyclerviewadapterReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportnew);
recyclerView=findViewById(R.id.recycler619);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Call<ArrayList<Callsinfo>> call= Apicontrollerflaxen.getInstance().getapi().getReport(1);
        call.enqueue(new Callback<ArrayList<Callsinfo>>() {
            @Override
            public void onResponse(Call<ArrayList<Callsinfo>> call, Response<ArrayList<Callsinfo>> response)
            { arr=response.body();
                System.out.println(arr.get(0).getTotalcalls());
                recyclerviewadapterReport=new RecyclerviewadapterReport(Reportnew.this,arr);
                recyclerView.setAdapter(recyclerviewadapterReport);


            }

            @Override
            public void onFailure(Call<ArrayList<Callsinfo>> call, Throwable t) {

            }
        });      //  System.out.println(c.getHours());
    }


    public void fabclick(View view)
    {
        Intent intent=new Intent(Reportnew.this, ReportFabnew.class);
        intent.putExtra("empid",getIntent().getIntExtra("empid",0));
        startActivity(intent);
    }
}