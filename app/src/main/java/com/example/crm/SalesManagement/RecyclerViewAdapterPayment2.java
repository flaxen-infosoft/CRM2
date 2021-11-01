package com.example.crm.SalesManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.R;

public class RecyclerViewAdapterPayment2 extends RecyclerView.Adapter<RecyclerViewAdapterPayment2.ViewHolder>
{ Context context;

    public RecyclerViewAdapterPayment2(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.paymentemptycard,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {holder.payment.setText("NO DATA FOUND");

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView payment;


        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);


            payment=itemView.findViewById(R.id.textView42);




        }

        @Override
        public void onClick(View view)
        {switch (view.getId())
        {

        }

        }
    }
}
