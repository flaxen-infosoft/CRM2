package com.example.crm.SalesManagement;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crm.CustomToast;
import com.example.crm.Model.LeaveItem;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveApplicationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

	EditText subject, body;
	TextView name, datetime;
	Button send;
	Spinner leavetype;
	int day, month, year, hour, minute;
	int myday, myMonth, myYear, myHour, myMinute;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leave_application);

		subject = findViewById(R.id.subject);
		body = findViewById(R.id.body);
		datetime = findViewById(R.id.dateandtime);
		name = findViewById(R.id.name);
		send = findViewById(R.id.btn_send);
		leavetype = findViewById(R.id.leavetype);

		datetime.setOnClickListener(v -> {
			Calendar calendar = Calendar.getInstance();
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH);
			day = calendar.get(Calendar.DAY_OF_MONTH);
			DatePickerDialog datePickerDialog = new DatePickerDialog(LeaveApplicationActivity.this, LeaveApplicationActivity.this, year, month, day);
			datePickerDialog.show();
		});

		send.setOnClickListener(v -> {
			LeaveItem leave = new LeaveItem();
			leave.setStatus("Na");
			leave.setBody(body.getText().toString());
			leave.setTitle(subject.getText().toString());
			leave.setDate(datetime.getText().toString());

			leave.setLeavetype(leavetype.getSelectedItem().toString());

			insertLeave(leave);

		});


	}

	private void insertLeave(LeaveItem leave) {
		RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);

		Call<JsonObject> call = ri.insertLeave(leave);

		call.enqueue(new Callback<JsonObject>() {
			@Override
			public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
				if (response.isSuccessful()) {
					CustomToast.makeText(LeaveApplicationActivity.this, "done" + response.message(), 0, Color.RED);
				} else {
					CustomToast.makeText(LeaveApplicationActivity.this, "Failed to do" + response.message(), 0, Color.RED);
				}
			}

			@Override
			public void onFailure(Call<JsonObject> call, Throwable t) {
				CustomToast.makeText(LeaveApplicationActivity.this, "Failed to do" + t.getMessage(), 0, Color.RED);
			}
		});
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
		myYear = year;
		myday = day;
		myMonth = month;
		Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR);
		minute = c.get(Calendar.MINUTE);
		TimePickerDialog timePickerDialog = new TimePickerDialog(LeaveApplicationActivity.this, LeaveApplicationActivity.this, hour, minute, DateFormat.is24HourFormat(this));
		timePickerDialog.show();
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		myHour = hourOfDay;
		myMinute = minute;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, myday);
		c.set(Calendar.MONTH, myMonth);
		c.set(Calendar.YEAR, myYear);
		c.set(Calendar.HOUR_OF_DAY, myHour);
		c.set(Calendar.MINUTE, myMinute);

		Date date = c.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm");
		String formatted = formatter.format(date);

		datetime.setText(formatted);

	}
}