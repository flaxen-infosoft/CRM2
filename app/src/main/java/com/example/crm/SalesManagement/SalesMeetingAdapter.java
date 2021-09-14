package com.example.crm.SalesManagement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.EmployeeManagement.ChooseTargetLocation;
import com.example.crm.R;
import com.example.crm.SalesManagement.model.Meeting;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;


public class SalesMeetingAdapter extends RecyclerView.Adapter<SalesMeetingAdapter.MeetingViewHolder> {
    ArrayList<Meeting> meetings;
    Context context;

    public SalesMeetingAdapter(ArrayList<Meeting> meetings, Context context) {
        this.meetings = meetings;
        this.context = context;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_ofsales_lead_meet, parent, false);
        return new MeetingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        holder.title.setText(meetings.get(position).getTitle());
        holder.startEndButton.setOnClickListener(v -> {
            Intent i = new Intent(context, ChooseTargetLocation.class);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    public static class MeetingViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        MaterialCardView startEndButton;
        ImageButton recordButton;

        public MeetingViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.leadname);
            startEndButton = view.findViewById(R.id.startendbutton);
            recordButton = view.findViewById(R.id.recordbutton);
        }
    }
}
