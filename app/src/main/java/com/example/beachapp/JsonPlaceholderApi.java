package com.example.beachapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceholderApi {
    @GET("/~dpsd16066/{filename}")  //ΕΔΩ ΜΑΛΛΟΝ ΕΙΝΑΙ ΤΟ ΛΑΘΟΣ
    Call<BeachesRes> getAllBeaches(@Path("filename") String filename);
}
