package com.example.crm.HRManagement;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.CustomProgressAlert;
import com.example.crm.CustomToast;
import com.example.crm.EmployeeManagement.EmployeeRegistrationActvity;
import com.example.crm.Model.Candidate;
import com.example.crm.Model.Employee;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreferenticalActivity extends AppCompatActivity {

    ArrayList<Candidate> candidates, tempList;
    PreferentialCandidateAdapter adapter;
    RecyclerView rv;
    ProgressDialog loading;
    EditText search;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_preferentical);
        rv = findViewById(R.id.recyclerView);
        search = findViewById(R.id.search);
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            finish();
        });

        tempList = new ArrayList<>();
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tempList.clear();
                if (s.length() == 0) {
                    tempList.addAll(candidates);
                } else {
                    for (Candidate c : candidates) {
                        if (c.getName().contains(s))
                            tempList.add(c);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        loading = CustomProgressAlert.make(this, "Loading...");
        loading.show();
        getPreferentialCandidate();
    }

    public void getPreferentialCandidate() {

        RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);

        Call<ArrayList<Candidate>> call = ri.getPreferCandidate();
        call.enqueue(new Callback<ArrayList<Candidate>>() {
            @Override
            public void onResponse(Call<ArrayList<Candidate>> call, Response<ArrayList<Candidate>> response) {
                if (response.isSuccessful()) {
                    candidates = response.body();
                    for (Candidate c : candidates)
                        tempList.add(c);
                    updateUI();
                } else {
                    CustomToast.makeText(PreferenticalActivity.this, "Server Error :(", 0, Color.RED);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Candidate>> call, Throwable t) {
                CustomToast.makeText(PreferenticalActivity.this, "Error: " + t.getMessage(), 0, Color.RED);
            }
        });


    }

    private void updateUI() {
        loading.dismiss();
        adapter = new PreferentialCandidateAdapter();
        rv.setLayoutManager(new LinearLayoutManager(PreferenticalActivity.this));
        rv.setAdapter(adapter);

    }

    private void addCandidateIntoEmployee(Candidate candidate) {

        Employee dummyEmployee = new Employee();
        dummyEmployee.setName(candidate.getName());
        dummyEmployee.setDepartment(candidate.getDepartment());
        dummyEmployee.setDesignation(candidate.getDesignation());
        dummyEmployee.setPhone(candidate.getPhone());
        dummyEmployee.setAltphone(candidate.getAltphone());
        dummyEmployee.setPid(candidate.getPid());
        dummyEmployee.setOid(candidate.getOid());
        dummyEmployee.setSource(candidate.getSource());
        dummyEmployee.setState(candidate.getState());
        dummyEmployee.setCity(candidate.getCity());
        dummyEmployee.setStatus("New Joinee");
        dummyEmployee.setResume(candidate.getResume());
        dummyEmployee.setAddress(candidate.getAddress());
        dummyEmployee.setHave_leptop(candidate.getHave_laptop());
        dummyEmployee.setCollege_name(candidate.getCollege_name());
        dummyEmployee.setDuration(candidate.getDuration());
        dummyEmployee.setStipend_amount(candidate.getStipend());

        Intent i = new Intent(PreferenticalActivity.this, EmployeeRegistrationActvity.class);
        i.putExtra("employee", dummyEmployee);
        startActivity(i);
        finish();


    }

    public class PreferentialCandidateViewHolder extends RecyclerView.ViewHolder {

        TextView name, applied_for, date;
        MaterialCardView accepted, rejected;

        public PreferentialCandidateViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            applied_for = itemView.findViewById(R.id.applied_for);
            date = itemView.findViewById(R.id.date);
            accepted = itemView.findViewById(R.id.accepted);
            rejected = itemView.findViewById(R.id.reject);

            accepted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new MaterialAlertDialogBuilder(PreferenticalActivity.this)
                            .setTitle("Shortlist candidate as employee?")
                            .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    addCandidateIntoEmployee(candidates.get(getAdapterPosition()));
                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                }
            });
        }
    }

    public class PreferentialCandidateAdapter extends RecyclerView.Adapter<PreferentialCandidateViewHolder> {

        @NonNull
        @Override
        public PreferentialCandidateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(PreferenticalActivity.this).inflate(R.layout.preferential_candidate_list_item, parent, false);
            return new PreferentialCandidateViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull PreferentialCandidateViewHolder holder, int position) {

            holder.name.setText(tempList.get(position).getName());
            holder.applied_for.setText(tempList.get(position).getApplied_for());
            holder.date.setText(tempList.get(position).getDateof_interview());
        }

        @Override
        public int getItemCount() {
            return tempList.size();
        }
    }
}