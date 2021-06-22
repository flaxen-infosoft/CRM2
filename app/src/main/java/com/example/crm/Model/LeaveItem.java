package com.example.crm.Model;

public class LeaveItem {

	String title, body, date, status, leavetype, deptHr, deptHr1, teamLead, teamMember;
	int duration;

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLeavetype() {
		return leavetype;
	}

	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}

	public String getDeptHr() {
		return deptHr;
	}

	public void setDeptHr(String deptHr) {
		this.deptHr = deptHr;
	}

	public String getDeptHr1() {
		return deptHr1;
	}

	public void setDeptHr1(String deptHr1) {
		this.deptHr1 = deptHr1;
	}

	public String getTeamLead() {
		return teamLead;
	}

	public void setTeamLead(String teamLead) {
		this.teamLead = teamLead;
	}

	public String getTeamMember() {
		return teamMember;
	}

	public void setTeamMember(String teamMember) {
		this.teamMember = teamMember;
	}
}
