package com.example.testapplicationjava.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.testapplicationjava.R;
import com.example.testapplicationjava.adapters.TrackAdapter;
import com.example.testapplicationjava.controllers.MusicController;
import com.example.testapplicationjava.models.Tracks;
import com.example.testapplicationjava.models.TrackData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
  MusicController apiService;
  private RecyclerView trackListRecyclerView;
  private TrackAdapter trackAdapter;
  private Tracks trackListData;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    trackListRecyclerView = findViewById(R.id.trackList);
    LinearLayoutManager linearLayout = new LinearLayoutManager(this);
    trackListRecyclerView.setLayoutManager(linearLayout);


    apiService = new MusicController(this);
    Call<Tracks> callService = apiService.getTrackList("imagine", 10, 5, "tracks");
    callService.enqueue(new Callback<Tracks>() {
      @Override
      public void onResponse(Call<Tracks> call, Response<Tracks> response) {
        trackListData = response.body();
        List<TrackData> items = trackListData.getTracks().getItems();
        trackAdapter = new TrackAdapter(getApplicationContext(), items);
        trackListRecyclerView.setAdapter(trackAdapter);
      }

      @Override
      public void onFailure(Call<Tracks> call, Throwable t) {
        System.out.println(t.getMessage());
      }
    });
  }
}