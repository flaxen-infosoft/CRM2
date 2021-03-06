package com.example.crm.Retro;

import com.example.crm.Model.Candidate;
import com.example.crm.Model.Customer;
import com.example.crm.Model.Employee;
import com.example.crm.Model.LeaveItem;
import com.example.crm.Model.QuestionPaper;
import com.example.crm.Model.Report;
import com.example.crm.Model.TestResponse;
import com.example.crm.Model.UpcomingCustomer;
import com.example.crm.SalesManagement.Callsinfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RetroInterface {
    @POST("insert_candidate.php")
    Call<Candidate> addCandidate(@Body Candidate candidate);

    @GET("get_candidate.php")
    Call<List<Candidate>> getcandidate();

    @PUT("update_candidate.php")
    Call<Candidate> updateCandidate(@Body Candidate candidate);

    @GET("/question_paper")
    Call<QuestionPaper> getQuestionPaper();

    @POST("insert_test_res.php")
    Call<JsonObject> postTestResponse(@Body TestResponse testResponse);

    @GET("get_short_candidate.php")
    Call<ArrayList<Candidate>> getAllShortlistedCandidates();

    @POST("insert_short_candidate.php")
    Call<JsonObject> shortListCandidate(@Body Candidate id);

    @PUT("shortlisted_candidateupdateapi.php")
    Call<Candidate> updateShortlisedCandidate(@Body Candidate candidate);

    @POST("insert_prefer_candidate.php")
    Call<JsonObject> preferCandidate(@Body Candidate candidate);

    @GET("get_prefer_candidate.php")
    Call<ArrayList<Candidate>> getPreferCandidate();

    @POST("insert_emp1.php")
    Call<JsonObject> addEmployee(@Body Employee employee);

    @GET("get_employee.php")
    Call<ArrayList<Employee>> getEmployee();

    @GET("get_emp_by_id.php")
    Call<ArrayList<Employee>> getEmployeeById(@Query("id") int id);

    @GET("get_emp_by_mobileno.php")
    Call<ArrayList<Employee>> getEmployeeByPhone(@Query("phone") String phone);

    @PUT("update_employee.php")
    Call<Employee> updateEmployee(@Body Employee employee);

    @POST("insert_leave.php")
    Call<LeaveItem> insertLeave(@Body LeaveItem leaveItem);

    @GET("get_leave.php")
    Call<ArrayList<LeaveItem>> getLeaves();

    @PUT("update_leave.php")
    Call<LeaveItem> updateLeave(@Body LeaveItem leaveItem);

    @POST("insert_c.php")
    Call<JsonObject> insertCustomer(@Body Customer customer);

    @GET("get_all_customer.php")
    Call<ArrayList<Customer>> getAllCustomers();

    @POST("insert_upcoming_customer.php")
    Call<UpcomingCustomer> insertupcoming(@Body UpcomingCustomer upcomingCustomer);

    @GET("get_all_upcoming_customer.php")
    Call<List<UpcomingCustomer>> getAllUpcomingCustomer();

    @POST("insert_report.php")
    Call<Report> insertreport(@Body Report report);

    @GET("get_All_Report.php")
    Call<List<Report>> getReports();

    @GET("get_test_res.php")
    Call<TestResponse> getTestResponse(@Query("candidate_id") String candidateID);
    @POST("insertpayment.php")
    Call<JsonObject> insertpayment(@Query("lid") int lid,@Query("sid") int sid,@Query("amount") String amount,@Query("mode") String mode);
@POST("insertcall.php")
Call<JsonObject> insertcall(@Query("lid") int lid,@Query("sid") int sid,@Query("duration") String duration);
@GET("getreport.php")
Call<ArrayList<Callsinfo>> getReport(@Query("sid") int sid);
@GET("getcallbydate.php")
Call<ArrayList<Customer>> getcallbydate(@Query("sid") int sid,@Query("date") String date);
@GET("getclientcallbydate.php")
Call<ArrayList<Customer>> getclientcallbydate(@Query("sid") int sid,@Query("date") String date);
@GET("getpaymentbydate.php")
Call<ArrayList<Customer>> getpaymentbydate(@Query("date") String date);
@POST("insertmeeting.php")
Call<JsonArray> insertmeeting(@Query("Sid") int Sid, @Query("Lid") int Lid,@Query("name") String name, @Query("title") String title);

    @POST("insertleave.php")
    Call<JsonObject> insertleave(@Query("emp_id") int emp_id,@Query("date") String date,@Query("type") String type,@Query("message") String message);
    @GET("getclient.php")
    Call<ArrayList<Customer>> getleads();
    @GET("getleads.php")
    Call<ArrayList<Customer>> getleadsCustomer();
    @GET("getmeeting.php")
    Call<ArrayList<Customer>> getmeeting();
    @PUT("resetcall.php")
    Call<Customer> updatecall(@Query("id")int id);
@PUT("uploadproposal.php")
Call<ResponsePOJO> uploadProposal(@Body Customer c);
    @PUT("uploadinvoice.php")
    Call<ResponsePOJO> uploadinvoice(@Body Customer c);
    @PUT("updateamount.php")
    Call<Customer> updateamount(@Query("lid") int id,@Query("amount") String amount);
    @POST("insertclient.php")
    Call<Customer> insertclient(@Query("lid") int id);

    @PUT("updateleads.php")
    Call<Customer> updateLeads(@Query("id")int id,@Query("remark") String remark);
    @DELETE("deleteleads.php")
    Call<Customer> deleteleads(@Query("id")int id);
    @PUT("updatecallcount.php")
    Call<Customer> updatecallcount(@Query("id")int id);



}

