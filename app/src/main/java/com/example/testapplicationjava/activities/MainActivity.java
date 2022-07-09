package com.example.testapplicationjava.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.testapplicationjava.R;
import com.example.testapplicationjava.classes.Music;
import com.example.testapplicationjava.services.ServiceSpotify;
import com.example.testapplicationjava.utils.DownloadImageTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

  private ImageView musicImage;
  private TextView nameMusic;
  private final String baseURL = "https://spotify23.p.rapidapi.com/";
  private final String rapidKey = "9cAltS2JMymsh4XDwsgmYKHA6wpHp1LE1RqjsnEestrQ6wR9Ev";
  private final String rapidHost = "spotify23.p.rapidapi.com";
  Map<String, String> headers = new HashMap<String, String>();
  private RequestQueue queue;
  private Music musicData = new Music();;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    this.getTrack();

    musicImage = findViewById(R.id.musicImage);
    musicImage.setImageResource(R.drawable.sound_waves);
    nameMusic = findViewById(R.id.nameMusic);
  }

  public void getTrack(){
    headers.put("X-RapidAPI-Key", rapidKey);
    headers.put("X-RapidAPI-Host", rapidHost);
    queue = Volley.newRequestQueue(this);
    String url = baseURL + "tracks/?ids=4WNcduiCmDNfmTEz7JvmLv";
    ServiceSpotify request = new ServiceSpotify(url, headers, null,
          new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
              try {
                JSONArray tracks = response.getJSONArray("tracks");
                JSONObject trackFirst = tracks.getJSONObject(0);
                JSONObject album = trackFirst.getJSONObject("album");
                JSONArray artists = album.getJSONArray("artists");
                JSONObject principalArtist = artists.getJSONObject(0);
                String nameArtist = principalArtist.getString("name");
                String nameMusic = album.getString("name");
                JSONArray images = album.getJSONArray("images");
                JSONObject imageData = images.getJSONObject(0);
                String imageURL = imageData.getString("url");

                musicData.setNameSong(nameMusic);
                musicData.setNameArtist(nameArtist);
                musicData.setImageSong(imageURL);
                setTextOnActivity();
              } catch (JSONException e) {
                e.printStackTrace();
              }
            }
          }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        // TODO
      }
    });

    queue.add(request);
  }

  public void setTextOnActivity(){
    nameMusic.setText(musicData.getNameSong());
    new DownloadImageTask(musicImage).execute(musicData.getImageSong());
    System.out.println(musicData.getNameArtist());
  }

}