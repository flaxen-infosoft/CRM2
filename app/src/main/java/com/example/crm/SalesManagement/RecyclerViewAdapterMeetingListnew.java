package com.example.crm.SalesManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.R;
import com.google.android.material.card.MaterialCardView;

import java.io.File;

public class RecyclerViewAdapterMeetingListnew extends RecyclerView.Adapter<RecyclerViewAdapterMeetingListnew.AudioViewHolder> {
   File[] allfiles;
   Context context;

    public RecyclerViewAdapterMeetingListnew(File[] allfiles, Context context) {
        this.allfiles = allfiles;
        this.context = context;
    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    { View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recordlistcard,null);
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position)
    {
          holder.name.setText(allfiles[position].getName());

    }

    @Override
    public int getItemCount() {
        return allfiles.length;
    }

    public class AudioViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        MaterialCardView play,submit;
        public AudioViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name=itemView.findViewById(R.id.recname);
            play=itemView.findViewById(R.id.play);
            submit=itemView.findViewById(R.id.startendbutton);

        }
    }
}
