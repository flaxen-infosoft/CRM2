package com.example.crm.HRManagement;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.CustomProgressAlert;
import com.example.crm.Model.Candidate;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewCandidateActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressDialog loading;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_candidate);

        back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            finish();
        });
        loading = CustomProgressAlert.make(this, "Loading...");
        loading.show();
        recyclerView = findViewById(R.id.new_registerrecyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(NewCandidateActivity.this));
        RetroInterface retroInterface = Retrofi.initretro().create(RetroInterface.class);
        Call<List<Candidate>> candidateCall = retroInterface.getcandidate();
        candidateCall.enqueue(new Callback<List<Candidate>>() {
            @Override
            public void onResponse(Call<List<Candidate>> call, Response<List<Candidate>> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.code());
                } else {
                    List<Candidate> candidateList;
                    candidateList = response.body();
                    if (candidateList == null)
                        candidateList = new ArrayList<>();
                    NewCandidateAdapter newCandidateAdapter = new NewCandidateAdapter(NewCandidateActivity.this, candidateList);
                    recyclerView.setAdapter(newCandidateAdapter);
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Candidate>> call, Throwable t) {
                loading.dismiss();
            }


        });
    }
}