package com.example.crm.EmployeeManagement;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Model.Employee;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewJoineeEmployee extends AppCompatActivity {
	RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_joinee_employee);
		recyclerView = findViewById(R.id.employee_newjoinee_recyler);
		recyclerView.setLayoutManager(new LinearLayoutManager(NewJoineeEmployee.this));


		RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
		Gson gson = new Gson();
		Call<ArrayList<Employee>> call = ri.getEmployee();
		call.enqueue(new Callback<ArrayList<Employee>>() {
			@Override
			public void onResponse(Call<ArrayList<Employee>> call, Response<ArrayList<Employee>> response) {
			}

			@Override
			public void onFailure(Call<ArrayList<Employee>> call, Throwable t) {

			}
		});


	}
}