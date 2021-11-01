package com.example.crm.SalesManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Model.Customer;
import com.example.crm.R;

import java.util.ArrayList;

public class Recyclerviewadapterreportdetail extends RecyclerView.Adapter<Recyclerviewadapterreportdetail.ViewHolder>
{
    Context context;
    ArrayList<Customer> detail;

    public Recyclerviewadapterreportdetail(Context context, ArrayList<Customer> detail) {
        this.context = context;
        this.detail = detail;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.reportdetailnewcard,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        int duration=Integer.parseInt(detail.get(position).getDuration())/60;
        holder.clientname.setText(detail.get(position).getName());
        holder.servicetype.setText(detail.get(position).getDomain());
        holder.duration.setText(duration+" MINS");

    }

    @Override
    public int getItemCount() {
        return detail.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView clientname, servicetype,duration;


        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            clientname = itemView.findViewById(R.id.nametext576);
            servicetype = itemView.findViewById(R.id.domain1234);
            duration=itemView.findViewById(R.id.durationtext);



        }

        @Override
        public void onClick(View view)
        {switch (view.getId())
        {

        }

        }
    }
}