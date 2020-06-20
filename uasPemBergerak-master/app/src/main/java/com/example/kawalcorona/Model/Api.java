package com.example.kawalcorona.Model;

import com.example.kawalcorona.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    public static String url = "https://api.kawalcorona.com/";
    public static Service service(){
        Retrofit r = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        Service s  = r.create(Service.class);
        return s;
    }
}
