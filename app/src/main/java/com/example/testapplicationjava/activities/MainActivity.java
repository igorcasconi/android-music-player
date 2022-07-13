package com.example.testapplicationjava.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.testapplicationjava.R;
import com.example.testapplicationjava.models.Music;
import com.example.testapplicationjava.services.ServiceSpotify;
import com.example.testapplicationjava.utils.DownloadImageTask;
import com.example.testapplicationjava.utils.TimeString;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

  private ImageView musicImage;
  private TextView nameMusic, nameArtist, popularityNumber, releaseDate, durationMusic;
  private final String baseURL = "https://spotify23.p.rapidapi.com/";
  Map<String, String> headers = new HashMap<String, String>();
  private RequestQueue queue;
  private Music musicData = new Music();
  private String rapidKey ;
  private String rapidHost;
  private Button playAndPauseButton, spotifyButton;
  private ViewStub progressScreen;
  private TimeString timeString = new TimeString();
  MediaPlayer mediaPlayer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    rapidKey = this.getResources().getString(R.string.rapidapi_key);
    rapidHost = this.getResources().getString(R.string.rapidapi_host);
    musicImage = findViewById(R.id.musicImage);
    musicImage.setImageResource(R.drawable.sound_waves);
    nameMusic = findViewById(R.id.nameMusic);
    nameArtist = findViewById(R.id.nameArtist);
    popularityNumber = findViewById(R.id.popularityNumber);
    releaseDate = findViewById(R.id.releaseDate);
    mediaPlayer = new MediaPlayer();
    playAndPauseButton = findViewById(R.id.playAndPauseButton);
    durationMusic = findViewById(R.id.durationMusic);
    Drawable playButtonImage = getApplicationContext().getResources().getDrawable(R.drawable.play_icon_outlined);
    Drawable pauseButtonImage = getApplicationContext().getResources().getDrawable(R.drawable.pause_icon);
    playButtonImage.setBounds(0, 0, 90, 90);
    pauseButtonImage.setBounds(0, 0, 90, 90);
    progressScreen = findViewById(R.id.viewProgressStub);
    spotifyButton = findViewById(R.id.spotifyButton);
    progressScreen.inflate();
    this.getTrack();

    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
      @Override
      public void onCompletion(MediaPlayer mediaPlayer) {
        setPlayButton(playButtonImage, "PLAY PREVIEW");
      }
    });

    playAndPauseButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {

        if (mediaPlayer.isPlaying()) {
          mediaPlayer.pause();
          setPlayButton(playButtonImage, "PLAY PREVIEW");
        } else {
          mediaPlayer.start();
          mediaPlayer.start();
          setPlayButton(pauseButtonImage, "PAUSE PREVIEW");
        }
      }
    });
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
                String uriStringSong = trackFirst.getString("preview_url");
                int popularity = trackFirst.getInt("popularity");
                Uri uriSong = Uri.parse(uriStringSong);
                String releaseDate = album.getString("release_date");
                JSONObject externalUrl = trackFirst.getJSONObject("external_urls");
                String musicPage = externalUrl.getString("spotify");
                long durationMusic = trackFirst.getLong("duration_ms");
                String durationInMinutes = timeString.convertMsInMinutes(durationMusic);

                musicData.setNameSong(nameMusic);
                musicData.setNameArtist(nameArtist);
                musicData.setImageSong(imageURL);
                musicData.setUrlSong(uriSong);
                musicData.setPopularity(popularity);
                musicData.setReleaseDate(releaseDate);
                musicData.setMusicPage(musicPage);
                musicData.setDurationSong(durationInMinutes);
                setContentOnActivity();
              } catch (JSONException | ParseException e) {
                e.printStackTrace();
              } finally {
                progressScreen.setVisibility(View.GONE);
              }
            }
          }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "Ocorreu um erro ao buscar a música selecionada!", Toast.LENGTH_SHORT);
      }
    });

    queue.add(request);
  }

  public void setContentOnActivity() {
    nameMusic.setText(musicData.getNameSong());
    nameArtist.setText(musicData.getNameArtist());
    popularityNumber.setText(String.valueOf(musicData.getPopularity()));
    releaseDate.setText(musicData.getReleaseDate());
    durationMusic.setText(musicData.getDurationSong());
    new DownloadImageTask(musicImage).execute(musicData.getImageSong());
    try {
      mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
      mediaPlayer.setDataSource(getApplicationContext(), musicData.getUrlSong());
      mediaPlayer.prepare();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setPlayButton(Drawable iconImage, String buttonText) {
    playAndPauseButton.setText(buttonText);
    playAndPauseButton.setCompoundDrawables(iconImage, null, null, null);
  }

  public void openExternalURL(View view){
      Uri spotifyPage = Uri.parse(musicData.getMusicPage());
      Intent intent = new Intent(Intent.ACTION_VIEW, spotifyPage);
      startActivity(intent);
  }

}