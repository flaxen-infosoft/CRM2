package com.example.crm.Model;

import java.util.ArrayList;

public class TestResponse {
	String candidateID;
	ArrayList<Integer> correctAns;
	long timeRequired;
	String remark;

	public TestResponse() {
		correctAns = new ArrayList<>();
	}

	public String getCandidateID() {
		return candidateID;
	}

	public void setCandidateID(String candidateID) {
		this.candidateID = candidateID;
	}

	public ArrayList<Integer> getCorrectAns() {
		return correctAns;
	}

	public void setCorrectAns(ArrayList<Integer> correctAns) {
		this.correctAns = correctAns;
	}

	public long getTimeRequired() {
		return timeRequired;
	}

	public void setTimeRequired(long timeRequired) {
		this.timeRequired = timeRequired;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
