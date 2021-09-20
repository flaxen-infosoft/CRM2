package com.example.crm.SalesManagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.CustomToast;
import com.example.crm.Model.Customer;
import com.example.crm.R;
import com.example.crm.Retro.Apicontrollerflaxen;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewAdapterleads extends RecyclerView.Adapter<RecyclerViewAdapterleads.ViewHolder>{private Context context;
    private ArrayList<Customer> clients;

    public RecyclerViewAdapterleads(Context context, ArrayList<Customer> clients) {
        this.context = context;
        this.clients = clients;
    }

    @NonNull

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.leadscardnew,parent,false);

        return new ViewHolder(view1);
    }


    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Customer customer=clients.get(position);
        final int[] count = {0};
            holder.servicetype.setText(customer.getDomain());
            holder.clientname.setText(customer.getName());
            holder.callcount.setText(customer.getCallcount());
            holder.intrested.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (customer.getRemark().equals("Not Intrested"))
                    {customer.setRemark("Lead");
                        Call<Customer> call=Apicontrollerflaxen.getInstance().getapi().updateLeads(customer.getId(),customer.getRemark());
                        call.enqueue(new Callback<Customer>() {
                            @Override
                            public void onResponse(Call<Customer> call, Response<Customer> response)
                            {
                                Intent intent=new Intent(context,LeadsActivitynew.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                context.startActivity(intent);



                            }
                            @Override
                            public void onFailure(Call<Customer> call, Throwable t) {
                                CustomToast.makeText(view.getContext(), "not updated", Toast.LENGTH_LONG, R.color.red);

                            }
                        });

                    }
                    else if(customer.getRemark().equals("Lead"))
                    {
                        customer.setRemark("Followup");
                        Call<Customer> call=Apicontrollerflaxen.getInstance().getapi().updateLeads(customer.getId(),customer.getRemark());
                        call.enqueue(new Callback<Customer>() {
                            @Override
                            public void onResponse(Call<Customer> call, Response<Customer> response)
                            {
                                Intent intent=new Intent(context,LeadsActivitynew.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                context.startActivity(intent);



                            }
                            @Override
                            public void onFailure(Call<Customer> call, Throwable t) {
                                CustomToast.makeText(view.getContext(), "not updated", Toast.LENGTH_LONG, R.color.red);

                            }
                        });


                    }
                }
            });
            holder.notintrested.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                { if(customer.getRemark().equals("Lead"))
                {
                    customer.setRemark("Not Intrested");
                    Call<Customer> call = Apicontrollerflaxen.getInstance().getapi().updateLeads(customer.getId(), customer.getRemark());
                    call.enqueue(new Callback<Customer>() {
                        @Override
                        public void onResponse(Call<Customer> call, Response<Customer> response) {
                            Intent intent = new Intent(context, LeadsActivitynew.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            context.startActivity(intent);


                        }

                        @Override
                        public void onFailure(Call<Customer> call, Throwable t) {
                            CustomToast.makeText(view.getContext(), "not updated", Toast.LENGTH_LONG, R.color.red);

                        }
                    });
                }
                   else  if(customer.getRemark().equals("Not Intrested"))
                    {
                             customer.setRemark("history");
                        Call<Customer> call1= Apicontrollerflaxen.getInstance().getapi().updateLeads(customer.getId(),customer.getRemark());
                        call1.enqueue(new Callback<Customer>() {
                            @Override
                            public void onResponse(Call<Customer> call1, Response<Customer> response) {
                                Intent intent1 = new Intent(context, LeadsActivitynew.class);
                                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                context.startActivity(intent1);


                            }

                            @Override
                            public void onFailure(Call<Customer> call1, Throwable t) {
                                CustomToast.makeText(view.getContext(), "not updated", Toast.LENGTH_LONG, R.color.red);

                            }
                        });
                    }

                }
            });
            holder.noresponse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    count[0] += 1;

                    System.out.println(count[0]);
                    //.setCallcount(""+count[0]);
                    holder.callcount.setText(customer.getCallcount());
                    Call<Customer> call1= Apicontrollerflaxen.getInstance().getapi().updatecallcount(customer.getId());
                    call1.enqueue(new Callback<Customer>() {
                        @Override
                        public void onResponse(Call<Customer> call1, Response<Customer> response) {
                            Intent intent1 = new Intent(context, LeadsActivitynew.class);
                            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            context.startActivity(intent1);



                        }

                        @Override
                        public void onFailure(Call<Customer> call1, Throwable t) {
                            CustomToast.makeText(view.getContext(), "not updated", Toast.LENGTH_LONG, R.color.red);

                        }
                    });

                }
            });


    }


//    public void onBindViewHolder(@NonNull RecyclerViewAdapterleads.ViewHolder holder, int position)
//    { Customer customer=clients.get(position);
//        holder.servicetype.setText(customer.getDomain());
//        holder.clientname.setText(customer.getName());
//
//
//    }

    public int getItemCount() {
     return clients.size();


    }
    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView clientname, servicetype,callcount;
        ImageView call, sms;
        MaterialCardView notintrested,intrested,noresponse;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clientname = itemView.findViewById(R.id.textView19);
            servicetype = itemView.findViewById(R.id.textView18);
            call = itemView.findViewById(R.id.imageView20);
            sms = itemView.findViewById(R.id.imageView22);
           notintrested = itemView.findViewById(R.id.nicard);
            intrested = itemView.findViewById(R.id.intrcard);
          noresponse = itemView.findViewById(R.id.nores);
            call.setOnClickListener(this);
            callcount=itemView.findViewById(R.id.callcount);
       notintrested.setOnClickListener(this);
            sms.setOnClickListener(this);
           intrested.setOnClickListener(this);
        noresponse.setOnClickListener(this);


        }

        @Override
        public void onClick(View view)
        {switch (view.getId())
        {

        }

        }
    }



}
