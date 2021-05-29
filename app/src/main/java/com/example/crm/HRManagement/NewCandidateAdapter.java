package com.example.crm.HRManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

;
import com.example.crm.Model.Candidate;
import com.example.crm.R;


import java.util.List;

public class NewCandidateAdapter extends RecyclerView.Adapter<NewCandidateAdapter.ViewHolder> {

    Context context;
    List<Candidate> candidateList;

    public NewCandidateAdapter(Context context, List<Candidate> candidateList) {
        this.context = context;
        this.candidateList = candidateList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.candidatecard, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
Candidate candidate=candidateList.get(position);
holder.name.setText(candidate.getName());
holder.designation.setText(candidate.getDesignation());
holder.resumebt.setOnClickListener(view -> {

});
    }

    @Override
    public int getItemCount() {
        return candidateList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, designation,  date;
        Button resumebt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_card_candidatename);
            designation = itemView.findViewById(R.id.tv_card_candidatedesignation);
            date = itemView.findViewById(R.id.tv_card_candidatedate);
            resumebt = itemView.findViewById(R.id.resumebt);
        }
    }
}
