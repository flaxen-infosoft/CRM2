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

    Button btnregister,dateofinterviewbt;
    EditText collagename,Duratuion,stipendamount,remarks;
    Spinner spin_department, spin_status;
    RadioButton job, intern,laptopyes,laptopno,stipendyes,stipendno,fresher,experience;
    ExpandableLayout expandablemycontent, expandableinterncontent;
String id,startdate,appliedfor,havelaptop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_remark);

        btnregister = findViewById(R.id.candidate_register);
        spin_department = findViewById(R.id.department);
        spin_status = findViewById(R.id.status);
        dateofinterviewbt = findViewById(R.id.candidate_remark_dateofinterview);
        laptopyes = findViewById(R.id.yes);
        laptopno = findViewById(R.id.no);
        job = findViewById(R.id.job);
        intern = findViewById(R.id.intern);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Department, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_department.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Status, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_status.setAdapter(adapter1);
        dateofinterviewbt.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(CandidateRemark.this, (datePicker, i, i1, i2) -> {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yyyy");
                Calendar calendar = Calendar.getInstance();
                calendar.set(i, i1, i2);
                startdate = sdf.format(calendar.getTime());
                dateofinterviewbt.setText(startdate);
            }, Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            datePickerDialog.setTitle("start date");
            datePickerDialog.show();
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (job.isChecked()) {
                    appliedfor = "job";
                } else {
                    appliedfor = "internship";
                }
                if (laptopyes.isChecked()) {
                    havelaptop = "yes";
                } else {
                    havelaptop = "no";
                }
                RetroInterface retroInterface = Retrofi.initretro().create(RetroInterface.class);
                Candidate candidate= new Candidate();
                candidate.setId(id);
                candidate.setHave_laptop(havelaptop);
                candidate.setApplied_for(appliedfor);
                candidate.setDateof_interview(startdate);
                Call<Candidate> call=retroInterface.uodateCandidate(candidate);
                call.enqueue(new Callback<Candidate>() {
                    @Override
                    public void onResponse(Call<Candidate> call, Response<Candidate> response) {
                        if (!response.isSuccessful()) {
                            System.out.println(response.code());
                        }
                        CustomToast.makeText(CandidateRemark.this,"Details Updated",0, Color.parseColor("#32CD32"));
                    }

                    @Override
                    public void onFailure(Call<Candidate> call, Throwable t) {
                        System.out.println(t.getMessage());
                        CustomToast.makeText(CandidateRemark.this, "Please Try Again", 0, Color.RED);
                    }
                });
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