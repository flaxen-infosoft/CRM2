package com.example.crm.SalesManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.crm.CRMManagement.CustomersDetails;
import com.example.crm.CustomToast;
import com.example.crm.LeaveManagement.RecyclerViewAdapter;
import com.example.crm.Model.Customer;
import com.example.crm.R;
import com.example.crm.Retro.ApiController;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Clientnew extends AppCompatActivity {
     public ArrayList<Customer> clients=new ArrayList<>();
   RecyclerViewAdapterclient recyclerViewAdapterclient;
    RecyclerView recyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientnew);
        recyclerView=findViewById(R.id.rec1);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Call<ArrayList<Customer>> call = ApiController.getInstance().getapi().getAllCustomers();
        call.enqueue(new Callback<ArrayList<Customer>>() {
            @Override
            public void onResponse(Call<ArrayList<Customer>> call, Response<ArrayList<Customer>> response)
            {
                if (response.isSuccessful()) {
                    clients = response.body();
                    System.out.println(clients.get(0).getName());
                    recyclerViewAdapterclient=new RecyclerViewAdapterclient(Clientnew.this,clients);
                    recyclerView.setAdapter(recyclerViewAdapterclient);

                } else {
                    CustomToast.makeText(Clientnew.this,"Failed: " + response.message(), 0, Color.RED);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Customer>> call, Throwable t) {
                Log.d("hellomyname","failed to fetch");
            }
        });
        //System.out.println("hello"+clients.get(0).getName());


    }

}