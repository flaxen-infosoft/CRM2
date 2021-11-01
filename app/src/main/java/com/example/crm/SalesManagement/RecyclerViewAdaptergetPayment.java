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

public class RecyclerViewAdaptergetPayment extends RecyclerView.Adapter<RecyclerViewAdaptergetPayment.ViewHolder> {
    Context context;
    ArrayList<Customer> detail;

    public RecyclerViewAdaptergetPayment(Context context, ArrayList<Customer> detail) {
        this.context = context;
        this.detail = detail;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.reportdetailnewcard,null);
        return new RecyclerViewAdaptergetPayment.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    { holder.payment.setText("Paid:Rs."+detail.get(position).getAmount_paid());
    holder.clientname.setText(detail.get(position).getName());
        holder.servicetype.setText(detail.get(position).getDomain());
        if(detail.get(position).getAmount_paid()!=null&&detail.get(position).getTotal_amount()!=null) {
            long total = Long.parseLong(detail.get(position).getTotal_amount());
            long paid = Long.parseLong(detail.get(position).getAmount_paid());
            holder.pendingpayment.setText("Pending:Rs." + (total - paid));
        }

    }

    @Override
    public int getItemCount() {
        return detail.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView clientname, servicetype,payment,pendingpayment;


        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            clientname = itemView.findViewById(R.id.nametext576);
            servicetype = itemView.findViewById(R.id.domain1234);
           payment=itemView.findViewById(R.id.durationtext);
           pendingpayment=itemView.findViewById(R.id.pendingpayment);



        }

        @Override
        public void onClick(View view)
        {switch (view.getId())
        {

        }

        }
    }
}
