package com.example.crm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Model.Customer;
import com.example.crm.Retro.Apicontrollerflaxen;
import com.example.crm.SalesManagement.RecyclerViewAdapterPayment2;
import com.example.crm.SalesManagement.RecyclerViewAdaptergetPayment;
import com.example.crm.SalesManagement.Recyclerviewadapterreportdetail;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportDetailnew extends AppCompatActivity {
    RecyclerView recyclerView;
    Recyclerviewadapterreportdetail recyclerviewadapterreportdetail;
    TabLayout tab;
    ArrayList<Customer> customers;
    ArrayList<Customer> clients;
    ArrayList<Customer> fullarray;
    String date;
    ArrayList<Customer> payment;
RecyclerViewAdaptergetPayment recyclerViewAdaptergetPayment;
RecyclerViewAdapterPayment2 recyclerViewAdapterPayment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detailnew);
        recyclerView=findViewById(R.id.recdetail);
        tab=findViewById(R.id.tab22);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//clients=new ArrayList<>();
//customers=new ArrayList<>();
fullarray=new ArrayList<>();
    date=getIntent().getStringExtra("date");
        Call<ArrayList<Customer>> call= Apicontrollerflaxen.getInstance().getapi().getcallbydate(1,date);
        call.enqueue(new Callback<ArrayList<Customer>>() {
            @Override
            public void onResponse(Call<ArrayList<Customer>> call, Response<ArrayList<Customer>> response)
            {customers=response.body();
                for(Customer c:customers)
                {
                    fullarray.add(c);
                }
                System.out.println(fullarray.get(0).getName());
            }

            @Override
            public void onFailure(Call<ArrayList<Customer>> call, Throwable t)
            {
                System.out.println(t);
            }
        });
        Call<ArrayList<Customer>> call22=Apicontrollerflaxen.getInstance().getapi().getclientcallbydate(1,date);
        call22.enqueue(new Callback<ArrayList<Customer>>() {
            @Override
            public void onResponse(Call<ArrayList<Customer>> call, Response<ArrayList<Customer>> response)
            {clients=response.body();
                for(Customer c:clients)
                {
                    fullarray.add(c);
                }
                recyclerviewadapterreportdetail=new Recyclerviewadapterreportdetail(ReportDetailnew.this, fullarray);


                recyclerView.setAdapter(recyclerviewadapterreportdetail);

            }

            @Override
            public void onFailure(Call<ArrayList<Customer>> call, Throwable t) {

            }
        });

//        for(int i=0;i<clients.size();i++)
//        {
//            fullarray.add(clients.get(i));
//        }
//        System.out.println(fullarray.get(0).getName());

tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int i = tab.getPosition();
        if (i == 0)
        {
            recyclerviewadapterreportdetail = new Recyclerviewadapterreportdetail(ReportDetailnew.this, fullarray);


            recyclerView.setAdapter(recyclerviewadapterreportdetail);
        } else if (i == 1)
        {

        }
        else if (i == 2)
        {
            try {


                Call<ArrayList<Customer>> call4 = Apicontrollerflaxen.getInstance().getapi().getpaymentbydate(getIntent().getStringExtra("date"));
                call4.enqueue(new Callback<ArrayList<Customer>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Customer>> call, Response<ArrayList<Customer>> response)
                    {
                       // System.out.println(response.body().get(0).getAmount_paid());

                            recyclerViewAdaptergetPayment = new RecyclerViewAdaptergetPayment(ReportDetailnew.this, response.body());
                            recyclerView.setAdapter(recyclerViewAdaptergetPayment);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<Customer>> call, Throwable t) {
                        System.out.println(t);

                    }
                });
            } catch (Exception e) {
                System.out.println("hi");
            }


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