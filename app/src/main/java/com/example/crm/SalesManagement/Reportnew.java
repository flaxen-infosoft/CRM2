package com.example.crm.SalesManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crm.R;
import com.example.crm.ReportFabnew;

public class Reportnew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportnew);
    }

    public void fabclick(View view)
    {
        Intent intent=new Intent(Reportnew.this, ReportFabnew.class);
        intent.putExtra("empid",getIntent().getIntExtra("empid",0));
        startActivity(intent);
    }
}