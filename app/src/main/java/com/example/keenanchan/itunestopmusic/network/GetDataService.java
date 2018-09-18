package com.example.keenanchan.itunestopmusic.network;

import com.example.keenanchan.itunestopmusic.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/apple-music/coming-soon/all/10/explicit")
    Call<Example> getExample();
}
