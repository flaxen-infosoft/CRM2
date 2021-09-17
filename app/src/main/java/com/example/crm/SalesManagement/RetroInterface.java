package com.example.crm.SalesManagement;

import com.example.crm.SalesManagement.model.ClientListItem;
import com.example.crm.SalesManagement.model.Meeting;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetroInterface {

    @GET("getmeeting.php")
    Call<ArrayList<Meeting>> fetchMeetings();

    @GET("getclient.php")
    Call<ArrayList<ClientListItem>> fetchClients();

    @POST("insertmeeting.php")
    Call<ArrayList<Meeting>> createMeeting(@Query("title") String title,
                                           @Query("cilentid") String clientid,
                                           @Query("salesid") String salesid);
}
