package com.example.crm.Retro;

import com.example.crm.Model.Candidate;
import com.example.crm.Model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RetroInterface {
    @POST("candidate_registerapi.php")
    Call<Candidate> addCandidate(@Body Candidate candidate);

    @PUT("update_candidate.php")
    Call<Candidate> uodateCandidate(@Body Candidate candidate);

    @GET("get_candidate.php")
    Call<List<Candidate>> getcandidate();

    @POST("employee_registerapi.php")
    Call<Employee> addEmployee(@Body Employee employee);
}
