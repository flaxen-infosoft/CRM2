package com.example.crm.EmployeeManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Model.Employee;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeListActivity extends AppCompatActivity {

	ArrayList<Employee> employees;

	RecyclerView rv;
	EmployeeAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee_list);
		employees = new ArrayList<>();
		rv = findViewById(R.id.recyclerView);
		adapter = new EmployeeAdapter();
		rv.setLayoutManager(new LinearLayoutManager(this));
		rv.setAdapter(adapter);


		//TODO Create API to fetch all employees and show them here => dummy employees

		fetch();
	}

	private void fetch() {
		RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
		Gson gson = new Gson();
		Call<ArrayList<Employee>> call = ri.getEmployee();
		call.enqueue(new Callback<ArrayList<Employee>>() {
			@Override
			public void onResponse(Call<ArrayList<Employee>> call, Response<ArrayList<Employee>> response) {
				if (!response.isSuccessful()) {
					System.out.println("response = " + response.code());
				}
				employees.addAll(response.body());
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onFailure(Call<ArrayList<Employee>> call, Throwable t) {
				System.out.println("t = " + t.getMessage());
			}
		});
	}


	public class EmployeeViewHolder extends RecyclerView.ViewHolder {

		TextView name, date, dept;

		public EmployeeViewHolder(@NonNull View itemView) {
			super(itemView);
			name = itemView.findViewById(R.id.name);
			date = itemView.findViewById(R.id.date);
			dept = itemView.findViewById(R.id.dept);

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int pos = getAdapterPosition();
					Intent i = new Intent(EmployeeListActivity.this, ActivityNewJoinee.class);
					i.putExtra("employee", employees.get(pos));
					startActivity(i);
				}
			});


		}
	}

	public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeViewHolder> {

		@NonNull
		@Override
		public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View v = LayoutInflater.from(EmployeeListActivity.this).inflate(R.layout.employee_list_item, parent, false);
			return new EmployeeViewHolder(v);
		}

		@Override
		public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
			holder.name.setText(employees.get(position).getName());
			holder.date.setText(employees.get(position).getDate());
			holder.dept.setText(employees.get(position).getDepartment());
		}

		@Override
		public int getItemCount() {
			return employees.size();
		}
	}

}