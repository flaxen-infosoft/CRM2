package com.example.crm.HRManagement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Constants;
import com.example.crm.Model.Candidate;
import com.example.crm.Model.Remark;
import com.example.crm.R;
import com.example.crm.SPOps;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewCandidate2Adapter extends RecyclerView.Adapter<NewCandidate2Adapter.CandidateHolder> {
    Context context;
    List<Candidate> candidates;
    GeneralInterface gi;

    public NewCandidate2Adapter(Context context, List<Candidate> candidates, GeneralInterface gi) {
        this.context = context;
        this.candidates = new ArrayList<>();
        this.candidates.addAll(candidates);
        this.gi = gi;
    }

    public void setTempList(List<Candidate> nc) {
        candidates.clear();
        candidates.addAll(nc);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CandidateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_new_candidate_2, parent, false);
        return new CandidateHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CandidateHolder holder, int position) {
        holder.name.setText(candidates.get(position).getName());
        holder.date.setText("Date of Interview: " + candidates.get(position).getDateof_interview());
        holder.applied_for.setText("Applied for: " + candidates.get(position).getDesignation());
        holder.assignedBy.setText("Assigned by: " + candidates.get(position).getAssignedBy());
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }

    public class CandidateHolder extends RecyclerView.ViewHolder {

        TextView name, date, applied_for, link, updateStatus, viewresume, remark, assignedBy;
        ImageView call;
        @SuppressLint("ClickableViewAccessibility")
        public CandidateHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            applied_for = itemView.findViewById(R.id.applied_for);
            link = itemView.findViewById(R.id.link);
            updateStatus = itemView.findViewById(R.id.updateStatus);
            viewresume = itemView.findViewById(R.id.viewresume);
            remark = itemView.findViewById(R.id.remark);
            assignedBy = itemView.findViewById(R.id.assignedBy);
            call = itemView.findViewById(R.id.callnew);
            viewresume.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PdfViewerActivity.class);
                    intent.putExtra("pdfurl", candidates.get(getAdapterPosition()).getResume());
                    context.startActivity(intent);
                }
            });
            call.setOnClickListener(view -> {
                int pos = getAdapterPosition();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + candidates.get(pos).getPhone()));
                context.startActivity(intent);
            });
            remark.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                View view = LayoutInflater.from(context).inflate(R.layout.remarks_dialog, null, false);
                TextView textView = ((TextView) view.findViewById(R.id.remarktxt));
                textView.setText(candidates.get(pos).getRemarks());
                textView.setScroller(new Scroller(context));
                textView.setVerticalScrollBarEnabled(true);
                textView.setMovementMethod(new ScrollingMovementMethod());

                new MaterialAlertDialogBuilder(context)
                        .setTitle("Remarks for " + candidates.get(pos).getName())
                        .setView(view)
                        .setCancelable(false)
                        .setPositiveButton("Add remark", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                View d = LayoutInflater.from(context).inflate(R.layout.add_remark_dialog, null, false);
                                new MaterialAlertDialogBuilder(context)
                                        .setView(d)
                                        .setTitle("Add Remark")
                                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                String remark = ((TextInputLayout) d.findViewById(R.id.et)).getEditText().getText().toString();
                                                Remark r = new Remark();
                                                r.setAuthor(SPOps.getLoggedInUserName(context));
                                                r.setText(remark);
                                                r.setDate(new Date());
                                                dialog.dismiss();
                                                ((NewCandidateActivity2) context).updateRemark(candidates.get(pos), r);
                                            }
                                        }).setCancelable(false)
                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        }).show();

                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            });

            updateStatus.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                new MaterialAlertDialogBuilder(context).setTitle("Shortlist " + candidates.get(pos).getName() + "?")
                        .setNeutralButton("Cancel", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .setPositiveButton("Shortlist", (dialog, which) -> {
                            gi.onShortlistCandidate(candidates.get(pos));
                        }).show();
            });
            link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/html");
                    intent.putExtra(Intent.EXTRA_EMAIL, candidates.get(pos).getPid());
                    intent.putExtra(Intent.EXTRA_SUBJECT, Constants.email_subject);
                    intent.putExtra(Intent.EXTRA_TEXT, Constants.body + "http://com.example.crm/" + candidates.get(pos).getId());
                    context.startActivity(Intent.createChooser(intent, "Send Email"));

                }
            });
        }
    }

}
