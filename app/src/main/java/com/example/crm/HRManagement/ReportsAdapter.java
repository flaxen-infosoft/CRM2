package com.example.crm.HRManagement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Model.Report;
import com.example.crm.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ReportsAdapter extends RecyclerView.Adapter<ReportsAdapter.ViewHolder> {
    Context context;
    List<Report> reportList;

    public ReportsAdapter(Context context, List<Report> reportList) {
        this.context = context;
        this.reportList = reportList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.report_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Report report = reportList.get(position);
        holder.name.setText(report.getName());
        holder.designation.setText(report.getDesignation());
        holder.date.setText(report.getDate());
        holder.noofdeals.setText(report.getNoOfSalesDone());
        holder.itemView.setOnClickListener(view -> {
            context.startActivity(new Intent(context,ReportDetailsActivity.class).putExtra("report",reportList.get(position)));
        });
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, date, designation, noofdeals;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameof_employee_report);
            date = itemView.findViewById(R.id.dateofreport);
            designation = itemView.findViewById(R.id.designationemployee_report);
            noofdeals = itemView.findViewById(R.id.noofdeals);
        }
    }
}
