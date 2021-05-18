package com.example.crm.HRManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.crm.CRMManagement.CRM_RegisterActivity;
import com.example.crm.R;
import com.example.crm.citystate.Cities;
import com.example.crm.citystate.Rinterface;
import com.example.crm.citystate.object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CandidateRegistration extends AppCompatActivity  {

    EditText name, phone, alt_phone, personal_email, official_email, source, address;
    Spinner city, state;
    Button btn_update;
    Spinner stateSpin, citySpin;
    List<String> stateList = new ArrayList<>();
    List<String> cityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_registration);

        name = findViewById(R.id.candidate_name);
        phone = findViewById(R.id.candidate_phone);
        alt_phone = findViewById(R.id.candidate_alt_phone);
        personal_email = findViewById(R.id.candidate_personal_email);
        official_email = findViewById(R.id.candidate_official_email);
        source = findViewById(R.id.candidate_source);
        address = findViewById(R.id.candidate_address);
        btn_update = findViewById(R.id.candidate_resume);

        stateSpin = findViewById(R.id.state);
        citySpin = findViewById(R.id.city);


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
                stateSpin.setAdapter(new ArrayAdapter<>(CandidateRegistration.this, android.R.layout.simple_spinner_dropdown_item, filteredStateList));
                stateSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String state = stateSpin.getSelectedItem().toString();
                        cityList.clear();
                        for (Cities cities1 : cities) {
                            if (cities1.getState().equals(state)) {
                                cityList.add(cities1.getCity());
                            }
                        }
                        List<String> filteredCityList = removeDuplicates(cityList);
                        Collections.sort(filteredCityList);
                        filteredCityList.add(0, "Select City");
                        citySpin.setAdapter(new ArrayAdapter<>(CandidateRegistration.this, android.R.layout.simple_spinner_dropdown_item, filteredCityList));

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
                Intent intent = new Intent(CandidateRegistration.this, NewCandidateActivity.class);
                startActivity(intent);
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