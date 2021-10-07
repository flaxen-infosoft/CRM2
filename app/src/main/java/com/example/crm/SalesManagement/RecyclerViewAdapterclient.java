package com.example.crm.SalesManagement;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.CustomToast;
import com.example.crm.Model.Customer;
import com.example.crm.R;
import com.example.crm.Retro.Apicontrollerflaxen;
import com.example.crm.Sendnvoice;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        //System.out.println(customer.getInvoice());

if(customer.getInvoice().isEmpty())
{ holder.sendinvoicetext.setText("Send Invoice");

}
else
    {
        holder.sendinvoicetext.setText("Invoice Sent");
}
    holder.sendinvoice.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view)
        { Intent intent=new Intent(context, Sendnvoice.class);
            intent.putExtra("id",customer);
            context.startActivity(intent);


        }
    });
    holder.withdraw.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {customer.setRemark("Withdraw");
            //customer.setRemark("Deleted");


            Call<Customer> call1= Apicontrollerflaxen.getInstance().getapi().updateLeads(customer.getId(),customer.getRemark());
            call1.enqueue(new Callback<Customer>() {
                @Override
                public void onResponse(Call<Customer> call1, Response<Customer> response) {
                    Intent intent1 = new Intent(context, Clientnew.class);
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
    holder.complete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            customer.setRemark("Completed");
            //customer.setRemark("Deleted");
            Call<Customer> call1= Apicontrollerflaxen.getInstance().getapi().updateLeads(customer.getId(),customer.getRemark());
            call1.enqueue(new Callback<Customer>() {
                @Override
                public void onResponse(Call<Customer> call1, Response<Customer> response) {
                    Intent intent1 = new Intent(context, Clientnew.class);
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
holder.call.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent callintent= new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+customer.getPhone_no().trim()));
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
    @Override
    public int getItemCount() {

            return clients.size();



    }




    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView clientname, servicetype,sendinvoicetext;
        ImageView call, sms;
        MaterialCardView sendinvoice, complete, withdraw;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sendinvoicetext=itemView.findViewById(R.id.clientstext);
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
