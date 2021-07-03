package com.example.crm.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.crm.AttendanceManagement.AttendanceActivity;
import com.example.crm.CRMManagement.CRMDashboardActivity;
import com.example.crm.EmployeeManagement.EmployeeDashboardActivity;
import com.example.crm.HRManagement.CandidateMainPage;
import com.example.crm.LeaveManagement.LeaveManagementActivity;
import com.example.crm.R;
import com.example.crm.SalesManagement.LeaveApplicationActivity;
import com.example.crm.SalesManagement.SalesLoginActivity;
import com.example.crm.WebmailActivity;

public class DashboardFragment extends Fragment {
	CardView webmailcard;

	CardView employee_cardView, leave_card, crm_card, candidate_card, sales, applyforleave;
	CardView cardView, attendancecard;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

		employee_cardView = v.findViewById(R.id.employee);
		attendancecard = v.findViewById(R.id.attendance);
		webmailcard = v.findViewById(R.id.webmail);
		leave_card = v.findViewById(R.id.leavemanagement);
		crm_card = v.findViewById(R.id.crm);
		candidate_card = v.findViewById(R.id.candidate);
		sales = v.findViewById(R.id.invoice);
		applyforleave = v.findViewById(R.id.applyforleave);

		sales.setOnClickListener(v15 -> startActivity(new Intent(getContext(), SalesLoginActivity.class)));

		crm_card.setOnClickListener(v13 -> startActivity(new Intent(getContext(), CRMDashboardActivity.class)));

		candidate_card.setOnClickListener(v14 -> startActivity(new Intent(getContext(), CandidateMainPage.class)));

		leave_card.setOnClickListener(v1 -> {
			Intent intent = new Intent(getContext(), LeaveManagementActivity.class);
			startActivity(intent);
		});

		attendancecard.setOnClickListener(view -> {
			startActivity(new Intent(getContext(), AttendanceActivity.class));
		});

		webmailcard.setOnClickListener(view -> {
			startActivity(new Intent(getContext(), WebmailActivity.class));
		});

		employee_cardView.setOnClickListener(v12 -> {
			Intent intent = new Intent(getContext(), EmployeeDashboardActivity.class);
			startActivity(intent);
		});

		applyforleave.setOnClickListener(v1 -> {
			Intent intent = new Intent(getContext(), LeaveApplicationActivity.class);
			startActivity(intent);
		});

		return v;

	}
}