package com.example.crm.EmployeeManagement;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.crm.Model.Employee;
import com.example.crm.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EmployeeRegistrationFragment1 extends Fragment implements DatePickerDialog.OnDateSetListener {

	View v;
	Employee employee;
	EditText name, designation, phone, altphone, date, state, city, pid, oid, officialsim, address, password, status, clgname, duration, stipend;
	Button next;
	Spinner gender;


	public EmployeeRegistrationFragment1() {
		// Required empty public constructor
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		if (v == null) {
			v = inflater.inflate(R.layout.fragment_employee_registration1, container, false);
			name = v.findViewById(R.id.name);
			designation = v.findViewById(R.id.designation);
			phone = v.findViewById(R.id.phone);
			altphone = v.findViewById(R.id.altphone);
			date = v.findViewById(R.id.date);
			state = v.findViewById(R.id.state);
			city = v.findViewById(R.id.city);
			pid = v.findViewById(R.id.pid);
			oid = v.findViewById(R.id.oid);
			officialsim = v.findViewById(R.id.officialsim);
			address = v.findViewById(R.id.address);
			password = v.findViewById(R.id.password);
			status = v.findViewById(R.id.status);
			clgname = v.findViewById(R.id.clgname);
			duration = v.findViewById(R.id.duration);
			stipend = v.findViewById(R.id.stipend);
			next = v.findViewById(R.id.btn_next);
			gender = v.findViewById(R.id.gender);

			next.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					employee.setName(name.getText().toString());
					employee.setDesignation(designation.getText().toString());
					employee.setPhone(phone.getText().toString());
					employee.setAltphone(altphone.getText().toString());
					employee.setDate(date.getText().toString());
					employee.setState(state.getText().toString());
					employee.setCity(city.getText().toString());
					employee.setPid(pid.getText().toString());
					employee.setOid(oid.getText().toString());
					employee.setOfficial_sim(officialsim.getText().toString());
					employee.setAddress(address.getText().toString());
					employee.setPassword(password.getText().toString());
					employee.setStatus(status.getText().toString());
					employee.setCollege_name(clgname.getText().toString());
					employee.setDuration(duration.getText().toString());
					employee.setStipend_amount(stipend.getText().toString());
					employee.setGender(gender.getSelectedItem().toString());

					((EmployeeRegistrationActvity) getContext()).switchFragment();

				}
			});

			date.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Calendar calendar = Calendar.getInstance();
					DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), EmployeeRegistrationFragment1.this::onDateSet, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
					datePickerDialog.show();
				}
			});

		}
		if (v != null) {
			name.setText(employee.getName());
			designation.setText(employee.getDesignation());
			phone.setText(employee.getPhone());
			altphone.setText(employee.getAltphone());
			date.setText(employee.getDate());
			state.setText(employee.getState());
			city.setText(employee.getCity());
			pid.setText(employee.getPid());
			oid.setText(employee.getOid());
			officialsim.setText(employee.getOfficial_sim());
			address.setText(employee.getAddress());
			password.setText(employee.getPassword());
			status.setText(employee.getStatus());
			clgname.setText(employee.getCollege_name());
			duration.setText(employee.getDuration());
			stipend.setText(employee.getStipend_amount());
		}
		return v;
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DATE, dayOfMonth);
		Date _date = c.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		String formatted = formatter.format(date);
		date.setText(formatted);
	}
}