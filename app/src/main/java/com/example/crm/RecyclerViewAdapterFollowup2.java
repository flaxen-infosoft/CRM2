package com.example.crm;

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

import com.example.crm.Model.Customer;
import com.example.crm.Retro.Apicontrollerflaxen;
import com.example.crm.SalesManagement.Followupnew;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewAdapterFollowup2  extends RecyclerView.Adapter<RecyclerViewAdapterFollowup2.ViewHolder>{private Context context;
    private ArrayList<Customer> clients;

    public RecyclerViewAdapterFollowup2(Context context, ArrayList<Customer> clients) {
        this.context = context;
        this.clients = clients;
    }

    @NonNull

    public RecyclerViewAdapterFollowup2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.leadscardnew,parent,false);

        return new RecyclerViewAdapterFollowup2.ViewHolder(view1);
    }




    public void onBindViewHolder(@NonNull RecyclerViewAdapterFollowup2.ViewHolder holder, int position) {
        Customer customer=clients.get(position);
        final int[] count = {0};
        holder.servicetype.setText(customer.getDomain());
        holder.clientname.setText(customer.getName());
        holder.callcount.setText(customer.getCallcount());
        holder.intrested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (customer.getRemark().equals("In future"))
                {customer.setRemark("Follow up");

                    Call<Customer> call= Apicontrollerflaxen.getInstance().getapi().updateLeads(customer.getId(),customer.getRemark());
                    call.enqueue(new Callback<Customer>() {
                        @Override
                        public void onResponse(Call<Customer> call, Response<Customer> response)
                        {
                            Intent intent=new Intent(context, Followupnew.class);
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
            { if(customer.getRemark().equals("In future"))
            {
                customer.setRemark("Deleted");
                Call<Customer> call = Apicontrollerflaxen.getInstance().getapi().updateLeads(customer.getId(), customer.getRemark());
                call.enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                        Intent intent = new Intent(context, Followupnew.class);
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


        holder.callcount.setText("No response: "+customer.getCallcount()+" times");
        holder.noresponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                count[0] += 1;

                System.out.println(count[0]);
                //customer.setCallcount("No response:"+customer.getCallcount()+" times");

                Call<Customer> call1= Apicontrollerflaxen.getInstance().getapi().updatecallcount(customer.getId());
                call1.enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call1, Response<Customer> response) {
                        Intent intent1 = new Intent(context, Followupnew.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent1);
                        //holder.callcount.setText("No response:"+customer.getCallcount()+" times");


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


