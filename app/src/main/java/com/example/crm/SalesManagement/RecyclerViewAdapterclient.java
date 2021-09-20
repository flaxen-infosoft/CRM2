package com.example.crm.SalesManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Model.Customer;
import com.example.crm.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class RecyclerViewAdapterclient extends RecyclerView.Adapter<RecyclerViewAdapterclient.ViewHolder>
{ private Context context;
private ArrayList<Customer> clients;

    public RecyclerViewAdapterclient(Context context, ArrayList<Customer> clients) {
        this.context = context;
        this.clients = clients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.clientscardnew,parent,false);
        return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    { Customer customer=clients.get(position);
    holder.servicetype.setText(customer.getDomain());
    holder.clientname.setText(customer.getName());


    }
    @Override
    public int getItemCount() {

            return clients.size();



    }




    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView clientname, servicetype;
        ImageView call, sms;
        MaterialCardView sendinvoice, complete, withdraw;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clientname = itemView.findViewById(R.id.textView19);
            servicetype = itemView.findViewById(R.id.textView18);
            call = itemView.findViewById(R.id.imageView20);
            sms = itemView.findViewById(R.id.imageView22);
            sendinvoice = itemView.findViewById(R.id.sendcard1);
            complete = itemView.findViewById(R.id.completecard);
            withdraw = itemView.findViewById(R.id.withdrawcard);
            call.setOnClickListener(this);
            sendinvoice.setOnClickListener(this);
            sms.setOnClickListener(this);
            complete.setOnClickListener(this);
            withdraw.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

        }
    }


}
