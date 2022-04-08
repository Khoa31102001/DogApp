package com.example.myapplication.network;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {
    private static final String BASE_URL = "https://raw.githubusercontent.com/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitClient() {
        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
