package com.example.keenanchan.itunestopmusic.network;

import com.example.keenanchan.itunestopmusic.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("us/apple-music/coming-soon/all/10/explicit.json")
    Call<Example> getExample();
}
