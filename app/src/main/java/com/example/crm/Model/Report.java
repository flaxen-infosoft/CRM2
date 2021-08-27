package com.example.crm.Model;

import java.io.Serializable;

public class Report implements Serializable {
    String NoofCalls, NoofNonPickedCalls, NoofNonInterestedCalls, NoofSwitchedoff, RequirementsofClients, DataRequirements, NoofAppDevelopments, NoofWebsiteDevelopments, NoOfPhysicalMeetings, NoOfVirtualMeetings, NoOfProspectus, NoOfFollowups, NoOfSalesDone, Reportgivenby,name,designation ,date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReportgivenby() {
        return Reportgivenby;
    }

    public void setReportgivenby(String reportgivenby) {
        Reportgivenby = reportgivenby;
    }

    public String getNoofCalls() {
        return NoofCalls;
    }

    public void setNoofCalls(String noofCalls) {
        NoofCalls = noofCalls;
    }

    public String getNoofNonPickedCalls() {
        return NoofNonPickedCalls;
    }

    public void setNoofNonPickedCalls(String noofNonPickedCalls) {
        NoofNonPickedCalls = noofNonPickedCalls;
    }

    public String getNoofNonInterestedCalls() {
        return NoofNonInterestedCalls;
    }

    public void setNoofNonInterestedCalls(String noofNonInterestedCalls) {
        NoofNonInterestedCalls = noofNonInterestedCalls;
    }

    public String getNoofSwitchedoff() {
        return NoofSwitchedoff;
    }

    public void setNoofSwitchedoff(String noofSwitchedoff) {
        NoofSwitchedoff = noofSwitchedoff;
    }

    public String getRequirementsofClients() {
        return RequirementsofClients;
    }

    public void setRequirementsofClients(String requirementsofClients) {
        RequirementsofClients = requirementsofClients;
    }

    public String getDataRequirements() {
        return DataRequirements;
    }

    public void setDataRequirements(String dataRequirements) {
        DataRequirements = dataRequirements;
    }

    public String getNoofAppDevelopments() {
        return NoofAppDevelopments;
    }

    public void setNoofAppDevelopments(String noofAppDevelopments) {
        NoofAppDevelopments = noofAppDevelopments;
    }

    public String getNoofWebsiteDevelopments() {
        return NoofWebsiteDevelopments;
    }

    public void setNoofWebsiteDevelopments(String noofWebsiteDevelopments) {
        NoofWebsiteDevelopments = noofWebsiteDevelopments;
    }

    public String getNoOfPhysicalMeetings() {
        return NoOfPhysicalMeetings;
    }

    public void setNoOfPhysicalMeetings(String noOfPhysicalMeetings) {
        NoOfPhysicalMeetings = noOfPhysicalMeetings;
    }

    public String getNoOfVirtualMeetings() {
        return NoOfVirtualMeetings;
    }

    public void setNoOfVirtualMeetings(String noOfVirtualMeetings) {
        NoOfVirtualMeetings = noOfVirtualMeetings;
    }

    public String getNoOfProspectus() {
        return NoOfProspectus;
    }

    public void setNoOfProspectus(String noOfProspectus) {
        NoOfProspectus = noOfProspectus;
    }

    public String getNoOfFollowups() {
        return NoOfFollowups;
    }

    public void setNoOfFollowups(String noOfFollowups) {
        NoOfFollowups = noOfFollowups;
    }

    public String getNoOfSalesDone() {
        return NoOfSalesDone;
    }

    public void setNoOfSalesDone(String noOfSalesDone) {
        NoOfSalesDone = noOfSalesDone;
    }
}
