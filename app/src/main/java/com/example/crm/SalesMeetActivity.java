package com.example.crm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Model.Customer;
import com.example.crm.Retro.Apicontrollerflaxen;
import com.example.crm.SalesManagement.RecyclerViewAdaptermeetingnew;
import com.example.crm.SalesManagement.RetroInterface;
import com.example.crm.SalesManagement.SalesMeetingAdapter;
import com.example.crm.SalesManagement.model.ClientListItem;
import com.example.crm.SalesManagement.model.Meeting;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonArray;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesMeetActivity extends AppCompatActivity {
    FloatingActionButton fab;
    ArrayList<Meeting> meetings;
    SalesMeetingAdapter adapter;
    RecyclerView recyclerView;
    RetroInterface retrofi;
    ArrayList<ClientListItem> clients;
    ArrayList<Customer> leads;
    ArrayList<Customer> followup1;
    ArrayList<Customer> cc;
    ArrayList<String> leads1;
    RecyclerViewAdaptermeetingnew recyclerViewAdaptermeetingnew;
    ArrayList<Customer> customers;
    ArrayList<Customer> arr;
ArrayList<Customer> clients1;
    ArrayList<Customer> meetingarray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_meet);
        fab=findViewById(R.id.fabbutton);
        cc=new ArrayList<>();
        leads1=new ArrayList<>();
        arr=new ArrayList<>();
        clients1=new ArrayList<>();
        customers=new ArrayList<>();
        meetingarray=new ArrayList<>();


        recyclerView=findViewById(R.id.meeting_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Call<ArrayList<Customer>> call7=Apicontrollerflaxen.getInstance().getapi().getmeeting();
        call7.enqueue(new Callback<ArrayList<Customer>>() {
            @Override
            public void onResponse(Call<ArrayList<Customer>> call, Response<ArrayList<Customer>> response)
            {
               // System.out.println(response.body().get(0).getName());
                recyclerViewAdaptermeetingnew=new RecyclerViewAdaptermeetingnew(SalesMeetActivity.this,response.body());
              recyclerView.setAdapter(recyclerViewAdaptermeetingnew);
            }

            @Override
            public void onFailure(Call<ArrayList<Customer>> call, Throwable t) {

            }
        });



    }

    public void fabclickmeeting(View view)
    {
        fab=findViewById(R.id.fabbutton);
        cc=new ArrayList<>();

        leads1=new ArrayList<>();

                AlertDialog.Builder builder=new AlertDialog.Builder(SalesMeetActivity.this);
                builder.setTitle("SELECT YOUR CLIENT");
                View v= getLayoutInflater().inflate(R.layout.meetingclientcardnew,null);
                builder.setView(v);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
                SearchableSpinner searchableSpinner=v.findViewById(R.id.searchablespinner);
                MaterialCardView schedule=v.findViewById(R.id.schedulecard);
                //called apis
                Call<ArrayList<Customer>> call= Apicontrollerflaxen.getInstance().getapi().getleads();
                call.enqueue(new Callback<ArrayList<Customer>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Customer>> call, Response<ArrayList<Customer>> response)
                    {leads=response.body();

                        leads1=new ArrayList<>();
                        for(int i=0;i<leads.size();i++)
                        { cc.add(leads.get(i));

                            leads1.add(leads.get(i).getName());
                            //System.out.println(leads1.get(i));

                        }





                    }

                    @Override
                    public void onFailure(Call<ArrayList<Customer>> call, Throwable t) {

                    }


                });
                Call<ArrayList<Customer>> call4 = Apicontrollerflaxen.getInstance().getapi().getleadsCustomer();
                call4.enqueue(new Callback<ArrayList<Customer>>()
                {
                    @Override
                    public void onResponse(Call<ArrayList<Customer>> call4, Response<ArrayList<Customer>> response) {
                        followup1 = response.body();

                        for (int i = 0; i < followup1.size(); i++) {
                            cc.add(followup1.get(i));
                            leads1.add(followup1.get(i).getName());


                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SalesMeetActivity.this, android.R.layout.simple_spinner_item, leads1);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        searchableSpinner.setAdapter(adapter);


                    }

                    @Override
                    public void onFailure(Call<ArrayList<Customer>> call, Throwable t)
                    {

                    }
                });
                schedule.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {Call<JsonArray> call4=Apicontrollerflaxen.getInstance().getapi().insertmeeting(1,cc.get(searchableSpinner.getSelectedItemPosition()).getId(),cc.get(searchableSpinner.getSelectedItemPosition()).getName().toString(),"hello sparsh bhaiyya");
                        call4.enqueue(new Callback<JsonArray>() {
                            @Override
                            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                                Toast.makeText(SalesMeetActivity.this, "success", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(SalesMeetActivity.this, SalesMeetActivity.class);
                                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent1);
                                alertDialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<JsonArray> call, Throwable t)
                            {
                                System.out.println(t);

                            }
                        });
                    }
                });






    }
}

