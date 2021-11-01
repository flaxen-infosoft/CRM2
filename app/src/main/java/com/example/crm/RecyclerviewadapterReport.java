package com.example.crm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.SalesManagement.Callsinfo;

import java.util.ArrayList;

public class RecyclerviewadapterReport extends RecyclerView.Adapter<RecyclerviewadapterReport.ViewHolder>
{private Context context;
    private ArrayList<Callsinfo> callsinfo ;


    public RecyclerviewadapterReport(Context context, ArrayList<Callsinfo> callsinfo)
    {
        this.context = context;
        this.callsinfo = callsinfo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.reportcardnew,parent,false);

        return new RecyclerviewadapterReport.ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    { int free=Integer.parseInt(callsinfo.get(position).getHours())/60;
        holder.hours.setText((String.valueOf( free)));

        holder.totalcalls.setText(""+callsinfo.get(position).getTotalcalls());
        holder.datetext.setText(callsinfo.get(position).getDate());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(context,ReportDetailnew.class);
                intent.putExtra("date",holder.datetext.getText());
                context.startActivity(intent);

            }
        });

  // holder.totalcalls.setText(callsinfo.get(position).getTotalcalls());

    }

    @Override
    public int getItemCount() {
   return callsinfo.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView hours, totalcalls,datetext;
        ImageView call, sms;
        CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            totalcalls=itemView.findViewById(R.id.callstotal);
            hours=itemView.findViewById(R.id.hourstotal);
            card=itemView.findViewById(R.id.cardreport);
            datetext=itemView.findViewById(R.id.datetext111);




        }

        @Override
        public void onClick(View view)
        {switch (view.getId())
        {

        }

        }
    }
}
