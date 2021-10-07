package com.example.crm.SalesManagement;

import android.annotation.SuppressLint;
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
import com.example.crm.SendProposal;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewAdapterFollowup extends RecyclerView.Adapter<RecyclerViewAdapterFollowup.ViewHolder>{private Context context;
    private ArrayList<Customer> clients;
    String isuploaded;


    public RecyclerViewAdapterFollowup(Context context, ArrayList<Customer> clients) {
        this.context = context;
        this.clients = clients;
    }

    @NonNull

    public RecyclerViewAdapterFollowup.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.followupcardnew,parent,false);

        return new RecyclerViewAdapterFollowup.ViewHolder(view1);
    }


    @SuppressLint("ResourceAsColor")
    public void onBindViewHolder(@NonNull RecyclerViewAdapterFollowup.ViewHolder holder, int position) {
        Customer customer=clients.get(position);
        final int[] count = {0};
        holder.servicetype.setText(customer.getDomain());
        holder.clientname.setText(customer.getName());
        holder.callcount.setText(customer.getCallcount());

//        holder.button1.setText("In Future");
        holder.button2.setText("Send proposal");

       if(customer.getRemark().equals("Follow up"))
       {
           if (customer.getProposal()==null) {
               holder.button2.setText("Send Proposal");
           } else {
               holder.button2.setText("Sent");
           }
       }
       else if(customer.getRemark().equals("In future"))
       {
           holder.button2.setText("Add to ongoing");
           holder.button3.setText("Not Intrested");
       }
        //holder.notintrested.setCardForegroundColor();
//        holder.button2.setTextColor(ColorStateList.valueOf(R.color.aayushpeach));
//        //holder.intrested.setCardForegroundColor(ColorStateList.valueOf(R.color.aayushlightgreen));
//        holder.button1.setTextColor(ColorStateList.valueOf(R.color.aayushlightgreen));
        holder.sendpr.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                if (customer.getRemark().equals("Follow up"))
                {
                    openDialog(customer);

                }
                else if(customer.getRemark().equals("In future"))
                {
                    customer.setRemark("Follow up");
                    Call<Customer> call=Apicontrollerflaxen.getInstance().getapi().updateLeads(customer.getId(),customer.getRemark());
                    call.enqueue(new Callback<Customer>() {
                        @Override
                        public void onResponse(Call<Customer> call, Response<Customer> response)
                        {
                            Intent intent=new Intent(context,Followupnew.class);
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
//        holder.infuture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            { if(customer.getRemark().equals("Follow up"))
//            {
//                customer.setRemark("In future");
//                Call<Customer> call = Apicontrollerflaxen.getInstance().getapi().updateLeads(customer.getId(), customer.getRemark());
//                call.enqueue(new Callback<Customer>() {
//                    @Override
//                    public void onResponse(Call<Customer> call, Response<Customer> response) {
//                        Intent intent = new Intent(context, Followupnew.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        context.startActivity(intent);
//
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<Customer> call, Throwable t) {
//                        CustomToast.makeText(view.getContext(), "not updated", Toast.LENGTH_LONG, R.color.red);
//
//                    }
//                });
//            }
//            else  if(customer.getRemark().equals("In future"))
//            {
//                customer.setRemark("history");
//                Call<Customer> call1= Apicontrollerflaxen.getInstance().getapi().updateLeads(customer.getId(),customer.getRemark());
//                call1.enqueue(new Callback<Customer>() {
//                    @Override
//                    public void onResponse(Call<Customer> call1, Response<Customer> response) {
//                        Intent intent1 = new Intent(context, Followupnew.class);
//                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        context.startActivity(intent1);
//
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<Customer> call1, Throwable t) {
//                        CustomToast.makeText(view.getContext(), "not updated", Toast.LENGTH_LONG, R.color.red);
//
//                    }
//                });
//            }
//
//            }
//        });
        holder.callcount.setText("No response : "+customer.getCallcount()+" times");
        holder.noresponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                count[0] += 1;

                System.out.println(count[0]);
                //.setCallcount(""+count[0]);
                //holder.callcount.setText(customer.getCallcount());
                Call<Customer> call1= Apicontrollerflaxen.getInstance().getapi().updatecallcount(customer.getId());
                call1.enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call1, Response<Customer> response) {
                        Intent intent1 = new Intent(context, Followupnew.class);
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
    protected static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView clientname, servicetype,callcount,button1,button2,button3;
        ImageView call, sms;
        MaterialCardView sendpr,noresponse;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clientname = itemView.findViewById(R.id.textView19);
           // button1=itemView.findViewById(R.id.textView35);
            button2=itemView.findViewById(R.id.textView36);
            button3=itemView.findViewById(R.id.clientstext);
            servicetype = itemView.findViewById(R.id.textView18);
            call = itemView.findViewById(R.id.imageView20);
            sms = itemView.findViewById(R.id.imageView22);
          //  infuture = itemView.findViewById(R.id.nicard);
            sendpr = itemView.findViewById(R.id.intrcard);
            noresponse = itemView.findViewById(R.id.nores);
            call.setOnClickListener(this);
            callcount=itemView.findViewById(R.id.callcount);
//            infuture.setOnClickListener(this);
            sms.setOnClickListener(this);
            sendpr.setOnClickListener(this);
            noresponse.setOnClickListener(this);


        }

        @Override
        public void onClick(View view)
        {switch (view.getId())
        {

        }

        }
    }
    public  void openDialog(Customer customer)
    {Intent intent=new Intent(context, SendProposal.class);
    intent.putExtra("id",customer);
    context.startActivity(intent);

    }



}


