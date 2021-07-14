package com.example.crm.Model;

public class TestResponse {
    String tid;
    String candidate_id;
    String correction;
    long time_required;
    String remark;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(String candidate_id) {
        this.candidate_id = candidate_id;
    }

    public String getCorrection() {
        return correction;
    }

    public void setCorrection(String correction) {
        this.correction = correction;
    }

    public long getTime_required() {
        return time_required;
    }

    public void setTime_required(long time_required) {
        this.time_required = time_required;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
