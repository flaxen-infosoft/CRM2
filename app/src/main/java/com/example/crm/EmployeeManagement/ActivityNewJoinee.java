package com.example.crm.EmployeeManagement;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crm.Model.Employee;
import com.example.crm.R;
import com.google.gson.Gson;

public class ActivityNewJoinee extends AppCompatActivity {

	Employee employee;
	TextView name, dept, date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_joinee);

		name = findViewById(R.id.name);
		dept = findViewById(R.id.dept);
		date = findViewById(R.id.date);

		String jsonEmployee = getIntent().getExtras().getString("employee");

		Gson gson = new Gson();
		employee = gson.fromJson(jsonEmployee, Employee.class);

		name.setText(employee.getName());
		date.setText(employee.getDate());
		dept.setText(employee.getDesignation());
	}
}