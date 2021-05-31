package com.example.crm.HRManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.crm.R;

public class NewCandidateActivity extends AppCompatActivity {
    RecyclerView recyclerView;
;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_candidate);
recyclerView=findViewById(R.id.new_registerrecyler);
recyclerView.setLayoutManager(new LinearLayoutManager(NewCandidateActivity.this));
        RetroInterface retroInterface = Retrofi.initretro().create(RetroInterface.class);
        Call<List<Candidate>> candidateCall= retroInterface.getcandidate();
       candidateCall.enqueue(new Callback<List<Candidate>>() {
           @Override
           public void onResponse(Call<List<Candidate>> call, Response<List<Candidate>> response) {
               if (!response.isSuccessful()) {
                   System.out.println(response.code());
               }
               List<Candidate> candidateList= response.body();
               NewCandidateAdapter newCandidateAdapter=new NewCandidateAdapter(NewCandidateActivity.this,candidateList);
               recyclerView.setAdapter(newCandidateAdapter);
           }

        cardView = findViewById(R.id.card1);


    }
}