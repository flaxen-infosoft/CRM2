package com.example.crm.Model;


import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import lombok.Data;

import static android.graphics.Typeface.BOLD;

@Data
public class Candidate {

    String id;
    String department;
    String designation;
    String name;
    String phone;
    String altphone;
    String pid;
    String oid;
    String source;
    String state;
    String city;
    String status;
    String resume;
    String address;
    String remarks;
    String have_laptop;
    String applied_for;
    String college_name;
    String duration;
    String stipend;
    String amount;
    String eligibility;
    String dateof_interview;
    String assignedBy;

    public Candidate() {
        this.remarks = "[]";
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAltphone() {
        return altphone;
    }

    public void setAltphone(String altphone) {
        this.altphone = altphone;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public SpannableStringBuilder getRemarks() {
        Log.e("123", remarks);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        Gson gson = new Gson();
        ArrayList<Remark> remarks = gson.fromJson(this.remarks.replace("&quot;", "\""), new TypeToken<ArrayList<Remark>>() {
        }.getType());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        for (Remark remark : remarks) {
            int offset = builder.length();

            String txt = remark.getText();
            String author = remark.getAuthor() + " " + simpleDateFormat.format(remark.getDate());
            builder.append(txt);
            builder.append("\n- " + author);
            builder.setSpan(new StyleSpan(BOLD), offset + txt.length(), offset + txt.length() + author.length() + 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            builder.append("\n\n");
        }
        return builder;
    }

    public void setRemarks(Remark remark) {
        Gson gson = new Gson();
        ArrayList<Remark> remarks = gson.fromJson(this.remarks.replace("&quot;", "\""), new TypeToken<ArrayList<Remark>>() {
        }.getType());
        remarks.add(remark);

        this.remarks = gson.toJson(remarks);
    }

    public String getHave_laptop() {
        return have_laptop;
    }

    public void setHave_laptop(String have_laptop) {
        this.have_laptop = have_laptop;
    }

    public String getApplied_for() {
        return applied_for;
    }

    public void setApplied_for(String applied_for) {
        this.applied_for = applied_for;
    }

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStipend() {
        return stipend;
    }

    public void setStipend(String stipend) {
        this.stipend = stipend;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getDateof_interview() {
        return dateof_interview;
    }

    public void setDateof_interview(String dateof_interview) {
        this.dateof_interview = dateof_interview;
    }
}
