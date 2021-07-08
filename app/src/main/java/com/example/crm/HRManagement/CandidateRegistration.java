package com.example.crm.HRManagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.crm.CustomToast;
import com.example.crm.Model.Candidate;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;
import com.example.crm.SPOps;
import com.example.crm.citystate.Cities;
import com.example.crm.citystate.Rinterface;
import com.example.crm.citystate.object;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CandidateRegistration extends AppCompatActivity {

	private static final String[] departments = {"Select Department",
			"HR Management",
			"Development",
			"Sales",
			"Digital Marketing",
			"Designer"};
	private static final String[] designations = {"Select Designation",
			"HR",
			"Android APP Development",
			"Website Development",
			"Sales",
			"Digital Marketing",
			"UI UX Designer", "Graphic Designer"
	};

	private static final int[][] mappings = {{0}, {1}, {2, 3}, {4}, {5}, {6, 7}};

	TextView filename;
	EditText name, phone, alt_phone, personal_email, official_email, source, address;
	Spinner city, state, department, designation;
	Button btn_update, btn_upresume;
	List<String> stateList = new ArrayList<String>();
	List<String> cityList = new ArrayList<String>();
	ArrayAdapter departmentsAdapter, designationsAdapter;
	List<String> tempList;
	private String resumepdf;

	@SuppressLint("WrongViewCast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_candidate_registration);

		name = findViewById(R.id.name_of_candidate);
		phone = findViewById(R.id.candidate_phoneno_);
		alt_phone = findViewById(R.id.candidate_alter_phone);
		personal_email = findViewById(R.id.candidateemail);
		official_email = findViewById(R.id.candidate_portfolio);
		source = findViewById(R.id.candidate_sourceof);
		address = findViewById(R.id.candidate_addressline);
		designation = findViewById(R.id.candidate_designation);
		department = findViewById(R.id.candidate_department);
		btn_update = findViewById(R.id.candidate_resume);
		btn_upresume = findViewById(R.id.uploadresume);
		city = findViewById(R.id.cityspin);
		state = findViewById(R.id.statespin);
		filename = findViewById(R.id.filename);
		tempList = new ArrayList<>();
		departmentsAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, departments);
		departmentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		department.setAdapter(departmentsAdapter);
		designationsAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, tempList);
		designationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		designation.setAdapter(designationsAdapter);
		department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				tempList.clear();
				for (int a : mappings[position]) {
					tempList.add(designations[a]);
				}
				designationsAdapter.notifyDataSetChanged();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		btn_upresume.setOnClickListener(view -> {
			filePicker();
		});
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://raw.githubusercontent.com/fayazara/Indian-Cities-API/master/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		Rinterface rinterface = retrofit.create(Rinterface.class);
		Call<object> call = rinterface.getObject();
		call.enqueue(new Callback<object>() {
			@Override
			public void onResponse(Call<object> call, Response<object> response) {
				if (!response.isSuccessful()) {
					System.out.println("response.code() = " + response.code());
				}

				List<Cities> cities = response.body().getCities();
				for (Cities cities1 : cities) {
					stateList.add(cities1.getState());
				}
				List<String> filteredStateList = removeDuplicates(stateList);
				Collections.sort(filteredStateList);
				filteredStateList.add(0, "Select State");
				state.setAdapter(new ArrayAdapter<>(CandidateRegistration.this, android.R.layout.simple_spinner_dropdown_item, filteredStateList));
				state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						String state1 = state.getSelectedItem().toString();
						cityList.clear();
						for (Cities cities1 : cities) {
							if (cities1.getState().equals(state1)) {
								cityList.add(cities1.getCity());
							}
						}
						List<String> filteredCityList = removeDuplicates(cityList);
						Collections.sort(filteredCityList);
						filteredCityList.add(0, "Select City");
						city.setAdapter(new ArrayAdapter<>(CandidateRegistration.this, android.R.layout.simple_spinner_dropdown_item, filteredCityList));

					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});
			}

			@Override
			public void onFailure(Call<object> call, Throwable t) {
				System.out.println("t.getMessage() = " + t.getMessage());
			}
		});


		btn_update.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Check();
			}
		});

	}

	private void filePicker() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("application/pdf");
		intent = Intent.createChooser(intent, "Choose file");
		startActivityForResult(intent, 101);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
			Uri path = data.getData();
			InputStream inputStream = null;
			try {
				inputStream = CandidateRegistration.this.getContentResolver().openInputStream(path);
				byte[] pdfinByte = new byte[inputStream.available()];
				inputStream.read(pdfinByte);
				resumepdf = Base64.encodeToString(pdfinByte, Base64.DEFAULT);
				getFilename(path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private void getFilename(Uri path) {
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

		filename.setText(result);
		filename.setTextColor(Color.BLACK);

	}

	private void Check() {
		String canname = name.getText().toString();
		String canphone = phone.getText().toString();
		String canpersonalemail = personal_email.getText().toString();
		String canaltphone = alt_phone.getText().toString();
		String canofficialemail = official_email.getText().toString();
		String canaddress = address.getText().toString();
		String cansource = source.getText().toString();
		String canstate = state.getSelectedItem().toString();
		String cancity = city.getSelectedItem().toString();
		String candepatment = department.getSelectedItem().toString();
		if (canphone.length() != 10) {
			phone.setError("Please Enter Valid Phone Number ");
			phone.requestFocus();
		} else if (canname.isEmpty()) {
			name.setError("Please Enter  Name");
			name.requestFocus();
		} else if (!Patterns.EMAIL_ADDRESS.matcher(canpersonalemail).matches()) {
			personal_email.setError("Please Enter a Valid Email Address");
			personal_email.requestFocus();
		} else if (canaddress.isEmpty()) {
			address.setError("Please Enter a Address");
			address.requestFocus();

		} else if (cansource.isEmpty()) {
			source.setError("Please Enter Your Password");
			source.requestFocus();

		} else if (!canofficialemail.contains("@")) {
			official_email.setError("Please Enter a Valid Email Address");
			official_email.requestFocus();
		} else if (canaltphone.length() != 10) {
			alt_phone.setError("Please Enter Valid Phone Number ");
			alt_phone.requestFocus();
		} else if (canstate.contains("Select State")) {
			Toast.makeText(this, "Please Select State", Toast.LENGTH_SHORT).show();
		} else if (cancity.contains("Select City")) {
			Toast.makeText(this, "Please Select City", Toast.LENGTH_SHORT).show();
		} else {

			Candidate candidate = new Candidate();
			candidate.setName(canname);
			candidate.setAddress(canaddress);
			candidate.setAltphone(canaltphone);
			candidate.setState(canstate);
			candidate.setCity(cancity);
			candidate.setPhone(canphone);
			candidate.setSource(cansource);
			candidate.setPid(canpersonalemail);
			candidate.setOid(canofficialemail);
			candidate.setResume(resumepdf);
			candidate.setDepartment(candepatment);
			candidate.setDesignation(designation.getSelectedItem().toString());
			candidate.setAssignedBy(SPOps.getLoggedInUserName(this));
			CandidateRegister(candidate);


		}
	}

	private void CandidateRegister(Candidate candidate) {
		RetroInterface retroInterface = Retrofi.initretro().create(RetroInterface.class);
		Call<Candidate> call = retroInterface.addCandidate(candidate);
		call.enqueue(new Callback<Candidate>() {
			@Override
			public void onResponse(Call<Candidate> call, Response<Candidate> response) {
				if (!response.isSuccessful()) {
					System.out.println(response.code());
				} else {
					CustomToast.makeText(CandidateRegistration.this, "Candidate Registered", 0, Color.parseColor("#32CD32"));
					Intent i = new Intent(CandidateRegistration.this, NewCandidateActivity.class);
					startActivity(i);
					finish();
				}
			}

			@Override
			public void onFailure(Call<Candidate> call, Throwable t) {
				System.out.println(t.getMessage());
				CustomToast.makeText(CandidateRegistration.this, "Candidate Not Registered", 0, Color.RED);

			}
		});
	}

	private List<String> removeDuplicates(List<String> stateList) {
		List<String> statesList = new ArrayList<>();
		for (String state : stateList) {
			if (!statesList.contains(state)) {
				statesList.add(state);
			}
		}
		return statesList;
	}
}