package com.example.bds_kzn;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private ApiInterface api;

    public ApiService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://testingsitewil.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ApiInterface.class);
    }


    public Call<List<Event>> getEvents() {
        return api.getEvents();
    }

    public Call<List<Shopping>> getShopping(){
        return api.getShopping();
    }
}
