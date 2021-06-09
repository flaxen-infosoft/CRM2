package com.example.crm.HRManagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.crm.R;

public class SecondRoundFragment extends Fragment {

	View v = null;

	public SecondRoundFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		if (v == null)
			v = inflater.inflate(R.layout.fragment_second_round, container, false);
		return v;
	}
}