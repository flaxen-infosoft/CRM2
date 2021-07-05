package com.example.crm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Model.UpcomingCustomer;

import java.util.List;

public class UpcomingAdapter extends  RecyclerView.Adapter<UpcomingAdapter.ViewHolder>{
    List<UpcomingCustomer> upcomingCustomerList;

    public UpcomingAdapter(List<UpcomingCustomer> upcomingCustomerList) {
        this.upcomingCustomerList = upcomingCustomerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UpcomingAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.upcomingcustomercard,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
UpcomingCustomer upcomingCustomer= upcomingCustomerList.get(position);
holder.name.setText(upcomingCustomer.getName());
    }

    @Override
    public int getItemCount() {
        return upcomingCustomerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
name=itemView.findViewById(R.id.name_of_upcoming_cust);
        }
    }
}
