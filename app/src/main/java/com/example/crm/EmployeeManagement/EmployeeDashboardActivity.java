package com.example.crm.EmployeeManagement;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
			//Intent intent = new Intent(EmployeeDashboardActivity.this, FlaxenDocumentationActivity.class);
			Intent intent = new Intent(EmployeeDashboardActivity.this, ActivityNewJoinee.class);
			startActivity(intent);
		});

		department.setOnClickListener(v -> {
			Intent intent = new Intent(EmployeeDashboardActivity.this, EmployeeDepartmentActivity.class);
			startActivity(intent);
		});

		register.setOnClickListener(v -> {
			Intent intent = new Intent(EmployeeDashboardActivity.this, EmployeeListActivity.class);
			startActivity(intent);
		});

	}
}