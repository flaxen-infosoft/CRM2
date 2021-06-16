package com.example.crm.HRManagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Model.Question;
import com.example.crm.Model.QuestionPaperAdapter;
import com.example.crm.R;

import java.util.ArrayList;

public class AptitudeTestFragment extends Fragment {
	View v;
	RecyclerView rv;
	QuestionPaperAdapter qpa;
	ArrayList<Question> q;

	public AptitudeTestFragment() {
		// Required empty public constructor
	}

	public AptitudeTestFragment(ArrayList<Question> q) {
		this.q = q;
	}

	public int getCorrectAnswers() {
		int cnt = 0;
		for (Question que : q) {
			if (que.getAns() == que.getCorrectAns())
				cnt++;
		}
		return cnt;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		qpa = new QuestionPaperAdapter(q, getContext());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		if (v == null) {
			v = inflater.inflate(R.layout.fragment_aptitude_test, container, false);
			rv = v.findViewById(R.id.recyclerView);
			rv.setLayoutManager(new LinearLayoutManager(getContext()));
			rv.setAdapter(qpa);
		}
		return v;
	}
}