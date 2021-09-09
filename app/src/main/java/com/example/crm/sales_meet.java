package com.example.crm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.example.crm.HRManagement.NewCandidateActivity;
import com.example.crm.SalesManagement.new_sales_dashborad;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class sales_meet extends AppCompatActivity {
    CardView onfiledmeet,onhousemeet;
    FloatingActionButton fab;
    AlertDialog.Builder builderSingle;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    ArrayList<leadsname> arrayList;
    alertdialong_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_meet);
        initUI();
        onfabclick();
    }

    private void onfabclick() {
        fab.setOnClickListener(v -> {
            builderSingle = new AlertDialog.Builder(this);
            builderSingle.setTitle("Select your Country");
            LayoutInflater inflater = getLayoutInflater();
            View convertView = (View) inflater.inflate(R.layout.customalertdialog, null);
            builderSingle.setView(convertView);
            progressBar  = convertView.findViewById(R.id.progress_alert);
            recyclerView= convertView.findViewById(R.id.Countrylist);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);
            adapter = new alertdialong_adapter(arrayList);
            getthdata();
            recyclerView.setAdapter(adapter);
            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builderSingle.show();
        });
    }

    private void getthdata() {

    }

    private void initUI() {
        onfiledmeet=findViewById(R.id.onfiledmeet);
        onhousemeet=findViewById(R.id.inhousemeet);
        fab= findViewById(R.id.fabbutton);
    }

}