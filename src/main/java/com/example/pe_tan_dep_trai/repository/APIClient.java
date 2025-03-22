package com.example.pe_tan_dep_trai.repository;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static String baseURL = "https://67275ec9270bd0b97551cd40.mockapi.io/";
    private static Retrofit retrofit;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
