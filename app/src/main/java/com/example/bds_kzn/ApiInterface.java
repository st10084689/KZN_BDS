package com.example.bds_kzn;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("events")
    Call<eventsResponse> getEvents();

    @GET("products")
    Call<shoppingResponse> getShopping();
}

