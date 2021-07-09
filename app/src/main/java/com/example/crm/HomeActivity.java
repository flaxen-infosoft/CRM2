package com.example.crm;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.crm.Fragments.DashboardFragment;
import com.example.crm.HRManagement.CandidateMainPage;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        //This line is to remove the home activity and to direct show the home fragment.
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_frame, new DashboardFragment()).commit();
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);

        navigationView.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.dashboard:
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_frame, new DashboardFragment()).commit();
                    drawer.closeDrawer(GravityCompat.START);
                    break;
                case R.id.hrmanagement:
                    startActivity(new Intent(getApplicationContext(), CandidateMainPage.class));
                    drawer.closeDrawer(GravityCompat.START);
                    break;
                case R.id.logout:
                    SPOps.logout(HomeActivity.this);
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                    drawer.closeDrawer(GravityCompat.START);
                    break;
            }

            return true;
        });
    }
}