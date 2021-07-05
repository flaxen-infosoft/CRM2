package com.example.crm.CRMManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.example.crm.CustomToast;
import com.example.crm.Model.Customer;
import com.example.crm.Model.UpcomingCustomer;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerDetails extends AppCompatActivity {
Button upcoming;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        upcoming= findViewById(R.id.bt_addupcoming);
        upcoming.setOnClickListener(view -> {
            Customer customer= (Customer) getIntent().getSerializableExtra("customer");
            UpcomingCustomer upcomingCustomer= (UpcomingCustomer) customer;
            addUpcoming(upcomingCustomer);
        });
    }

    private void addUpcoming(UpcomingCustomer upcomingCustomer) {
        RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
        Call<UpcomingCustomer> call= ri.insertupcoming(upcomingCustomer);
        call.enqueue(new Callback<UpcomingCustomer>() {
            @Override
            public void onResponse(Call<UpcomingCustomer> call, Response<UpcomingCustomer> response) {
                if (!response.isSuccessful()){
                    System.out.println("response = " + response.code());
                }
                CustomToast.makeText(CustomerDetails.this,"Added to Upcoming Customer",0, Color.parseColor("#32cd32"));
            }

            @Override
            public void onFailure(Call<UpcomingCustomer> call, Throwable t) {
                System.out.println("t = " + t.getMessage());
            }
        });
    }
}