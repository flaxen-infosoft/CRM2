package com.example.crm.CRMManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.crm.CustomerRegisteration;
import com.example.crm.R;

public class CRMDashboardActivity extends AppCompatActivity {

    CardView register, manage, upcoming, find_customer, task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_r_m_dashboard);

        register = findViewById(R.id.crm_register);
        manage = findViewById(R.id.manage);
        upcoming = findViewById(R.id.upcomming);
        find_customer = findViewById(R.id.find_customer);
        task = findViewById(R.id.task_details);

        register.setOnClickListener(v -> startActivity(new Intent(CRMDashboardActivity.this, CRM_RegisterActivity.class)));

        manage.setOnClickListener(v -> startActivity(new Intent(CRMDashboardActivity.this, CustomersDetails.class)));

        upcoming.setOnClickListener(v -> startActivity(new Intent(CRMDashboardActivity.this, UpcomingSalesActivity.class)));
    }
}