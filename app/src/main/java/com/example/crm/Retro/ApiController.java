package com.example.crm.Retro;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController
{
    private static final String url="https://creatorsdesire.in/app_api/";
    private static ApiController clientObject;
    private static Retrofit retrofit;
    ApiController()
  { //OkHttpClient client = new OkHttpClient.Builder()
//            .connectTimeout(100, TimeUnit.SECONDS)
//            .readTimeout(100,TimeUnit.SECONDS).build();
        retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
    public static synchronized ApiController getInstance()
    {
        if(clientObject==null)
            clientObject=new ApiController();
        return clientObject;

    }
    public RetroInterface getapi()
    { return retrofit.create(RetroInterface.class);

    }


}

