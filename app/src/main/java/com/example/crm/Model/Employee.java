package com.example.crm.Model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Employee implements Serializable {
	String id;
	String name;
	String department;
	String designation;
	String phone;
	String date;
	String state;
	String city;
	String oid;
	String pid;
	String password;
	String status;
	String gender;
	String altphone;
	String source;
	String resume;
	String address;
	String have_leptop;
	String college_name;
	String duration;
	String stipend_amount;
	String dateofjion;
	String aadharcard;
	String pancard;
	String markof10th;
	String markof12th;
	String graduation;
	String gradu_certificate;
	String pre_com_certi;
	String cheque;
	String ac_details;
	String offer_letter;
	String non_ds_aggre;
	String verification_form;
	String training_form;
	String official_mail;
	String official_sim;
	String intern_certificate;
	String reevingletter;
	String profileImg;

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAltphone() {
		return altphone;
	}

	public void setAltphone(String altphone) {
		this.altphone = altphone;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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

	public String getHave_leptop() {
		return have_leptop;
	}

	public void setHave_leptop(String have_leptop) {
		this.have_leptop = have_leptop;
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

	public String getStipend_amount() {
		return stipend_amount;
	}

	public void setStipend_amount(String stipend_amount) {
		this.stipend_amount = stipend_amount;
	}

	public String getDateofjion() {
		return dateofjion;
	}

	public void setDateofjion(String dateofjion) {
		this.dateofjion = dateofjion;
	}

	public String getAadharcard() {
		return aadharcard;
	}

	public void setAadharcard(String aadharcard) {
		this.aadharcard = aadharcard;
	}

	public String getPancard() {
		return pancard;
	}

	public void setPancard(String pancard) {
		this.pancard = pancard;
	}

	public String getMarkof10th() {
		return markof10th;
	}

	public void setMarkof10th(String markof10th) {
		this.markof10th = markof10th;
	}

	public String getMarkof12th() {
		return markof12th;
	}

	public void setMarkof12th(String markof12th) {
		this.markof12th = markof12th;
	}

	public String getGraduation() {
		return graduation;
	}

	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}

	public String getGradu_certificate() {
		return gradu_certificate;
	}

	public void setGradu_certificate(String gradu_certificate) {
		this.gradu_certificate = gradu_certificate;
	}

	public String getPre_com_certi() {
		return pre_com_certi;
	}

	public void setPre_com_certi(String pre_com_certi) {
		this.pre_com_certi = pre_com_certi;
	}

	public String getCheque() {
		return cheque;
	}

	public void setCheque(String cheque) {
		this.cheque = cheque;
	}

	public String getAc_details() {
		return ac_details;
	}

	public void setAc_details(String ac_details) {
		this.ac_details = ac_details;
	}

	public String getOffer_letter() {
		return offer_letter;
	}

	public void setOffer_letter(String offer_letter) {
		this.offer_letter = offer_letter;
	}

	public String getNon_ds_aggre() {
		return non_ds_aggre;
	}

	public void setNon_ds_aggre(String non_ds_aggre) {
		this.non_ds_aggre = non_ds_aggre;
	}

	public String getVerification_form() {
		return verification_form;
	}

	public void setVerification_form(String verification_form) {
		this.verification_form = verification_form;
	}

	public String getTraining_form() {
		return training_form;
	}

	public void setTraining_form(String training_form) {
		this.training_form = training_form;
	}

	public String getOfficial_mail() {
		return official_mail;
	}

	public void setOfficial_mail(String official_mail) {
		this.official_mail = official_mail;
	}

	public String getOfficial_sim() {
		return official_sim;
	}

	public void setOfficial_sim(String official_sim) {
		this.official_sim = official_sim;
	}

	public String getIntern_certificate() {
		return intern_certificate;
	}

	public void setIntern_certificate(String intern_certificate) {
		this.intern_certificate = intern_certificate;
	}

	public String getReevingletter() {
		return reevingletter;
	}

	public void setReevingletter(String reevingletter) {
		this.reevingletter = reevingletter;
	}
}
