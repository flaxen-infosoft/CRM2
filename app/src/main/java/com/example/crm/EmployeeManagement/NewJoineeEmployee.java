package com.example.crm.EmployeeManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crm.R;

public class NewJoineeEmployee extends AppCompatActivity {
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_joinee_employee);
        recyclerView=findViewById(R.id.employee_newjoinee_recyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(NewJoineeEmployee.this));
    }
}