package com.example.crm.HRManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.crm.R;

public class LiteratureTestPaper extends AppCompatActivity {

    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_literature_test_paper);

        btn_submit = findViewById(R.id.test_done_btn);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LiteratureTestPaper.this, TestRespoondActivity.class));
            }
        });

    }
}