package com.example.crm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crm.EmployeeManagement.EmployeeDashboardActivity;
import com.example.crm.Model.Employee;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;
import com.example.crm.SalesManagement.SalesMainActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
	Button login;
	Spinner employee_id;
	EditText mobile, password;
	RetroInterface ri;
	ProgressDialog loading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		login = findViewById(R.id.login_button);
		employee_id = findViewById(R.id.employee_id);
		mobile = findViewById(R.id.mobile);
		password = findViewById(R.id.password);
		ri = Retrofi.initretro().create(RetroInterface.class);
		loading = CustomProgressAlert.make(this, "Verifying...");


		if (SPOps.getLoggedUser(this) != null) {
			startActivity(SPOps.getId(this));
		}
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
						verify(m, pass, 0);
						break;
					case "Website Development":
						verify(m, pass, 1);
						break;
					case "Sales":
						verify(m, pass, 2);
						break;
					case "Digital Marketing":
						verify(m, pass, 3);
						break;
					case "UI UX Designer":
						verify(m, pass, 4);
						break;
					case "Graphic Designer":
						verify(m, pass, 5);
						break;
					case "Admin":
						verify(m, pass, 6);
						break;
				}

			}
		});
	}

	private void verify(String mobile, String password, int id) {
		loading.show();
		Call<ArrayList<Employee>> call = ri.getEmployeeByPhone(mobile);
		call.enqueue(new Callback<ArrayList<Employee>>() {
			@Override
			public void onResponse(Call<ArrayList<Employee>> call, Response<ArrayList<Employee>> response) {
				if (response.isSuccessful()) {
					loading.dismiss();
					if (response.body().size() == 0) {
						CustomToast.makeText(LoginActivity.this, "No such user exists.", 0, Color.RED);
					} else {
						Employee te = response.body().get(0);
						if (mobile.equals(te.getPhone()) && password.equals(te.getPassword())) {
							SPOps.loggedIn(te, id, LoginActivity.this);
							startActivity(id);
						} else {
							CustomToast.makeText(LoginActivity.this, "Credential doesn't match :(", 0, Color.RED);
						}
					}
				} else {
					CustomToast.makeText(LoginActivity.this, "Failed :(", 0, Color.RED);
				}
			}

			@Override
			public void onFailure(Call<ArrayList<Employee>> call, Throwable t) {
				loading.dismiss();
				CustomToast.makeText(LoginActivity.this, "Failed :(", 0, Color.RED);
			}
		});
	}

	private void startActivity(int id) {
		switch (id) {
			case 0:
			case 1:
			case 3:
			case 4:
			case 5:
				startActivity(new Intent(LoginActivity.this, EmployeeDashboardActivity.class));
				finish();
				break;
			case 2:
				startActivity(new Intent(LoginActivity.this, SalesMainActivity.class));
				finish();
				break;
			case 6:
				startActivity(new Intent(LoginActivity.this, HomeActivity.class));
				finish();
				break;

		}
	}
}