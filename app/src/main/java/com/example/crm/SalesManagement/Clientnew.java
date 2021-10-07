package com.example.crm.SalesManagement;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Model.Customer;
import com.example.crm.R;
import com.example.crm.Retro.Apicontrollerflaxen;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Clientnew extends AppCompatActivity {
     public ArrayList<Customer> clients=new ArrayList<>();
   RecyclerViewAdapterclient recyclerViewAdapterclient;
    RecyclerView recyclerView;
    private ArrayList<Customer> followup1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientnew);
        recyclerView=findViewById(R.id.rec1);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Customer> follow;
       ArrayList<Customer> followup = new ArrayList<>();
        Call<ArrayList<Customer>> call = Apicontrollerflaxen.getInstance().getapi().getleadsCustomer();
        call.enqueue(new Callback<ArrayList<Customer>>() {
            @Override
            public void onResponse(Call<ArrayList<Customer>> call, Response<ArrayList<Customer>> response) {
                followup1 = response.body();
                //ArrayList<Customer> leads1=new ArrayList<>();
                for (int i = 0; i < followup1.size(); i++) {
                    if (followup1.get(i).getRemark().toString().equals("Client")) {
                        followup.add(followup1.get(i));
                       //  System.out.println(followup.get(i).getInvoice());
                    }
                    //  System.out.println(followup.get(i).getName());

                }


               recyclerViewAdapterclient=new RecyclerViewAdapterclient(Clientnew.this,followup);
                recyclerView.setAdapter(recyclerViewAdapterclient);

            }

            @Override
            public void onFailure(Call<ArrayList<Customer>> call, Throwable t)
            {

            }
        });

        //System.out.println("hello"+clients.get(0).getName());


    }

}