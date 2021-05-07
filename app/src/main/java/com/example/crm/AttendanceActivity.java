package com.example.crm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

public class AttendanceActivity extends AppCompatActivity {
CardView cardView1,cardView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        cardView1=findViewById(R.id.cardview1);
        cardView2=findViewById(R.id.cardview2);
        cardView1.setOnClickListener(view -> {
            startActivity(new Intent(AttendanceActivity.this,ReportingtimeActivity.class));
        });
        cardView2.setOnClickListener(view -> {
            startActivity(new Intent(AttendanceActivity.this,ReportingtimeActivity.class));
        });
    }
}