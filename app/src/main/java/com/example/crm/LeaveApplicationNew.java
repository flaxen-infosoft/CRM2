package com.example.crm;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.crm.Retro.Apicontrollerflaxen;
import com.google.gson.JsonObject;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveApplicationNew extends AppCompatActivity {
EditText editText,message;
TextView textView1,textView2;
    Spinner spinner;
    Button apply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_application_new);
apply=findViewById(R.id.button);
message=findViewById(R.id.editTextTextPersonName2);
        editText=findViewById(R.id.editTextTextPersonName3);
        editText.setText("");
textView2=findViewById(R.id.textView32);
        spinner = (Spinner) findViewById(R.id.spinner4);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        textView1=findViewById(R.id.textView33);
        spinner.setAdapter(adapter);
        if(spinner.getSelectedItem()!=null)
        {textView1.setText("");
        }
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            { if(textView2.getText().equals("")&&textView1.getText().equals("")&&!(message.getText().toString().isEmpty()))
            {
                Call<JsonObject> call= Apicontrollerflaxen.getInstance().getapi().insertleave(1,editText.getText().toString(),spinner.getSelectedItem().toString(),message.getText().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Toast.makeText(LeaveApplicationNew.this,"success",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LeaveApplicationNew.this,SalesDashboardnew.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t)
                    {
                        System.out.println("failed");

                    }
                });

            }
            else {
                Toast.makeText(LeaveApplicationNew.this,"FILLING ALL FIELDS IS MANDATORY",Toast.LENGTH_SHORT).show();
            }

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        spinner=findViewById(R.id.spinner4);
        editText=findViewById(R.id.editTextTextPersonName3);
        editText.setText("");

        if(spinner.getSelectedItem()!=null)
        {textView1.setText("");
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        spinner=findViewById(R.id.spinner4);
        editText=findViewById(R.id.editTextTextPersonName3);
        editText.setText("");
        if(spinner.getSelectedItem()!=null)
        {textView1.setText("");

        }

    }

    public void calenderrr(View view)
    {editText=findViewById(R.id.editTextTextPersonName3);

        Calendar mcurrentDate=Calendar.getInstance();
        int year = mcurrentDate.get(Calendar.YEAR);
        int month = mcurrentDate.get(Calendar.MONTH);
        int day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker=new DatePickerDialog(LeaveApplicationNew.this, new DatePickerDialog.OnDateSetListener()
        {
            public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
                // TODO Auto-generated method stub
                /*      Your code   to get date and time    */

                textView2.setText("");
                System.out.println(selectedYear);

            }
        },year, month, day);
        editText.setText(year + "/" + month + "/" + day);
        mDatePicker.setTitle("Select date");
        mDatePicker.show();
    }

        }
