package com.example.crm.HRManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crm.PaySlipFormActivity;
import com.example.crm.R;

import net.cachapa.expandablelayout.ExpandableLayout;

public class CandidateRemark extends AppCompatActivity {

    Button btnregister;
    Spinner spin_department, spin_status;
    RadioButton job, intern;
    ExpandableLayout expandablemycontent, expandableinterncontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_remark);

        btnregister = findViewById(R.id.candidate_register);
        spin_department = findViewById(R.id.department);
        spin_status = findViewById(R.id.status);

        job = findViewById(R.id.job);
        intern = findViewById(R.id.intern);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Department, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_department.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Status, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_status.setAdapter(adapter1);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CandidateRemark.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showmyinformation(View view){
        expandablemycontent = (ExpandableLayout) findViewById(R.id.mycontent);
        expandablemycontent.toggle();
    }

    public void showjobinformation(View view){
        expandableinterncontent = findViewById(R.id.myjobcontent);
        expandableinterncontent.toggle();
    }
}