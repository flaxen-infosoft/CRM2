package com.example.crm.EmployeeManagement;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.crm.CustomToast;
import com.example.crm.Model.Employee;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeRegistrationActvity extends AppCompatActivity {

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
	Employee employee;
	ViewPager pager;
	EmployeeRegistrationFragment1 frag1;
	EmployeeRegistrationFragment2 frag2;
	ViewPagerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee_registration_actvity);

		employee = (Employee) getIntent().getSerializableExtra("employee");
		pager = findViewById(R.id.viewPager);
		frag1 = new EmployeeRegistrationFragment1();
		frag1.setEmployee(employee);
		frag2 = new EmployeeRegistrationFragment2();
		frag2.setEmployee(employee);
		adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
		adapter.add(frag1);
		adapter.add(frag2);
		pager.setAdapter(adapter);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && data != null) {
			Uri path = data.getData();
			InputStream inputStream = null;
			String file = null, filename = null;

			try {
				inputStream = EmployeeRegistrationActvity.this.getContentResolver().openInputStream(path);
				byte[] pdfinByte = new byte[inputStream.available()];
				inputStream.read(pdfinByte);
				file = Base64.encodeToString(pdfinByte, Base64.DEFAULT);
				filename = getFilename(path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			switch (requestCode) {
				case RESULT_RESUME:
					employee.setResume(file);
					frag2.updateTextView(requestCode, filename);
					break;
				case RESULT_ADHAAR_CARD:
					employee.setAadharcard(file);
					frag2.updateTextView(requestCode, filename);
					break;
				case RESULT_PAN_CARD:
					employee.setPancard(file);
					frag2.updateTextView(requestCode, filename);
					break;
				case RESULT_SSC_MARKSHEET:
					employee.setMarkof10th(file);
					frag2.updateTextView(requestCode, filename);
					break;
				case RESULT_HSC_MARKSHEET:
					employee.setMarkof12th(file);
					frag2.updateTextView(requestCode, filename);
					break;
				case RESULT_GRADUATION_CERTIFICATE:
					employee.setGradu_certificate(file);
					frag2.updateTextView(requestCode, filename);
					break;
				case RESULT_PREVIOUS_COMPANY_CERTIFICATE:
					employee.setPre_com_certi(file);
					frag2.updateTextView(requestCode, filename);
					break;
				case RESULT_CHEQUE:
					employee.setCheque(file);
					frag2.updateTextView(requestCode, filename);
					break;
				case RESULT_OFFER_LETTER:
					employee.setOffer_letter(file);
					frag2.updateTextView(requestCode, filename);
					break;
				case RESULT_NDA:
					employee.setNon_ds_aggre(file);
					frag2.updateTextView(requestCode, filename);
					break;
				case RESULT_VERIFICATION_FORM:
					employee.setVerification_form(file);
					frag2.updateTextView(requestCode, filename);
					break;
				case RESULT_TRAINING_FORM:
					employee.setTraining_form(file);
					frag2.updateTextView(requestCode, filename);
					break;
				case RESULT_INTERNSHIP_CERTIFICATE:
					employee.setIntern_certificate(file);
					frag2.updateTextView(requestCode, filename);
					break;
				case RESULT_RELEVING_LETTER:
					employee.setReevingletter(file);
					frag2.updateTextView(requestCode, filename);
					break;

			}
		}


	}

	public void postEmployee() {
		RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
		Call<JsonObject> call = ri.addEmployee(employee);
		call.enqueue(new Callback<JsonObject>() {
			@Override
			public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
				if (response.isSuccessful()) {
					Intent intent = new Intent(EmployeeRegistrationActvity.this, EmployeeListActivity.class);
					startActivity(intent);
				} else {
					CustomToast.makeText(EmployeeRegistrationActvity.this, "Failed to perform action" + response.message(), 0, Color.RED);
				}
			}

			@Override
			public void onFailure(Call<JsonObject> call, Throwable t) {
				CustomToast.makeText(EmployeeRegistrationActvity.this, "Failed to perform action" + t.getMessage(), 0, Color.RED);
			}
		});
	}

	private String getFilename(Uri path) {
		String result = "";
		if (path.getScheme().equals("content")) {
			Cursor cursor = getContentResolver().query(path, null, null, null, null);
			try {
				if (cursor != null && cursor.moveToFirst()) {
					result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
				}
			} finally {
				cursor.close();
			}
		}
		if (result == null) {
			result = path.getPath();
			int cut = result.lastIndexOf('/');
			if (cut != -1) {
				result = result.substring(cut + 1);
			}
		}
		return result;

	}

	public void switchFragment() {
		pager.setCurrentItem(1, true);
	}


	public class ViewPagerAdapter extends FragmentPagerAdapter {

		private final ArrayList<Fragment> fragments = new ArrayList<>();

		public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
			super(fm, behavior);
		}

		@NonNull
		@Override
		public Fragment getItem(int position) {
			return fragments.get(position);
		}

		public void add(Fragment fragment) {
			fragments.add(fragment);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}
	}
}