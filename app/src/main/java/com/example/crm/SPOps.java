package com.example.crm;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.crm.Model.Employee;
import com.google.gson.Gson;

public class SPOps {
	public static Employee getLoggedUser(Context context) {
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		String employeeString = sp.getString("loggedInUser", null);
		if (employeeString == null) {
			return null;
		} else {
			Gson gson = new Gson();
			return gson.fromJson(employeeString, Employee.class);
		}
	}

	public static void loggedIn(Employee employee, int id, Context context) {
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		Gson gson = new Gson();
		sp.edit().putString("loggedInUser", gson.toJson(employee)).putInt("id", id).apply();
	}

	public static int getId(Context context) {
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getInt("id", -1);
	}
}
