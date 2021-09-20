package com.example.crm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class LeaveApplicationNew extends AppCompatActivity {
EditText editText;
TextView textView1;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_application_new);

        editText=findViewById(R.id.editTextTextPersonName3);
        editText.setText("");

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
        TextView textView=findViewById(R.id.textView32);
        Calendar mcurrentDate=Calendar.getInstance();
        int year = mcurrentDate.get(Calendar.YEAR);
        int month = mcurrentDate.get(Calendar.MONTH);
        int day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker=new DatePickerDialog(LeaveApplicationNew.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
                // TODO Auto-generated method stub
                /*      Your code   to get date and time    */
                editText.setText(selectedMonth + "/" + selectedDay + "/" + selectedYear);
                textView.setText("");


            }
        },year, month, day);
        mDatePicker.setTitle("Select date");
        mDatePicker.show();
    }

        }
