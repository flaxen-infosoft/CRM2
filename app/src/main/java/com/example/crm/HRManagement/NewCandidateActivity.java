package com.example.crm.HRManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;

public class NewCandidateActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_candidate);
recyclerView=findViewById(R.id.new_registerrecyler);
recyclerView.setLayoutManager(new LinearLayoutManager(NewCandidateActivity.this));
        RetroInterface retroInterface = Retrofi.initretro().create(RetroInterface.class);
//        NewCandidateAdapter newCandidateAdapter=new NewCandidateAdapter(NewCandidateActivity.this,)

    }
}