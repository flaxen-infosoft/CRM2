package com.example.crm.Retro;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apicontrollerflaxen
{
    private static final String url="https://flaxeninfosoft.com/app_api/";
    private static Apicontrollerflaxen clientObject;
    private static Retrofit retrofit;
    Apicontrollerflaxen()
    {//OkHttpClient client = new OkHttpClient.Builder()
           // .connectTimeout(100, TimeUnit.SECONDS)
           // .readTimeout(100,TimeUnit.SECONDS).build();
        retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
    public static synchronized Apicontrollerflaxen getInstance()
    {
        if(clientObject==null)
            clientObject=new Apicontrollerflaxen();
        return clientObject;

    }
    public RetroInterface getapi()
    { return retrofit.create(RetroInterface.class);

    }


}

