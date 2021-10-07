package com.example.crm;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.crm.Model.Customer;
import com.example.crm.Retro.Apicontrollerflaxen;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportFabnew extends AppCompatActivity
{ArrayList<Customer> leads;
MaterialCardView callbutton,paymentbutton;
EditText amount,mode,duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_fabnew);
        callbutton=findViewById(R.id.callbuttoncard);
        paymentbutton=findViewById(R.id.paymentcard);


        callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            { AlertDialog.Builder builder=new AlertDialog.Builder(ReportFabnew.this);
                View view1=getLayoutInflater().inflate(R.layout.callformnew,null);
                builder.setTitle("Fill info");
                duration=view1.findViewById(R.id.editTextTextPersonName0);
                Spinner spinner=view1.findViewById(R.id.spinner56);
                Call<ArrayList<Customer>> call= Apicontrollerflaxen.getInstance().getapi().getleadsCustomer();
                call.enqueue(new Callback<ArrayList<Customer>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Customer>> call, Response<ArrayList<Customer>> response)
                    {leads=response.body();
                        ArrayList<String> leads1=new ArrayList<>();
                        for(int i=0;i<leads.size();i++)
                        {

                            leads1.add(leads.get(i).getName());
                            System.out.println(leads1.get(i));

                        }


                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ReportFabnew.this, android.R.layout.simple_spinner_item, leads1);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);


                    }

                    @Override
                    public void onFailure(Call<ArrayList<Customer>> call, Throwable t) {

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    { dialogInterface.dismiss();

                    }
                });
                builder.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        Call<JsonObject> call5=Apicontrollerflaxen.getInstance().getapi().insertcall(100,getIntent().getIntExtra("empid",0),duration.getText().toString());
                        call5.enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response)
                            {
                                Toast.makeText(ReportFabnew.this,"success",Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {

                            }
                        });

                    }
                });
                builder.setView(view1);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }
        });
        paymentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder builder=new AlertDialog.Builder(ReportFabnew.this);
                View view1=getLayoutInflater().inflate(R.layout.paymentalertdialog,null);
                amount=view1.findViewById(R.id.editTextTextPersonName4);
                mode=view1.findViewById(R.id.editTextTextPersonName5);

                builder.setTitle("Fill info");
                Spinner spinner=view1.findViewById(R.id.spinner5);
                Call<ArrayList<Customer>> call= Apicontrollerflaxen.getInstance().getapi().getleadsCustomer();
                call.enqueue(new Callback<ArrayList<Customer>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Customer>> call, Response<ArrayList<Customer>> response)
                    {leads=response.body();
                        ArrayList<String> leads1=new ArrayList<>();
                        for(int i=0;i<leads.size();i++)
                        {

                            leads1.add(leads.get(i).getName());
                            System.out.println(leads1.get(i));

                        }


                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ReportFabnew.this, android.R.layout.simple_spinner_item, leads1);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);


                    }

                    @Override
                    public void onFailure(Call<ArrayList<Customer>> call, Throwable t) {

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    { dialogInterface.dismiss();

                    }

                });
                builder.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        System.out.println(amount.getText());
                        Call<JsonObject> call3=Apicontrollerflaxen.getInstance().getapi().insertpayment(2,5,amount.getText().toString(),mode.getText().toString());
                    call3.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response)
                        {   System.out.println(amount.getText());
                            Toast.makeText(ReportFabnew.this,"success",Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });

                    }
                });
                builder.setView(view1);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }

            });

    }


}