package com.example.testapplicationjava.interfaces;

import com.example.testapplicationjava.models.Music;
import com.example.testapplicationjava.models.Tracks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface SpotifyService {

  @GET("tracks/")
  @Headers("Content-Type: application/json")
  Call<Music> getDetailTrack(@Query("ids") String id);

  @GET("search/")
  @Headers("Content-Type: application/json")
  Call<Tracks> getTrackList(
        @Query("q") String query,
        @Query("limit") int limit,
        @Query("numberOfTopResults") int numberResults,
        @Query("type") String type);
}
