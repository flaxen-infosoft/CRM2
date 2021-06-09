package com.example.crm.HRManagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.crm.R;

public class BlackListedFragment extends Fragment {
	View v;

	public BlackListedFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		if (v == null)
			v = inflater.inflate(R.layout.fragment_black_listed, container, false);
		return v;
	}
}