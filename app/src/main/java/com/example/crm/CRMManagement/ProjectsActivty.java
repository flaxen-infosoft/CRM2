package com.example.crm.CRMManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.crm.R;

public class ProjectsActivty extends AppCompatActivity {
Button newproject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_activty);
        newproject=findViewById(R.id.newproject);
        newproject.setOnClickListener(view -> {

        });
    }
}