package com.example.crm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaySlipSecondActivity extends AppCompatActivity {

    Button btnnext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_slip_second);

        btnnext = findViewById(R.id.slip_submit);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaySlipSecondActivity.this, NewProposalActivity.class);
                startActivity(intent);
            }
        });

    }
}