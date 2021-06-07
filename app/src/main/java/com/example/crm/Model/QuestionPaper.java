package com.example.crm.Model;

import java.util.ArrayList;

public class QuestionPaper {
	String uid;
	ArrayList<Question> questions;

	public QuestionPaper() {
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
}
