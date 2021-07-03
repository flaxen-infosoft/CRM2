package com.example.crm.EmployeeManagement;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.crm.CustomToast;
import com.example.crm.HRManagement.PdfViewerActivity;
import com.example.crm.Model.Employee;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;
import com.google.android.material.button.MaterialButton;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityNewJoinee extends AppCompatActivity implements View.OnClickListener {

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
	Employee employee, newEmployee;
	TextView name, dept, date;
	MaterialButton vadhar, uadhar, vpan, upan, vssc, ussc, vhsc, uhsc, vgrad, ugrad, vpcc, upcc, vcheque, ucheque, vgradcer, ugradcer;
	MaterialButton vol, uol, vnda, unda, vvf, uvf, vtf, utf, vic, uic, vrl, url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_joinee);

		employee = (Employee) getIntent().getSerializableExtra("employee");

		name = findViewById(R.id.name);
		dept = findViewById(R.id.dept);
		date = findViewById(R.id.date);

		vol = findViewById(R.id.vol);
		uol = findViewById(R.id.uol);
		vnda = findViewById(R.id.vnda);
		unda = findViewById(R.id.unda);
		vvf = findViewById(R.id.vvf);
		uvf = findViewById(R.id.uvf);
		vtf = findViewById(R.id.vtf);
		utf = findViewById(R.id.utf);
		vic = findViewById(R.id.vic);
		uic = findViewById(R.id.uic);
		vrl = findViewById(R.id.vrl);
		url = findViewById(R.id.url);

		vol.setOnClickListener(this);
		uol.setOnClickListener(this);
		vnda.setOnClickListener(this);
		unda.setOnClickListener(this);
		vvf.setOnClickListener(this);
		uvf.setOnClickListener(this);
		vtf.setOnClickListener(this);
		utf.setOnClickListener(this);
		vic.setOnClickListener(this);
		uic.setOnClickListener(this);
		vrl.setOnClickListener(this);
		url.setOnClickListener(this);

		vadhar = findViewById(R.id.vadhar);
		uadhar = findViewById(R.id.uadhar);
		vpan = findViewById(R.id.vpan);
		upan = findViewById(R.id.upan);
		vssc = findViewById(R.id.vssc);
		ussc = findViewById(R.id.ussc);
		vhsc = findViewById(R.id.vhsc);
		uhsc = findViewById(R.id.uhsc);
		vgrad = findViewById(R.id.vgrad);
		ugrad = findViewById(R.id.ugrad);
		vpcc = findViewById(R.id.vpcc);
		upcc = findViewById(R.id.upcc);
		vcheque = findViewById(R.id.vcheque);
		ucheque = findViewById(R.id.ucheque);
		vgradcer = findViewById(R.id.vgradcer);
		ugradcer = findViewById(R.id.ugradcer);

		name.setText("Name: " + employee.getName());
		date.setText("Date of Joining: " + employee.getDate());
		dept.setText("Department: " + employee.getDesignation());

		vadhar.setOnClickListener(this);
		uadhar.setOnClickListener(this);
		vpan.setOnClickListener(this);
		upan.setOnClickListener(this);
		vssc.setOnClickListener(this);
		ussc.setOnClickListener(this);
		vhsc.setOnClickListener(this);
		uhsc.setOnClickListener(this);
		vgrad.setOnClickListener(this);
		ugrad.setOnClickListener(this);
		vpcc.setOnClickListener(this);
		upcc.setOnClickListener(this);
		vcheque.setOnClickListener(this);
		ucheque.setOnClickListener(this);
		vgradcer.setOnClickListener(this);
		ugradcer.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.uadhar:
				filePicker(RESULT_ADHAAR_CARD);
				break;
			case R.id.upan:
				filePicker(RESULT_PAN_CARD);
				break;
			case R.id.ussc:
				filePicker(RESULT_SSC_MARKSHEET);
				break;
			case R.id.uhsc:
				filePicker(RESULT_HSC_MARKSHEET);
				break;
			case R.id.ugradcer:
				filePicker(RESULT_GRADUATION_CERTIFICATE);
				break;
			case R.id.upcc:
				filePicker(RESULT_PREVIOUS_COMPANY_CERTIFICATE);
				break;
			case R.id.ucheque:
				filePicker(RESULT_CHEQUE);
				break;

			case R.id.uol:
				filePicker(RESULT_OFFER_LETTER);
				break;
			case R.id.unda:
				filePicker(RESULT_NDA);
				break;
			case R.id.uvf:
				filePicker(RESULT_VERIFICATION_FORM);
				break;
			case R.id.utf:
				filePicker(RESULT_TRAINING_FORM);
				break;
			case R.id.uic:
				filePicker(RESULT_INTERNSHIP_CERTIFICATE);
				break;
			case R.id.url:
				filePicker(RESULT_RELEVING_LETTER);
				break;

			case R.id.vadhar:
				pdfView(employee.getAadharcard());
				break;
			case R.id.vpan:
				pdfView(employee.getPancard());
				break;
			case R.id.vssc:
				pdfView(employee.getMarkof10th());
				break;
			case R.id.vhsc:
				pdfView(employee.getMarkof12th());
				break;
			case R.id.vgradcer:
				pdfView(employee.getGradu_certificate());
				break;
			case R.id.vpcc:
				pdfView(employee.getPre_com_certi());
				break;
			case R.id.vcheque:
				pdfView(employee.getCheque());
				break;

			case R.id.vol:
				pdfView(employee.getOffer_letter());
				break;
			case R.id.vnda:
				pdfView(employee.getNon_ds_aggre());
				break;
			case R.id.vvf:
				pdfView(employee.getVerification_form());
				break;
			case R.id.vtf:
				pdfView(employee.getTraining_form());
				break;
			case R.id.vic:
				pdfView(employee.getIntern_certificate());
				break;
			case R.id.vrl:
				pdfView(employee.getReevingletter());
				break;

		}
	}

	private void pdfView(String url) {
		Intent i = new Intent(ActivityNewJoinee.this, PdfViewerActivity.class);
		i.putExtra("pdfurl", url);
		startActivity(i);
	}

	private void filePicker(int requestCode) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("application/pdf");
		intent = Intent.createChooser(intent, "Choose file");
		startActivityForResult(intent, requestCode);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && data != null) {
			Uri path = data.getData();
			InputStream inputStream = null;
			String file = null, filename = null;

			try {
				inputStream = ActivityNewJoinee.this.getContentResolver().openInputStream(path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			byte[] pdfinByte = new byte[0];
			try {
				pdfinByte = new byte[inputStream.available()];
				inputStream.read(pdfinByte);
				file = Base64.encodeToString(pdfinByte, Base64.DEFAULT);
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			newEmployee = new Employee();
			newEmployee.setId(employee.getId());
			switch (requestCode) {
				case RESULT_ADHAAR_CARD:
					newEmployee.setAadharcard(file);
					break;
				case RESULT_PAN_CARD:
					newEmployee.setPancard(file);
					break;
				case RESULT_SSC_MARKSHEET:
					newEmployee.setMarkof10th(file);
					break;
				case RESULT_HSC_MARKSHEET:
					newEmployee.setMarkof12th(file);
					break;
				case RESULT_GRADUATION_CERTIFICATE:
					newEmployee.setGradu_certificate(file);
					break;
				case RESULT_PREVIOUS_COMPANY_CERTIFICATE:
					newEmployee.setPre_com_certi(file);
					break;
				case RESULT_CHEQUE:
					newEmployee.setCheque(file);
					break;

				case RESULT_OFFER_LETTER:
					newEmployee.setOffer_letter(file);
					break;
				case RESULT_NDA:
					newEmployee.setNon_ds_aggre(file);
					break;
				case RESULT_VERIFICATION_FORM:
					newEmployee.setVerification_form(file);
					break;
				case RESULT_TRAINING_FORM:
					newEmployee.setTraining_form(file);
					break;
				case RESULT_INTERNSHIP_CERTIFICATE:
					newEmployee.setIntern_certificate(file);
					break;
				case RESULT_RELEVING_LETTER:
					newEmployee.setReevingletter(file);
					break;

			}
			updateEmployee(newEmployee);
		}


	}

	private void updateEmployee(Employee newEmployee) {
		RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
		Call<Employee> call = ri.updateEmployee(newEmployee);
		call.enqueue(new Callback<Employee>() {
			@Override
			public void onResponse(Call<Employee> call, Response<Employee> response) {
				if (response.isSuccessful()) {
					//success
				} else {
					CustomToast.makeText(ActivityNewJoinee.this, "Failed :("+response.code(), 0, Color.RED);
				}
			}

			@Override
			public void onFailure(Call<Employee> call, Throwable t) {
				CustomToast.makeText(ActivityNewJoinee.this, "Failed :("+t.getMessage(), 0, Color.RED);
			}
		});
	}

}
