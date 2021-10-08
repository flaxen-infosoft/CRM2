package com.example.crm.SalesManagement;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.crm.R;
import com.google.android.material.card.MaterialCardView;

public class Meetingnew extends AppCompatActivity {
MaterialCardView ongoing,infuture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetingnew);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void completedclick(View view)
    {ongoing=findViewById(R.id.currenttt);
        infuture=findViewById(R.id.notintr);
        infuture.setCardBackgroundColor(getColor(R.color.aayushthemepurple));
        ongoing.setCardBackgroundColor(getColor(R.color.aayushpurple));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void withdrawclick(View view)
    {ongoing=findViewById(R.id.currenttt);
        infuture=findViewById(R.id.notintr);
        infuture.setCardBackgroundColor(getColor(R.color.aayushpurple));
       ongoing.setCardBackgroundColor(getColor(R.color.aayushthemepurple));
     }
}