package com.example.crm.EmployeeManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.crm.Model.Employee;
import com.example.crm.R;

public class EmployeeRegistrationFragment2 extends Fragment implements View.OnClickListener {

	private static final int RESULT_RESUME = 1001;
	private static final int RESULT_ADHAAR_CARD = 1002;
	private static final int RESULT_PAN_CARD = 1003;
	private static final int RESULT_SSC_MARKSHEET = 1004;
	private static final int RESULT_HSC_MARKSHEET = 1005;
	private static final int RESULT_GRADUATION_CERTIFICATE = 1006;
	private static final int RESULT_PREVIOUS_COMPANY_CERTIFICATE = 1007;
	private static final int RESULT_CHEQUE = 1008;
	private static final int RESULT_OFFER_LETTER = 1009;
	private static final int RESULT_NDA = 1010;
	private static final int RESULT_VERIFICATION_FORM = 1011;
	private static final int RESULT_TRAINING_FORM = 1012;
	private static final int RESULT_INTERNSHIP_CERTIFICATE = 1013;
	private static final int RESULT_RELEVING_LETTER = 1014;

	LinearLayout resume, adhaar, pan, ssc, hsc, graduation, precomcertificate, cheque, offerletter, nda, verificationform, trainingform, interncertificate, relevingletter;

	Button button;
	View v;
	Employee employee;

	public EmployeeRegistrationFragment2() {
		// Required empty public constructor
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	public void OnClick(View v) {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		if (v == null) {
			v = inflater.inflate(R.layout.fragment_employee_registration2, container, false);
			resume = v.findViewById(R.id.resume);
			adhaar = v.findViewById(R.id.adhaar);
			pan = v.findViewById(R.id.pan);
			ssc = v.findViewById(R.id.ssc);
			hsc = v.findViewById(R.id.hsc);
			graduation = v.findViewById(R.id.graduation);
			precomcertificate = v.findViewById(R.id.precomcertificate);
			cheque = v.findViewById(R.id.cheque);
			offerletter = v.findViewById(R.id.offerletter);
			nda = v.findViewById(R.id.nda);
			verificationform = v.findViewById(R.id.verificationform);
			trainingform = v.findViewById(R.id.trainingform);
			interncertificate = v.findViewById(R.id.interncertificate);
			relevingletter = v.findViewById(R.id.relevingletter);
			button = v.findViewById(R.id.btn_register);

			resume.setOnClickListener(this);
			adhaar.setOnClickListener(this);
			pan.setOnClickListener(this);
			ssc.setOnClickListener(this);
			hsc.setOnClickListener(this);
			graduation.setOnClickListener(this);
			precomcertificate.setOnClickListener(this);
			cheque.setOnClickListener(this);
			offerletter.setOnClickListener(this);
			relevingletter.setOnClickListener(this);
			nda.setOnClickListener(this);
			verificationform.setOnClickListener(this);
			trainingform.setOnClickListener(this);
			interncertificate.setOnClickListener(this);
			button.setOnClickListener(this);



			/*
			resume;
			adhaar;
			pan;
			ssc;
			hsc;
			graduation;
			precomcertificate;
			cheque;
			offerletter;
			nda;
			verificationform;
			trainingform;
			interncertificate;
			relevingletter;
			*/

		}
		return v;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.resume:

				filePicker(RESULT_RESUME);
				break;
			case R.id.adhaar:
				filePicker(RESULT_ADHAAR_CARD);
				break;
			case R.id.pan:
				filePicker(RESULT_PAN_CARD);
				break;
			case R.id.ssc:
				filePicker(RESULT_SSC_MARKSHEET);
				break;
			case R.id.hsc:
				filePicker(RESULT_HSC_MARKSHEET);
				break;
			case R.id.graduation:
				filePicker(RESULT_GRADUATION_CERTIFICATE);
				break;
			case R.id.precomcertificate:
				filePicker(RESULT_PREVIOUS_COMPANY_CERTIFICATE);
				break;
			case R.id.cheque:
				filePicker(RESULT_CHEQUE);
				break;
			case R.id.offerletter:
				filePicker(RESULT_OFFER_LETTER);
				break;
			case R.id.nda:
				filePicker(RESULT_NDA);
				break;
			case R.id.verificationform:
				filePicker(RESULT_VERIFICATION_FORM);
				break;
			case R.id.trainingform:
				filePicker(RESULT_TRAINING_FORM);
				break;
			case R.id.interncertificate:
				filePicker(RESULT_INTERNSHIP_CERTIFICATE);
				break;
			case R.id.relevingletter:
				filePicker(RESULT_RELEVING_LETTER);
				break;
			case R.id.btn_register:
				((EmployeeRegistrationActvity) getContext()).postEmployee();
				break;
		}

	}

	public void updateTextView(int requestCode, String filename) {
		switch (requestCode) {
			case RESULT_RESUME:
				((TextView) resume.findViewById(R.id.txt)).setText(filename);
				break;
			case RESULT_ADHAAR_CARD:
				((TextView) adhaar.findViewById(R.id.txt)).setText(filename);
				break;
			case RESULT_PAN_CARD:
				((TextView) pan.findViewById(R.id.txt)).setText(filename);
				break;
			case RESULT_SSC_MARKSHEET:
				((TextView) ssc.findViewById(R.id.txt)).setText(filename);
				break;
			case RESULT_HSC_MARKSHEET:
				((TextView) hsc.findViewById(R.id.txt)).setText(filename);
				break;
			case RESULT_GRADUATION_CERTIFICATE:
				((TextView) graduation.findViewById(R.id.txt)).setText(filename);
				break;
			case RESULT_PREVIOUS_COMPANY_CERTIFICATE:
				((TextView) precomcertificate.findViewById(R.id.txt)).setText(filename);
				break;
			case RESULT_CHEQUE:
				((TextView) cheque.findViewById(R.id.txt)).setText(filename);
				break;
			case RESULT_OFFER_LETTER:
				((TextView) offerletter.findViewById(R.id.txt)).setText(filename);
				break;
			case RESULT_NDA:
				((TextView) nda.findViewById(R.id.txt)).setText(filename);
				break;
			case RESULT_VERIFICATION_FORM:
				((TextView) verificationform.findViewById(R.id.txt)).setText(filename);
				break;
			case RESULT_TRAINING_FORM:
				((TextView) trainingform.findViewById(R.id.txt)).setText(filename);
				break;
			case RESULT_INTERNSHIP_CERTIFICATE:
				((TextView) interncertificate.findViewById(R.id.txt)).setText(filename);
				break;
			case RESULT_RELEVING_LETTER:
				((TextView) relevingletter.findViewById(R.id.txt)).setText(filename);
				break;
		}
	}

	private void filePicker(int requestCode) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("application/pdf");
		intent = Intent.createChooser(intent, "Choose file");
		getActivity().startActivityForResult(intent, requestCode);
	}


}