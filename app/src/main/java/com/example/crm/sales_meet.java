package com.example.crm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.example.crm.HRManagement.NewCandidateActivity;
import com.example.crm.LeaveManagement.LeaveManagementActivity;
import com.example.crm.Model.Employee;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;
import com.example.crm.SalesManagement.new_sales_dashborad;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sales_meet extends AppCompatActivity {
    CardView onfiledmeet,onhousemeet;
    ExtendedFloatingActionButton fab;
    AlertDialog.Builder builderSingle;
    ProgressBar progressBar;
    View contentview;
   public  alertdialong_adapter adapter;
    RecyclerView recyclerView;
  public  ArrayList<leadsname> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_meet);
        initUI();
      //  getthdata();
        onfabclick();
    }

    private void onfabclick() {
        fab.setOnClickListener(v -> {
            builderSingle = new AlertDialog.Builder(this);
            builderSingle.setTitle("Select your client bro");
            LayoutInflater inflater = getLayoutInflater();
            View convertView = (View) inflater.inflate(R.layout.customalertdialog, null);
            builderSingle.setView(convertView);
            progressBar  = convertView.findViewById(R.id.progress_alert);
            recyclerView= convertView.findViewById(R.id.Countrylist);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            getthdata();
           // recyclerView.setHasFixedSize(true);
           // adapter = new alertdialong_adapter(arrayList);
            //recyclerView.setAdapter(adapter);

            builderSingle.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builderSingle.show();
        });
    }

    private  synchronized void getthdata() {
        RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
        Gson gson = new Gson();
        Call<ArrayList<leadsname>> call = ri.getleads();
        call.enqueue(new Callback<ArrayList<leadsname>>() {
            @Override
            public void onResponse(Call<ArrayList<leadsname>> call, Response<ArrayList<leadsname>> response) {
                if (response.isSuccessful()) {
                    arrayList = new ArrayList<>();
                    /*for(int i=0; i<response.body().size();i++){
                        if(response.body().get(i).getRemark().equals("followup")){
                         leadsname add1  = response.body().get(i);
                            arrayList.add(add1);
                        }
                    }*/
                    arrayList = response.body();
                    System.out.println(arrayList);
                    adapter = new alertdialong_adapter(arrayList,builderSingle);
                    recyclerView.setAdapter(adapter);

                } else
                    CustomToast.makeText(sales_meet.this, "Failed to load"+response.code(), 0, Color.RED);
            }

            @Override
            public void onFailure(Call<ArrayList<leadsname>> call, Throwable t) {

            }
        });
    }

    private void initUI() {
        onfiledmeet=findViewById(R.id.onfiledmeet);
        onhousemeet=findViewById(R.id.inhousemeet);
        fab= findViewById(R.id.fabbutton);
    }

}