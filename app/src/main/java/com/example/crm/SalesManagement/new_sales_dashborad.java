package com.example.crm.SalesManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.example.crm.HRManagement.CandidateMainPage;
import com.example.crm.HRManagement.NewCandidateActivity;
import com.example.crm.R;
import com.example.crm.sales_meet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class new_sales_dashborad extends AppCompatActivity {
    CardView leads,reporting,meeting,history,followup,client,leave,webmail;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sales_dashborad);
        Initui();
        onclick();

    }


    private void Initui() {

        leads = findViewById(R.id.leadscard);
        followup = findViewById(R.id.followupcard);
        client   = findViewById(R.id.clientcard);
        meeting = findViewById(R.id.Meetingcard);
        history = findViewById(R.id.Historycard);
        webmail = findViewById(R.id.webmailcard);
        leave = findViewById(R.id.leadscard);
        reporting= findViewById(R.id.reportingcard);
    }
    private void onclick() {
        leads.setOnClickListener(v -> {
            Intent intent = new Intent(new_sales_dashborad.this, NewCandidateActivity.class);
            startActivity(intent);
        });
        reporting.setOnClickListener(v -> {
            Intent intent = new Intent(new_sales_dashborad.this, NewCandidateActivity.class);
            startActivity(intent);
        });
        webmail.setOnClickListener(v -> {
            Intent intent = new Intent(new_sales_dashborad.this, NewCandidateActivity.class);
            startActivity(intent);
        });
        meeting.setOnClickListener(v -> {
            Intent intent = new Intent(new_sales_dashborad.this, sales_meet.class);
            startActivity(intent);
        });
        client.setOnClickListener(v -> {
            Intent intent = new Intent(new_sales_dashborad.this, NewCandidateActivity.class);
            startActivity(intent);
        });
        followup.setOnClickListener(v -> {
            Intent intent = new Intent(new_sales_dashborad.this, NewCandidateActivity.class);
            startActivity(intent);
        });
        history.setOnClickListener(v -> {
            Intent intent = new Intent(new_sales_dashborad.this, NewCandidateActivity.class);
            startActivity(intent);
        });
        leave.setOnClickListener(v -> {
            Intent intent = new Intent(new_sales_dashborad.this, NewCandidateActivity.class);
            startActivity(intent);
        });
    }
}