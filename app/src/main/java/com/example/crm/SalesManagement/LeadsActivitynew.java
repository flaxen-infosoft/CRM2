package com.example.crm.SalesManagement;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Model.Customer;
import com.example.crm.R;
import com.example.crm.Retro.Apicontrollerflaxen;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeadsActivitynew extends AppCompatActivity {
    MaterialCardView current;
    MaterialCardView notintrested;
   private RecyclerView recyclerView;
   private ArrayList<Customer> leads;
   RecyclerViewAdapterleads recyclerViewAdapterclient;
    ArrayList<Customer> leads1;
@RequiresApi(api = Build.VERSION_CODES.M)
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leads_activitynew);
        recyclerView=findViewById(R.id.recycler2);
    notintrested=findViewById(R.id.notintr);
    current=findViewById(R.id.currenttt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Call<ArrayList<Customer>> call= Apicontrollerflaxen.getInstance().getapi().getleadsCustomer();
        call.enqueue(new Callback<ArrayList<Customer>>() {
            @Override
            public void onResponse(Call<ArrayList<Customer>> call, Response<ArrayList<Customer>> response)
            {leads=response.body();
  leads1=new ArrayList<>();
            for(int i=0;i<leads.size();i++)
            {
                System.out.println(response.body().get(i).getRemark());
                if(leads.get(i).getRemark().equals("Lead"))
                {
                    leads1.add(leads.get(i));
                    System.out.println(leads.get(i).getName());

                }

            }


                recyclerViewAdapterclient= new RecyclerViewAdapterleads(LeadsActivitynew.this,leads1);
                recyclerView.setAdapter(recyclerViewAdapterclient);



            }

            @Override
            public void onFailure(Call<ArrayList<Customer>> call, Throwable t) {

            }
        });
        notintrested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notintrested.setCardBackgroundColor(getColor(R.color.aayushpurple));
                current.setCardBackgroundColor(getColor(R.color.aayushthemepurple));
                Call<ArrayList<Customer>> call1= Apicontrollerflaxen.getInstance().getapi().getleadsCustomer();
                call1.enqueue(new Callback<ArrayList<Customer>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Customer>> call1, Response<ArrayList<Customer>> response)
                    {leads=response.body();

                        ArrayList<Customer> leads1=new ArrayList<>();
                        for(int i=0;i<leads.size();i++)
                        {
                            if(leads.get(i).getRemark().toString().equals("Not Intrested"))
                            {
                                leads1.add(leads.get(i));
                            }

                        }

                        recyclerViewAdapterclient= new RecyclerViewAdapterleads(LeadsActivitynew.this,leads1);
                        recyclerView.setAdapter(recyclerViewAdapterclient);



                    }

                    @Override
                    public void onFailure(Call<ArrayList<Customer>> call1, Throwable t) {

                    }
                });

            }
        });
        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notintrested.setCardBackgroundColor(getColor(R.color.aayushthemepurple));
                current.setCardBackgroundColor(getColor(R.color.aayushpurple));
                Call<ArrayList<Customer>> call2= Apicontrollerflaxen.getInstance().getapi().getleadsCustomer();
                call2.enqueue(new Callback<ArrayList<Customer>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Customer>> call2, Response<ArrayList<Customer>> response)
                    {leads=response.body();
                        ArrayList<Customer> leads1=new ArrayList<>();
                        for(int i=0;i<leads.size();i++)
                        {
                            if(leads.get(i).getRemark().toString().equals("Lead"))
                            {
                                leads1.add(leads.get(i));
                            }

                        }

                        recyclerViewAdapterclient= new RecyclerViewAdapterleads(LeadsActivitynew.this,leads1);
                        recyclerView.setAdapter(recyclerViewAdapterclient);



                    }

                    @Override
                    public void onFailure(Call<ArrayList<Customer>> call2, Throwable t) {

                    }
                });

            }
        });

    }






}