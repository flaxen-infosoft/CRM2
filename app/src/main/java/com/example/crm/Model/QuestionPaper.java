package com.example.crm.Model;

import java.util.ArrayList;
import java.util.Arrays;

public class QuestionPaper {
	String uid;
	ArrayList<Question> aptitiueQuestions, logicalQuestions, technicalQuestions, literatureQuestions;

	public QuestionPaper() {
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public ArrayList<Question> getAptitiueQuestions() {
		return aptitiueQuestions;
	}

	public void setAptitiueQuestions(ArrayList<Question> aptitiueQuestions) {
		this.aptitiueQuestions = aptitiueQuestions;
	}

	public ArrayList<Question> getLogicalQuestions() {
		return logicalQuestions;
	}

	public void setLogicalQuestions(ArrayList<Question> logicalQuestions) {
		this.logicalQuestions = logicalQuestions;
	}

	public ArrayList<Question> getTechnicalQuestions() {
		return technicalQuestions;
	}

	public void setTechnicalQuestions(ArrayList<Question> technicalQuestions) {
		this.technicalQuestions = technicalQuestions;
	}

	public ArrayList<Question> getLiteratureQuestions() {
		return literatureQuestions;
	}

	public void setLiteratureQuestions(ArrayList<Question> literatureQuestions) {
		this.literatureQuestions = literatureQuestions;
	}

	public void init() throws CloneNotSupportedException {
		aptitiueQuestions = new ArrayList<>();
		logicalQuestions = new ArrayList<>();
		technicalQuestions = new ArrayList<>();
		literatureQuestions = new ArrayList<>();
		Question q = new Question();
		q.setQue("What is 3+5?");
		q.setOptions(new ArrayList<>(Arrays.asList("6", "7", "8", "9")));
		q.setCorrectAns(2);
		aptitiueQuestions.add((Question) q.clone());
		logicalQuestions.add((Question) q.clone());
		technicalQuestions.add((Question) q.clone());
		literatureQuestions.add((Question) q.clone());
		q.setQue("What is 9/10?");
		q.setOptions(new ArrayList<>(Arrays.asList("0.9", "0.8", "0.6", "0.7")));
		q.setCorrectAns(0);
		aptitiueQuestions.add((Question) q.clone());
		logicalQuestions.add((Question) q.clone());
		technicalQuestions.add((Question) q.clone());
		literatureQuestions.add((Question) q.clone());
		q.setQue("What is sqrt(25)?");
		q.setOptions(new ArrayList<>(Arrays.asList("1", "3", "4", "5")));
		q.setCorrectAns(3);
		aptitiueQuestions.add((Question) q.clone());
		logicalQuestions.add((Question) q.clone());
		technicalQuestions.add((Question) q.clone());
		literatureQuestions.add((Question) q.clone());
		q.setQue("What is |-x|?");
		q.setOptions(new ArrayList<>(Arrays.asList("x", "-x", "can not be determined", "invalid")));
		q.setCorrectAns(0);
		aptitiueQuestions.add((Question) q.clone());
		logicalQuestions.add((Question) q.clone());
		technicalQuestions.add((Question) q.clone());
		literatureQuestions.add((Question) q.clone());
		q.setQue("What is sqrt(-25)?");
		q.setOptions(new ArrayList<>(Arrays.asList("5", "-5", "both", "imaginary")));
		q.setCorrectAns(3);
		aptitiueQuestions.add((Question) q.clone());
		logicalQuestions.add((Question) q.clone());
		technicalQuestions.add((Question) q.clone());
		literatureQuestions.add((Question) q.clone());
		q.setQue("What are root of x^2 + 2x + 1?");
		q.setOptions(new ArrayList<>(Arrays.asList("1", "-1", "both", "no of the above")));
		q.setCorrectAns(1);
		aptitiueQuestions.add((Question) q.clone());
		logicalQuestions.add((Question) q.clone());
		technicalQuestions.add((Question) q.clone());
		literatureQuestions.add((Question) q.clone());
	}
}
