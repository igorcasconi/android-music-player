package com.example.testapplicationjava.controllers;

import android.content.Context;

import com.example.testapplicationjava.R;
import com.example.testapplicationjava.interfaces.SpotifyService;
import com.example.testapplicationjava.models.Music;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailMusicController implements SpotifyService {
  private Retrofit apiService;
  private Context contextApplication;


  public DetailMusicController(Context context){
    contextApplication = context;
    OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
    String rapidAPIKey = context.getResources().getString(R.string.rapidapi_key);
    String rapidAPIHost = context.getResources().getString(R.string.rapidapi_host);
    httpBuilder.addInterceptor(new Interceptor() {
      @Override
      public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request request = original.newBuilder()
              .addHeader("X-RapidAPI-Key", rapidAPIKey)
              .addHeader("X-RapidAPI-Host", rapidAPIHost)
              .method(original.method(), original.body())
              .build();

        return chain.proceed(request);
      }
    });

    OkHttpClient client = httpBuilder.build();
    apiService = new Retrofit.Builder()
          .client(client)
          .baseUrl("https://spotify23.p.rapidapi.com/")
          .addConverterFactory(GsonConverterFactory.create())
          .build();
  }

  public SpotifyService getMusic(){
      return this.apiService.create(SpotifyService.class);
  }

  @Override
  public Call<Music> getDetailTrack(String id) {
    Call<Music> call = new DetailMusicController(contextApplication).getMusic().getDetailTrack(id);
    return call;
  }
}
