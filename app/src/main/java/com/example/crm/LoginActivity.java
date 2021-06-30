package com.example.crm;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crm.Model.Employee;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
	Button login;
	Spinner employee_id;
	EditText mobile, password;
	RetroInterface ri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		login = findViewById(R.id.login_button);
		employee_id = findViewById(R.id.employee_id);
		mobile = findViewById(R.id.mobile);
		password = findViewById(R.id.password);
		ri = Retrofi.initretro().create(RetroInterface.class);

		login.setOnClickListener(v ->
		{
			String m = mobile.getText().toString();
			String pass = password.getText().toString();
			if (employee_id.getSelectedItem().toString().equals("Select Department")) {
				CustomToast.makeText(LoginActivity.this, "Please select department", 0, Color.RED);
			} else if (m.isEmpty() || pass.isEmpty()) {
				CustomToast.makeText(LoginActivity.this, "Mobile and password can't be empty.", 0, Color.RED);
			} else {
				switch (employee_id.getSelectedItem().toString()) {
					case "Android APP Development":
						break;
					case "Website Development":
						break;
					case "Sales":
						break;
					case "Digital Marketing":
						break;
					case "UI UX Designer":
						break;
					case "Graphic Designer":
						break;
					case "Admin":
						verifyAdmin(m, pass);
						break;
				}

			}
		});
	}

	private void verifyAdmin(String mobile, String password) {
		Call<ArrayList<Employee>> call = ri.getEmployeeById(155);
		call.enqueue(new Callback<ArrayList<Employee>>() {
			@Override
			public void onResponse(Call<ArrayList<Employee>> call, Response<ArrayList<Employee>> response) {
				if (response.isSuccessful()) {
					Employee te = response.body().get(0);
					if (mobile.equals(te.getPhone()) && password.equals(te.getPassword())) {
						startActivity(new Intent(LoginActivity.this, HomeActivity.class));
					} else {
						CustomToast.makeText(LoginActivity.this, "Credential doesn't match :(", 0, Color.RED);
					}

				} else {
					CustomToast.makeText(LoginActivity.this, "Failed :(", 0, Color.RED);
				}
			}

			@Override
			public void onFailure(Call<ArrayList<Employee>> call, Throwable t) {
				CustomToast.makeText(LoginActivity.this, "Failed :(", 0, Color.RED);
			}
		});


	}
}