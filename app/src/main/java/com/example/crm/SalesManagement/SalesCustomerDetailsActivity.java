package com.example.crm.SalesManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.example.crm.CRMManagement.CustomerDetails;
import com.example.crm.R;

public class SalesCustomerDetailsActivity extends AppCompatActivity {

    CardView cardView;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_customer_details);

        cardView = findViewById(R.id.card);
        textview = findViewById(R.id.menu);

        cardView.setOnClickListener(v -> startActivity(new Intent(SalesCustomerDetailsActivity.this, CustomerDetails.class)));

        textview.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                getMenuInflater().inflate(R.menu.crm_menu, menu);
                return;
            }
        });
    }
}