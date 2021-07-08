package com.example.crm.SalesManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.crm.CustomToast;
import com.example.crm.Model.Employee;
import com.example.crm.Model.Report;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;
import com.example.crm.SPOps;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesReportsActivity extends AppCompatActivity {
EditText NoofCalls, NoofNonPickedCalls, NoofNonInterestedCalls, NoofSwitchedoff, RequirementsofClients, DataRequirements, NoofAppDevelopments, NoofWebsiteDevelopments, NoOfPhysicalMeetings, NoOfVirtualMeetings, NoOfProspectus, NoOfFollowups, NoOfSalesDone;
Button reportsub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_reports);
        NoofCalls=findViewById(R.id.number_of_calls);
        NoofNonPickedCalls=findViewById(R.id.non_picked_up_call);
        NoofNonInterestedCalls=findViewById(R.id.not_interested_calls);
        NoofSwitchedoff=findViewById(R.id.switched_off);
        RequirementsofClients=findViewById(R.id.requirements_of_clients);
        DataRequirements=findViewById(R.id.data_requirement);
        NoofAppDevelopments=findViewById(R.id.app_development);
        NoofWebsiteDevelopments=findViewById(R.id.website_development);
        NoOfPhysicalMeetings=findViewById(R.id.physical_meeting);
        NoOfVirtualMeetings=findViewById(R.id.virtual_meeting);
        NoOfProspectus=findViewById(R.id.noofprospectus);
        NoOfFollowups=findViewById(R.id.follow_ups_taken);
        NoOfSalesDone=findViewById(R.id.sales_done);
        reportsub=findViewById(R.id.reportsub);
        reportsub.setOnClickListener(view -> {
            Employee employee=SPOps.getLoggedUser(SalesReportsActivity.this);
            Report report= new Report();
            report.setNoofCalls(NoofCalls.getText().toString());
            report.setNoofNonPickedCalls(NoofNonPickedCalls.getText().toString());
            report.setNoofNonInterestedCalls(NoofNonInterestedCalls.getText().toString());
            report.setNoofSwitchedoff(NoofSwitchedoff.getText().toString());
            report.setRequirementsofClients(RequirementsofClients.getText().toString());
            report.setDataRequirements(DataRequirements.getText().toString());
            report.setNoofAppDevelopments(NoofAppDevelopments.getText().toString());
            report.setNoofWebsiteDevelopments(NoofWebsiteDevelopments.getText().toString());
            report.setNoOfPhysicalMeetings(NoOfPhysicalMeetings.getText().toString());
            report.setNoOfVirtualMeetings(NoOfVirtualMeetings.getText().toString());
            report.setNoOfProspectus(NoOfProspectus.getText().toString());
            report.setNoOfFollowups(NoOfFollowups.getText().toString());
            report.setNoOfSalesDone(NoOfSalesDone.getText().toString());
            report.setReportgivenby(employee.getName());
            insertreport(report);
        });
    }

    private void insertreport(Report report) {
        RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
        Call<Report> call= ri.insertreport(report);
        call.enqueue(new Callback<Report>() {
            @Override
            public void onResponse(Call<Report> call, Response<Report> response) {
                if (!response.isSuccessful()){
                    System.out.println("response = " + response.code());
                }
                CustomToast.makeText(SalesReportsActivity.this,"Report Submitted",0, Color.parseColor("#32cd32"));
            }

            @Override
            public void onFailure(Call<Report> call, Throwable t) {
                System.out.println("t = " + t.getMessage());
            }
        });
    }
}