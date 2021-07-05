package com.example.crm.CRMManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crm.Model.UpcomingCustomer;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;
import com.example.crm.UpcomingAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingSalesActivity extends AppCompatActivity {
RecyclerView recyclerView;
    List<UpcomingCustomer> upcomingCustomerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_sales);
        recyclerView= findViewById(R.id.upcomingrecyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(UpcomingSalesActivity.this));
        fetchupcoming();
    }

    private void fetchupcoming() {
        RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
        Call<UpcomingCustomer> call = ri.getAllUpcomingCustomer();
        call.enqueue(new Callback<UpcomingCustomer>() {
            @Override
            public void onResponse(Call<UpcomingCustomer> call, Response<UpcomingCustomer> response) {
                if (!response.isSuccessful()){
                    System.out.println("response = " + response.code());
                }
                upcomingCustomerList= (List<UpcomingCustomer>) response.body();
                UpcomingAdapter upcomingAdapter= new UpcomingAdapter(upcomingCustomerList);
                recyclerView.setAdapter(upcomingAdapter);

            }

            @Override
            public void onFailure(Call<UpcomingCustomer> call, Throwable t) {
                System.out.println("t = " + t.getMessage());
            }
        });
    }

}