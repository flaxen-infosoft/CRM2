package com.example.crm.Model;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Question {
	String que;
	@Nullable
	int ans;
	ArrayList<String> options;

	public Question() {
	}

	public String getQue() {
		return que;
	}

	public void setQue(String que) {
		this.que = que;
	}

	public int getAns() {
		return ans;
	}

	public void setAns(int ans) {
		this.ans = ans;
	}

	public ArrayList<String> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}
}
