package com.example.crm.SalesManagement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Model.Customer;
import com.example.crm.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class RecyclerViewAdaptermeetingnew extends RecyclerView.Adapter<RecyclerViewAdaptermeetingnew.ViewHolder>
{ Context context;
ArrayList<Customer> customers;
int pos;

    public RecyclerViewAdaptermeetingnew(Context context, ArrayList<Customer> customers, int pos) {
        this.context = context;
        this.customers = customers;
        this.pos = pos;
    }

    public RecyclerViewAdaptermeetingnew(Context context, ArrayList<Customer> customers) {
        this.context = context;
        this.customers = customers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    { View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_ofsales_lead_meet,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
      //  System.out.println(customers.get(position).getName());


           holder.leadname.setText(customers.get(position).getName());

           holder.start.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(context, StartSalesMeetingNew.class);
                   intent.putExtra("pos", position);

                   context.startActivity(intent);

               }
           });
           holder.date.setText("DATE:" + customers.get(position).getDate() + "\n  Time:" + customers.get(position).getTime());
          }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView leadname,date;
        public MaterialCardView start;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);


          leadname=itemView.findViewById(R.id.leadname);
start=itemView.findViewById(R.id.startendbutton);
date=itemView.findViewById(R.id.datetexttime);




        }

        @Override
        public void onClick(View view)
        {switch (view.getId())
        {

        }

        }
    }
}
