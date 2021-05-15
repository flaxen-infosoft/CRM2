package com.example.crm.SalesManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.crm.R;
import com.google.android.material.navigation.NavigationView;

public class SalesMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    CardView crm_card, task, reports, leaves;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.sales_nav_view);
        toolbar = findViewById(R.id.sales_toolbar);
        crm_card = findViewById(R.id.sales_crm);
        task = findViewById(R.id.sales_task);
        reports = findViewById(R.id.sales_report);
        leaves = findViewById(R.id.sales_leave);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation__drawer_open, R.string.navigation_drawer__close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_dashboard);

        crm_card.setOnClickListener(v -> startActivity(new Intent(SalesMainActivity.this, SalesCustomerDetailsActivity.class)));

        task.setOnClickListener(v -> startActivity(new Intent(SalesMainActivity.this, SalesTaskActivity.class)));

        reports.setOnClickListener(v -> startActivity(new Intent(SalesMainActivity.this, SalesReportsActivity.class )));

        leaves.setOnClickListener(v -> startActivity(new Intent(SalesMainActivity.this, LeaveActivity.class)));

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_dashboard :
                break;

            case R.id.nav_customer_management :
                Intent intent = new Intent(SalesMainActivity.this, Sales_CRM_Activity.class);
                startActivity(intent);
                break;

            case R.id.nav_leave :
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}