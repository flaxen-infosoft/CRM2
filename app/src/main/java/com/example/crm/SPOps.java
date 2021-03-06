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
    public static int getid( Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        int variable  = sp.getInt("localid", 4);
       return variable;
    }

    public static void loggedIn(Employee employee, int id, Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        sp.edit().putString("loggedInUser", gson.toJson(employee)).putInt("localid", id).putString("name", employee.getName()).putString("globalid", employee.getId()).apply();
    }

    public static int getLoggedInUserLocalId(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getInt("localid", -1);
    }

    public static String getLoggedInUserGlobalId(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString("globalid", null);
    }


    public static String getLoggedInUserName(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString("name", null);
    }

    public static void logout(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }

}
