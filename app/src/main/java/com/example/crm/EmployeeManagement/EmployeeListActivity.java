package com.example.crm.EmployeeManagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.CustomProgressAlert;
import com.example.crm.CustomToast;
import com.example.crm.Model.Employee;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeListActivity extends AppCompatActivity {

    ArrayList<Employee> employees;
    RecyclerView rv;
    EmployeeAdapter adapter;
    ProgressDialog loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        loading = CustomProgressAlert.make(this, "Loading...");
        employees = new ArrayList<>();
        rv = findViewById(R.id.recyclerView);
        adapter = new EmployeeAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        fetch();
    }

    private void fetch() {
        loading.show();
        RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
        Call<ArrayList<Employee>> call = ri.getEmployee();
        call.enqueue(new Callback<ArrayList<Employee>>() {
            @Override
            public void onResponse(Call<ArrayList<Employee>> call, Response<ArrayList<Employee>> response) {
                loading.dismiss();
                if (!response.isSuccessful()) {
                    System.out.println("response = " + response.code());
                } else {
                    for (Employee emp : response.body())
                        if (emp.getStatus().equals("New Joinee") || emp.getStatus().equals("Preferential"))
                            employees.add(emp);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Employee>> call, Throwable t) {
                loading.dismiss();
                System.out.println("t = " + t.getMessage());
            }
        });
    }

    public void updateEmployeeStatusToTraining(Employee employee) {
        Employee dummyEmployee = new Employee();
        dummyEmployee.setId(employee.getId());
        dummyEmployee.setStatus("Training");

        RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
        Call<Employee> call = ri.updateEmployee(dummyEmployee);
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Intent i = new Intent(EmployeeListActivity.this, EmployeeTrainingActivity.class);
                startActivity(i);
                finish();
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                CustomToast.makeText(EmployeeListActivity.this, "Failed to perform operation", 0, Color.RED);
            }
        });

    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView name, date, dept;
        ImageView img, docs;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            dept = itemView.findViewById(R.id.dept);
            img = itemView.findViewById(R.id.img);
            docs = itemView.findViewById(R.id.docs);

            docs.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                Intent i = new Intent(EmployeeListActivity.this, ActivityNewJoinee.class);
                i.putExtra("employee", employees.get(pos));
                startActivity(i);
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
            //TODO: uncomment this
            // Glide.with(EmployeeListActivity.this).load(employees.get(position).getProfileImg()).into(holder.img);

            holder.itemView.setOnClickListener(v -> new MaterialAlertDialogBuilder(EmployeeListActivity.this)
                    .setTitle("Add " + employees.get(position).getName() + " to Training?")
                    .setPositiveButton("Confirm", (dialog, which) -> {
                        dialog.dismiss();
                        updateEmployeeStatusToTraining(employees.get(position));
                    }).setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss()).show());

        }

        @Override
        public int getItemCount() {
            return employees.size();
        }
    }


}