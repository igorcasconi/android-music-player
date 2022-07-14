package com.example.testapplicationjava.interfaces;

import com.example.testapplicationjava.R;
import com.example.testapplicationjava.models.Music;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface SpotifyService {

  @GET("tracks/")
  @Headers("Content-Type: application/json")
  Call<Music> getDetailTrack(@Query("ids") String id);
}
