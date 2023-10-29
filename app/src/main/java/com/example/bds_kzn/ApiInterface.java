package com.example.bds_kzn;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("Events/GetAll")
    Call<List<Event>> getEvents();

    @GET("Shopping/GetAll")
    Call<List<Shopping>> getShopping();
}

