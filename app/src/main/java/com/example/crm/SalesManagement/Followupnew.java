package com.example.crm.SalesManagement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.CustomToast;
import com.example.crm.Model.Customer;
import com.example.crm.R;
import com.example.crm.RecyclerViewAdapterFollowup2;
import com.example.crm.Retro.Apicontrollerflaxen;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Followupnew extends AppCompatActivity {
    MaterialCardView ongoing;
    MaterialCardView infuture;
    TabLayout tab;
    ArrayList<Customer> f1;
    ArrayList<Customer> f2;
    ArrayList<Customer> followup2;
    ArrayList<Customer> followup3;
    private RecyclerView recyclerView;
    private ArrayList<Customer> followup;
    private ArrayList<Customer> followup1;
    RecyclerViewAdapterFollowup2 recyclerViewAdapterleads;
    RecyclerViewAdapterFollowup recyclerViewAdapterfollowup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followupnew);
        tab = findViewById(R.id.tab);
        recyclerView = findViewById(R.id.recycler69);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Customer> follow;
        followup = new ArrayList<>();
        Call<ArrayList<Customer>> call = Apicontrollerflaxen.getInstance().getapi().getleadsCustomer();
        call.enqueue(new Callback<ArrayList<Customer>>() {
            @Override
            public void onResponse(Call<ArrayList<Customer>> call, Response<ArrayList<Customer>> response) {
                followup1 = response.body();
                //ArrayList<Customer> leads1=new ArrayList<>();
                for (int i = 0; i < followup1.size(); i++) {
                    if (followup1.get(i).getRemark().toString().equals("Follow up")) {
                        followup.add(followup1.get(i));
                    }
                  //  System.out.println(followup.get(i).getName());

                }


                recyclerViewAdapterfollowup = new RecyclerViewAdapterFollowup(Followupnew.this, followup);
                recyclerView.setAdapter(recyclerViewAdapterfollowup);


            }

            @Override
            public void onFailure(Call<ArrayList<Customer>> call, Throwable t) {

            }
        });
        ItemTouchHelper.SimpleCallback simplecallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT)
        {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder1, int direction)
            {
                RecyclerViewAdapterFollowup.ViewHolder viewHolder = new RecyclerViewAdapterFollowup.ViewHolder(recyclerView);
                if (followup.get(viewHolder.getAdapterPosition()+1).getRemark().equals("Follow up")) {
                    Customer customer1 = followup.get(viewHolder.getAdapterPosition() + 1);
//            Customer customer=followup3.get(viewHolder.getAdapterPosition()+1);
                    if (direction == ItemTouchHelper.RIGHT) {
                        if (customer1.getRemark().equals("Follow up")) {
                            customer1.setRemark("Client");
                            Call<Customer> call = Apicontrollerflaxen.getInstance().getapi().updateLeads(customer1.getId(), customer1.getRemark());
                            call.enqueue(new Callback<Customer>() {
                                @Override
                                public void onResponse(Call<Customer> call, Response<Customer> response) {
                                    Intent intent = new Intent(Followupnew.this, Followupnew.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    // recyclerViewAdapterfollowup.notifyItemRemoved(customer1.getId());
                                    // recyclerView.notify();


                                    CustomToast.makeText(Followupnew.this, " Added to clients", Toast.LENGTH_SHORT, R.color.red);

                                }

                                @Override
                                public void onFailure(Call<Customer> call, Throwable t) {
                                    CustomToast.makeText(Followupnew.this, "not updated", Toast.LENGTH_LONG, R.color.red);

                                }
                            });


                        }


                    }
                    else
                    {
                            if (customer1.getRemark().equals("Follow up")) {
                            customer1.setRemark("In future");
                            Call<Customer> call = Apicontrollerflaxen.getInstance().getapi().updateLeads(customer1.getId(), customer1.getRemark());
                            call.enqueue(new Callback<Customer>() {
                                @Override
                                public void onResponse(Call<Customer> call, Response<Customer> response) {
                                    Intent intent = new Intent(Followupnew.this, Followupnew.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    // recyclerViewAdapterfollowup.notifyItemRemoved(customer1.getId());
                                    // recyclerView.notify();


                                    CustomToast.makeText(Followupnew.this, " updated", Toast.LENGTH_SHORT, R.color.red);

                                }

                                @Override
                                public void onFailure(Call<Customer> call, Throwable t) {
                                    CustomToast.makeText(Followupnew.this, "not updated", Toast.LENGTH_LONG, R.color.red);

                                }
                            });


                        }


                    }
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simplecallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerViewAdapterfollowup = new RecyclerViewAdapterFollowup(Followupnew.this, followup);
        recyclerView.setAdapter(recyclerViewAdapterfollowup);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                followup3 = new ArrayList<>();
                if (tab.getPosition() == 1)
                {
                    Call<ArrayList<Customer>> call1 = Apicontrollerflaxen.getInstance().getapi().getleadsCustomer();
                    call1.enqueue(new Callback<ArrayList<Customer>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Customer>> call1, Response<ArrayList<Customer>> response) {
                            followup2 = response.body();

                            for (int i = 0; i < followup2.size(); i++) {
                                if (followup2.get(i).getRemark().toString().equals("In future")) {
                                    followup3.add(followup2.get(i));
                                }

                            }


                            System.out.println("test");
//                            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simplecallback);
//                            itemTouchHelper.attachToRecyclerView(recyclerView);


                        recyclerViewAdapterleads = new RecyclerViewAdapterFollowup2(Followupnew.this, followup3);
                            recyclerView.setAdapter(recyclerViewAdapterleads);


                        }

                        @Override
                        public void onFailure(Call<ArrayList<Customer>> call1, Throwable t) {

                        }
                    });


                }
                else
                    {  ItemTouchHelper.SimpleCallback simplecallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT )
                    {
                        @Override
                        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                            return false;
                        }

                        @Override
                        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder1, int direction)
                        { if(direction==ItemTouchHelper.RIGHT)
                        {
                            RecyclerViewAdapterFollowup.ViewHolder viewHolder = new RecyclerViewAdapterFollowup.ViewHolder(recyclerView);
                            if (followup.get(viewHolder.getAdapterPosition()+1).getRemark().equals("Follow up"))
                            {
                                Customer customer1 = followup.get(viewHolder.getAdapterPosition() + 1);
//            Customer customer=followup3.get(viewHolder.getAdapterPosition()+1);
                                if (direction == ItemTouchHelper.RIGHT) {
                                    if (customer1.getRemark().equals("Follow up"))
                                    {
                                        customer1.setRemark("Client");
                                        Call<Customer> call = Apicontrollerflaxen.getInstance().getapi().updateLeads(customer1.getId(), customer1.getRemark());
                                        call.enqueue(new Callback<Customer>() {
                                            @Override
                                            public void onResponse(Call<Customer> call, Response<Customer> response) {
                                                Intent intent = new Intent(Followupnew.this, Followupnew.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);
                                                // recyclerViewAdapterfollowup.notifyItemRemoved(customer1.getId());
                                                // recyclerView.notify();


                                                CustomToast.makeText(Followupnew.this, " updated", Toast.LENGTH_SHORT, R.color.red);

                                            }

                                            @Override
                                            public void onFailure(Call<Customer> call, Throwable t) {
                                                CustomToast.makeText(Followupnew.this, "not updated", Toast.LENGTH_LONG, R.color.red);

                                            }
                                        });


                                    }


                                }
                            }


                            }

                        else if(direction==ItemTouchHelper.LEFT)
                        {
                            //System.out.println("hello friends");
                            RecyclerViewAdapterFollowup.ViewHolder viewHolder = new RecyclerViewAdapterFollowup.ViewHolder(recyclerView);
                            if(followup.get(viewHolder.getAdapterPosition()+1).getRemark().equals("Follow up"));
                            {Customer customer1 = followup.get(viewHolder.getAdapterPosition() + 1);
//            Customer customer=followup3.get(viewHolder.getAdapterPosition()+1);
                                if (direction == ItemTouchHelper.LEFT) {
                                    if (customer1.getRemark().equals("Follow up"))
                                    {
                                        customer1.setRemark("In future");
                                        Call<Customer> call = Apicontrollerflaxen.getInstance().getapi().updateLeads(customer1.getId(), customer1.getRemark());
                                        call.enqueue(new Callback<Customer>() {
                                            @Override
                                            public void onResponse(Call<Customer> call, Response<Customer> response) {
                                                Intent intent = new Intent(Followupnew.this, Followupnew.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);
                                                // recyclerViewAdapterfollowup.notifyItemRemoved(customer1.getId());
                                                // recyclerView.notify();


                                                CustomToast.makeText(Followupnew.this, " updated", Toast.LENGTH_SHORT, R.color.red);

                                            }

                                            @Override
                                            public void onFailure(Call<Customer> call, Throwable t) {
                                                CustomToast.makeText(Followupnew.this, "not updated", Toast.LENGTH_LONG, R.color.red);

                                            }
                                        });


                                    }


                                }
                            }

                        }
                        };
                    };


                        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simplecallback);
                        itemTouchHelper.attachToRecyclerView(recyclerView);

                        recyclerViewAdapterfollowup = new RecyclerViewAdapterFollowup(Followupnew.this, followup);
                    recyclerView.setAdapter(recyclerViewAdapterfollowup);

                }
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {Call<ArrayList<Customer>> call = Apicontrollerflaxen.getInstance().getapi().getleadsCustomer();
                call.enqueue(new Callback<ArrayList<Customer>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Customer>> call, Response<ArrayList<Customer>> response) {
                        followup1 = response.body();
                        //ArrayList<Customer> leads1=new ArrayList<>();
                        for (int i = 0; i < followup1.size(); i++) {
                            if (followup1.get(i).getRemark().toString().equals("Follow up")) {
                                followup.add(followup1.get(i));
                            }

                        }


                        recyclerViewAdapterfollowup = new RecyclerViewAdapterFollowup(Followupnew.this, followup);
                        recyclerView.setAdapter(recyclerViewAdapterfollowup);


                    }

                    @Override
                    public void onFailure(Call<ArrayList<Customer>> call, Throwable t) {

                    }
                });

            }
        });
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simplecallback);
//        itemTouchHelper.attachToRecyclerView(recyclerView);

        }


    }


