package com.example.crm.LeaveManagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Model.LeaveItem;
import com.example.crm.R;

import java.util.ArrayList;

public class LeaveManagementFragment1 extends Fragment {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ArrayList<LeaveItem> leaveItems;
    View v;
    UpdateStatusListeners usl;

    public LeaveManagementFragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        leaveItems = new ArrayList<>();
        usl = new UpdateStatusListeners() {
            @Override
            public void approve(LeaveItem leaveItem) {
                ((LeaveManagementActivity) getContext()).updateLeave(true, leaveItem);
            }

            @Override
            public void reject(LeaveItem leaveItem) {
                ((LeaveManagementActivity) getContext()).updateLeave(false, leaveItem);
            }
        };

    }

    public void setLeaveItems(ArrayList<LeaveItem> leaves) {
        leaveItems.addAll(leaves);
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_leave_management1, container, false);
			adapter = new RecyclerViewAdapter(getContext(), leaveItems, usl);
            recyclerView = v.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        }

        return v;
    }
}