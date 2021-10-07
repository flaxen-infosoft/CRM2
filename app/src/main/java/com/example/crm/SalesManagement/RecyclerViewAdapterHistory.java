package com.example.crm.SalesManagement;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class RecyclerViewAdapterHistory  extends RecyclerView.Adapter<RecyclerViewAdapterHistory.ViewHolder>{private Context context;
    private ArrayList<Customer> clients;

    public RecyclerViewAdapterHistory(Context context, ArrayList<Customer> clients) {
        this.context = context;
        this.clients = clients;
    }

    @NonNull

    public RecyclerViewAdapterHistory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.historycardnew,parent,false);

        return new RecyclerViewAdapterHistory.ViewHolder(view1);
    }


    public void onBindViewHolder(@NonNull RecyclerViewAdapterHistory.ViewHolder holder, int position) {
        Customer customer=clients.get(position);
        final int[] count = {0};
        holder.servicetype.setText(customer.getDomain());
        holder.clientname.setText(customer.getName());

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            { Intent callintent= new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+customer.getPhone_no().trim()));
                callintent.setData(Uri.parse("tel:"+customer.getPhone_no().trim()));
                context.startActivity(callintent);


            }
        });
        holder.sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("smsto:" +customer.getPhone_no().trim() );
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.putExtra("sms_body", "hello");
                i.setPackage("com.whatsapp.w4b");
                context.startActivity(i);
                System.out.println(customer.getPhone_no().trim());
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

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            clientname = itemView.findViewById(R.id.textView19);
            servicetype = itemView.findViewById(R.id.textView18);
            call = itemView.findViewById(R.id.imageView20);
            sms = itemView.findViewById(R.id.imageView22);


        }

        @Override
        public void onClick(View view)
        {switch (view.getId())
        {

        }

        }
    }



}




