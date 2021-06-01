package com.example.crm.EmployeeManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.example.crm.R;

public class EmployeeDashboardActivity extends AppCompatActivity {

    CardView register, department, documentation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_dashboard);

        register = findViewById(R.id.employee_newjoinee);
        department = findViewById(R.id.departments);
        documentation = findViewById(R.id.documentation);

        documentation.setOnClickListener(v -> {
            Intent intent = new Intent(EmployeeDashboardActivity.this, FlaxenDocumentationActivity.class);
            startActivity(intent);
        });

        department.setOnClickListener(v -> {
            Intent intent = new Intent(EmployeeDashboardActivity.this, EmployeeDepartmentActivity.class);
            startActivity(intent);
        });

        register.setOnClickListener(v -> {
            Intent intent = new Intent(EmployeeDashboardActivity.this, EmployeeRegisterSecondActivity.class);
            startActivity(intent);
        });

    }
}