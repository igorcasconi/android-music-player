package com.example.testapplicationjava.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testapplicationjava.R;
import com.example.testapplicationjava.adapters.TrackAdapter;
import com.example.testapplicationjava.controllers.MusicController;
import com.example.testapplicationjava.models.TrackItems;
import com.example.testapplicationjava.models.Tracks;
import com.example.testapplicationjava.models.TrackData;
import com.example.testapplicationjava.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity {
  MusicController apiService;
  private RecyclerView trackListRecyclerView;
  private TrackAdapter trackAdapter;
  private Tracks trackData;
  private EditText inputSearch;
  private ViewStub progressList;
  private ViewStub emptyList;
  private final static String emptySearchInput = "Não há nada para pesquisar!";
  private final static String typeSearch = "tracks";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    trackListRecyclerView = findViewById(R.id.trackList);
    LinearLayoutManager linearLayout = new LinearLayoutManager(this);
    trackListRecyclerView.setLayoutManager(linearLayout);
    inputSearch = findViewById(R.id.searchInputText);
    progressList = findViewById(R.id.viewProgressListStub);
    progressList.inflate();
    progressList.setVisibility(View.GONE);
    emptyList = findViewById(R.id.viewEmptyListStub);
    emptyList.inflate();
    apiService = new MusicController(this);

    trackListRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), trackListRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
      @Override
      public void onItemClick(View view, int position) {
        Intent intentHomeActivity = new Intent(HomeActivity.this, MainActivity.class);
        List<TrackData> trackItems = trackData.getTracks().getItems();
        String trackId = trackItems.get(position).getData().getId();
        intentHomeActivity.putExtra("trackId", trackId);
        startActivity(intentHomeActivity);
      }

      @Override
      public void onLongItemClick(View view, int position) {}

      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {}
    }));

  }

  public void onSearchMusic(View view) {
    String searchContent = inputSearch.getText().toString();
    progressList.setVisibility(View.VISIBLE);

    if (searchContent.isEmpty()) {
      Toast.makeText(getApplicationContext(), emptySearchInput, Toast.LENGTH_SHORT).show();
    }

    Call<Tracks> callService = apiService.getTrackList(searchContent, 10, 5, typeSearch);
    callService.enqueue(new Callback<Tracks>() {
      @Override
      public void onResponse(Call<Tracks> call, Response<Tracks> response) {
        trackData = response.body();
        TrackItems trackListData = trackData.getTracks();
        List<TrackData> itemsList = new ArrayList<TrackData>();

        if (trackListData != null) {
          itemsList = trackListData.getItems();
          emptyList.setVisibility(View.GONE);
        } else {
          emptyList.setVisibility(View.VISIBLE);
        }

        trackAdapter = new TrackAdapter(getApplicationContext(), itemsList);
        trackListRecyclerView.setAdapter(trackAdapter);
        progressList.setVisibility(View.GONE);
      }

      @Override
      public void onFailure(Call<Tracks> call, Throwable t) {
        progressList.setVisibility(View.GONE);
        System.out.println(t.getMessage());
      }
    });
  }
}