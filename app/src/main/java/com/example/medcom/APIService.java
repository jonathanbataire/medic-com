package com.example.medcom;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface APIService {
    String BASE_URL = "https://maps.googleapis.com";

    @GET("/maps/api/place/nearbysearch/json")
    Call<JsonObject> getPlaces(@Query("location") String location,
                               @Query("types") String type,
                               @Query("sensor") Boolean sensor,
                               @Query("rankby") String rankBy,
                               @Query("keyword") String keyWord,
                               @Query("key") String key);
}