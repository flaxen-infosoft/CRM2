package com.example.crm.SalesManagement;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Model.Customer;
import com.example.crm.R;
import com.example.crm.Retro.Apicontrollerflaxen;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Historynew extends AppCompatActivity {
    TabLayout tab;
    ArrayList<Customer> f1;
    ArrayList<Customer> f2;
    ArrayList<Customer> followup2;
    ArrayList<Customer> followup3;
    private RecyclerView recyclerView;
    private ArrayList<Customer> followup;
    private ArrayList<Customer> followup1;
   // RecyclerViewAdapterFollowup2 recyclerViewAdapterleads;
    RecyclerViewAdapterHistory recyclerViewAdapterHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historynew);
        tab = findViewById(R.id.tab1);
        recyclerView = findViewById(R.id.rec45);
        followup=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Call<ArrayList<Customer>> call = Apicontrollerflaxen.getInstance().getapi().getleadsCustomer();
        call.enqueue(new Callback<ArrayList<Customer>>() {
            @Override
            public void onResponse(Call<ArrayList<Customer>> call, Response<ArrayList<Customer>> response)
            {
                followup1 = response.body();
                //ArrayList<Customer> leads1=new ArrayList<>();
                for (int i = 0; i < followup1.size(); i++) {
                    if (followup1.get(i).getRemark().toString().equals("Completed")) {
                        followup.add(followup1.get(i));
                    }
                    //  System.out.println(followup.get(i).getName());

                }


                recyclerViewAdapterHistory=new RecyclerViewAdapterHistory(Historynew.this,followup);
                recyclerView.setAdapter(recyclerViewAdapterHistory);

            }

            @Override
            public void onFailure(Call<ArrayList<Customer>> call, Throwable t) {

            }
        });
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            { if(tab.getPosition()==0)
            { recyclerViewAdapterHistory=new RecyclerViewAdapterHistory(Historynew.this,followup);
                recyclerView.setAdapter(recyclerViewAdapterHistory);


            }
            else if(tab.getPosition()==1)
            {
                Call<ArrayList<Customer>> call1 = Apicontrollerflaxen.getInstance().getapi().getleadsCustomer();
                call1.enqueue(new Callback<ArrayList<Customer>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Customer>> call1, Response<ArrayList<Customer>> response) {
                        followup2 = response.body();
                        followup3=new ArrayList<>();
                        //ArrayList<Customer> leads1=new ArrayList<>();
                        for (int i = 0; i < followup2.size(); i++) {
                            if (followup2.get(i).getRemark().toString().equals("Withdraw")) {
                                followup3.add(followup2.get(i));
                            }
                            //  System.out.println(followup.get(i).getName());

                        }


                        recyclerViewAdapterHistory=new RecyclerViewAdapterHistory(Historynew.this,followup3);
                        recyclerView.setAdapter(recyclerViewAdapterHistory);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<Customer>> call1, Throwable t) {

                    }
                });

            }
            else if(tab.getPosition()==2)
            { Call<ArrayList<Customer>> call1 = Apicontrollerflaxen.getInstance().getapi().getleadsCustomer();
                call1.enqueue(new Callback<ArrayList<Customer>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Customer>> call1, Response<ArrayList<Customer>> response) {
                        f1 = response.body();
                        f2=new ArrayList<>();
                        //ArrayList<Customer> leads1=new ArrayList<>();
                        for (int i = 0; i < f1.size(); i++) {
                            if (f1.get(i).getRemark().toString().equals("Deleted")) {
                                f2.add(f1.get(i));
                            }
                            //  System.out.println(followup.get(i).getName());

                        }


                        recyclerViewAdapterHistory=new RecyclerViewAdapterHistory(Historynew.this,f2);
                        recyclerView.setAdapter(recyclerViewAdapterHistory);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<Customer>> call1, Throwable t) {

                    }
                });


            }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


}