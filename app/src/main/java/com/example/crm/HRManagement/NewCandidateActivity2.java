package com.example.crm.HRManagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.CustomDateRangePicker;
import com.example.crm.CustomProgressAlert;
import com.example.crm.CustomToast;
import com.example.crm.Model.Candidate;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewCandidateActivity2 extends AppCompatActivity {

    List<Candidate> candidates;
    NewCandidate2Adapter adapter;
    RecyclerView rv;
    GeneralInterface gi;
    ProgressDialog loading;
    EditText search;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_candidate2);
        rv = findViewById(R.id.recyclerView);
        search = findViewById(R.id.search);
        spinner = findViewById(R.id.filter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adapter != null) {
                    if (position == 0) {
                        adapter.setTempList(candidates);
                    } else if (position == 1) {
                        ArrayList<Candidate> tempList = new ArrayList<>(candidates);
                        Collections.sort(tempList, (m1, m2) -> m1.getDateof_interview().compareTo(m2.getDateof_interview()));
                        adapter.setTempList(tempList);
                    } else if (position == 2) {
                        ArrayList<Candidate> tempList = new ArrayList<>(candidates);
                        Collections.sort(tempList, (m1, m2) -> m1.getStatus().compareTo(m2.getStatus()));
                        adapter.setTempList(tempList);

                    } else if (position == 3) {

                        CustomDateRangePicker customDateRangePicker = new CustomDateRangePicker();
                        View v = customDateRangePicker.init(NewCandidateActivity2.this);

                        new MaterialAlertDialogBuilder(NewCandidateActivity2.this).setTitle("Select date range...")
                                .setPositiveButton("OK", (dialog, which) -> {
                                    dialog.dismiss();
                                    Date st = customDateRangePicker.getStartDate();
                                    Date en = customDateRangePicker.getEndDate();

                                    ArrayList<Candidate> tempList = new ArrayList<>();
                                    for (Candidate c : candidates) {
                                        try {
                                            Date test = new SimpleDateFormat("dd/MMMM/yyyy", Locale.getDefault()).parse(c.getDateof_interview());
                                            if (test != null && test.after(st) && test.before(en))
                                                tempList.add(c);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    adapter.setTempList(tempList);
                                }).setCancelable(true)
                                .setView(v).show();
                    } else {
                        String dept = parent.getItemAtPosition(position).toString();
                        ArrayList<Candidate> tempList = new ArrayList<>();
                        for (Candidate c : candidates) {
                            if (c.getDesignation().equals(dept))
                                tempList.add(c);
                        }
                        adapter.setTempList(tempList);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner = findViewById(R.id.filter);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 0)
                    adapter.setTempList(candidates);
                else {
                    ArrayList<Candidate> nc = new ArrayList<>();
                    for (Candidate c : candidates) {
                        if (c.getName().contains(s))
                            nc.add(c);
                    }
                    adapter.setTempList(nc);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        loading = CustomProgressAlert.make(this, "Loading...");
        loading.show();

        gi = this::shortlistCandidate;

        fetchCandidates();

    }

    private void shortlistCandidate(Candidate candidate) {

        RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
        Call<JsonObject> call = ri.shortListCandidate(candidate.getId());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NotNull Call<JsonObject> call, @NotNull Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Intent i = new Intent(NewCandidateActivity2.this, ShortlistedCandidateDetailsActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    CustomToast.makeText(NewCandidateActivity2.this, "Failed to update status :(", 0, Color.RED);
                }
            }

            @Override
            public void onFailure(@NotNull Call<JsonObject> call, @NotNull Throwable t) {
                CustomToast.makeText(NewCandidateActivity2.this, "Failed to update status :( " + t.getMessage(), 0, Color.RED);
            }
        });


    }

    public void updateRemark(Candidate candidate, String remark, int pos) {
        Log.e("123", remark + " " + pos);
        RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
        Call<Candidate> call = ri.updateCandidate(candidate);
        call.enqueue(new Callback<Candidate>() {
            @Override
            public void onResponse(@NotNull Call<Candidate> call, Response<Candidate> response) {
                if (response.isSuccessful()) {
                    Log.e("123", "success");
                    candidates.get(pos).setRemarks(candidates.get(pos).getRemarks() + "\n\n" + remark);
                    adapter.notifyItemChanged(pos);
                } else {
                    CustomToast.makeText(NewCandidateActivity2.this, "Failed to update", 0, Color.RED);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Candidate> call, @NotNull Throwable t) {
                CustomToast.makeText(NewCandidateActivity2.this, "Failed to update", 0, Color.RED);
            }
        });
    }

    public void fetchCandidates() {
        RetroInterface retroInterface = Retrofi.initretro().create(RetroInterface.class);

        Call<List<Candidate>> call = retroInterface.getcandidate();
        call.enqueue(new Callback<List<Candidate>>() {
            @Override
            public void onResponse(Call<List<Candidate>> call, Response<List<Candidate>> response) {
                if (!response.isSuccessful()) {
                    CustomToast.makeText(NewCandidateActivity2.this, "Failed to fetch", 0, Color.parseColor("#32CD32"));
                } else {
                    candidates = response.body();
                    adapter = new NewCandidate2Adapter(NewCandidateActivity2.this, candidates, gi);
                    rv.setLayoutManager(new LinearLayoutManager(NewCandidateActivity2.this));
                    rv.setAdapter(adapter);
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Candidate>> call, Throwable t) {
                CustomToast.makeText(NewCandidateActivity2.this, "Failed to fetch", 0, Color.parseColor("#32CD32"));
            }
        });

    }


}