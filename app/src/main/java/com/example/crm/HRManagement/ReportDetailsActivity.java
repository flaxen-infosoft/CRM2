package com.example.crm.HRManagement;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crm.Model.Report;
import com.example.crm.R;

public class ReportDetailsActivity extends AppCompatActivity {
    TextView Nameofemployee,NoofCalls, NoofNonPickedCalls, NoofNonInterestedCalls, NoofSwitchedoff, RequirementsofClients, DataRequirements, NoofAppDevelopments, NoofWebsiteDevelopments, NoOfPhysicalMeetings, NoOfVirtualMeetings, NoOfProspectus, NoOfFollowups, NoOfSalesDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_details);
        Report report = (Report) getIntent().getSerializableExtra("report");
        Nameofemployee= findViewById(R.id.nameof_employee_report_detail);
        NoofCalls=findViewById(R.id.number_of_calls_report);
        NoofNonPickedCalls=findViewById(R.id.non_picked_up_call_report);
        NoofNonInterestedCalls=findViewById(R.id.not_interested_calls_report);
        NoofSwitchedoff=findViewById(R.id.switched_off_report);
        RequirementsofClients=findViewById(R.id.requirements_of_clients_report);
        DataRequirements=findViewById(R.id.data_requirement_report);
        NoofAppDevelopments=findViewById(R.id.app_development_report);
        NoofWebsiteDevelopments=findViewById(R.id.website_development_report);
        NoOfPhysicalMeetings=findViewById(R.id.physical_meeting_report);
        NoOfVirtualMeetings=findViewById(R.id.virtual_meeting_report);
        NoOfProspectus=findViewById(R.id.noofprospectus_report);
        NoOfFollowups=findViewById(R.id.follow_ups_taken_report);
        NoOfSalesDone=findViewById(R.id.sales_done_report);




        Nameofemployee.setText(report.getName());
        NoofCalls.setText(report.getNoofCalls());
        NoofNonPickedCalls.setText(report.getNoofNonPickedCalls());
        NoofNonInterestedCalls.setText(report.getNoofNonInterestedCalls());
        NoofSwitchedoff.setText(report.getNoofSwitchedoff());
        RequirementsofClients.setText(report.getRequirementsofClients());
        DataRequirements.setText(report.getDataRequirements());
        NoofAppDevelopments.setText(report.getNoofAppDevelopments());
        NoofWebsiteDevelopments.setText(report.getNoofWebsiteDevelopments());
        NoOfPhysicalMeetings.setText(report.getNoOfPhysicalMeetings());
        NoOfVirtualMeetings.setText(report.getNoOfVirtualMeetings());
        NoOfProspectus.setText(report.getNoOfProspectus());
        NoOfFollowups.setText(report.getNoOfFollowups());
        NoOfSalesDone.setText(report.getNoOfSalesDone());
    }
}