package com.example.crm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

//    private static final String BASE_URL = "http://192.168.43.53/college/";
//    private static ApiClient apiClient;
//    private static Retrofit retrofit;
//
//    private OkHttpClient.Builder builder = new OkHttpClient.Builder();
//
//    private HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//
//    public ApiClient(){
//
//        Gson gson = new GsonBuilder().setLenient().create();
//
//        interceptor.level(HttpLoggingInterceptor.Level.BODY);
//        builder.addInterceptor(interceptor);
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(builder.build())
//                .build();
//    }
//
//    public static synchronized ApiClient getInstance(){
//        if (apiClient == null){
//            apiClient = new ApiClient();
//        }
//        return apiClient;
//    }
//
//    public ApiInterface getApi(){
//        return retrofit.create(ApiInterface.class);
//    }
}
