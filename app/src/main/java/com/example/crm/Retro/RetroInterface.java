package com.example.crm.Retro;

import com.example.crm.Model.Candidate;
import com.example.crm.Model.Employee;
import com.example.crm.Model.QuestionPaper;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RetroInterface {
	@POST("candidate_registerapi.php")
	Call<Candidate> addCandidate(@Body Candidate candidate);

	@PUT("update_candidate.php")
	Call<Candidate> updateCandidate(@Body Candidate candidate);

	@PUT("shortlisted_candidateupdateapi.php")
	Call<Candidate> updateShortlisedCandidate(@Body Candidate candidate);

	@GET("get_candidate.php")
	Call<List<Candidate>> getcandidate();

	@POST("employee_registerapi.php")
	Call<Employee> addEmployee(@Body Employee employee);

	@GET("/question_paper")
	Call<QuestionPaper> getQuestionPaper();

	@GET("get_short_candidate.php")
	Call<ArrayList<Candidate>> getAllShortlistedCandidates();

	@POST("shortlisted_candidateapi.php")
	Call<JsonObject> shortListCandidate(@Query("id") String id);

	@GET("get_prefer_candidate.php")
	Call<ArrayList<Candidate>> getPreferCandidate();

	@POST("insert_prefer_candidate.php")
	Call<JsonObject> preferCandidate(@Body Candidate candidate);

}
