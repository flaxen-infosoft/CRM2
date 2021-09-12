package com.example.crm.SalesManagement;

import com.example.crm.SalesManagement.model.ClientListItem;
import com.example.crm.SalesManagement.model.Meeting;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroInterface {

    @GET("getmeeting.php")
    Call<ArrayList<Meeting>> fetchMeetings();

    @GET("getclient.php")
    Call<ArrayList<ClientListItem>> fetchClients();

}
