package com.example.testapplicationjava.views;

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

import com.example.testapplicationjava.R;
import com.example.testapplicationjava.controllers.MusicController;
import com.example.testapplicationjava.models.Album;
import com.example.testapplicationjava.models.Artists;
import com.example.testapplicationjava.models.ExternalURLS;
import com.example.testapplicationjava.models.Images;
import com.example.testapplicationjava.models.Music;
import com.example.testapplicationjava.models.AlbumTracks;
import com.example.testapplicationjava.utils.DownloadImageTask;
import com.example.testapplicationjava.utils.TimeString;

import java.io.IOException;
import java.text.ParseException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  private ImageView musicImage;
  private TextView nameMusicTextView, nameArtistTextView, popularityNumberTextView, releaseDateTextView, durationMusicTextView;
  private Music musicData = new Music();
  private Button playAndPauseButton, spotifyButton;
  private ViewStub progressScreen;
  private TimeString timeString = new TimeString();
  MediaPlayer mediaPlayer;
  MusicController apiService;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    musicImage = findViewById(R.id.musicImage);
    musicImage.setImageResource(R.drawable.sound_waves);
    nameMusicTextView = findViewById(R.id.nameMusic);
    nameArtistTextView = findViewById(R.id.nameArtist);
    popularityNumberTextView = findViewById(R.id.popularityNumber);
    releaseDateTextView = findViewById(R.id.releaseDate);
    mediaPlayer = new MediaPlayer();
    playAndPauseButton = findViewById(R.id.playAndPauseButton);
    durationMusicTextView = findViewById(R.id.durationMusic);
    Drawable playButtonImage = getApplicationContext().getResources().getDrawable(R.drawable.play_icon_outlined);
    Drawable pauseButtonImage = getApplicationContext().getResources().getDrawable(R.drawable.pause_icon);
    playButtonImage.setBounds(0, 0, 90, 90);
    pauseButtonImage.setBounds(0, 0, 90, 90);
    progressScreen = findViewById(R.id.viewProgressStub);
    spotifyButton = findViewById(R.id.spotifyButton);
    progressScreen.inflate();

    apiService = new MusicController(this);
    Call<Music> callService = apiService.getDetailTrack("4WNcduiCmDNfmTEz7JvmLv");
    callService.enqueue(new Callback<Music>() {
      @Override
      public void onResponse(Call<Music> call, Response<Music> response) {
        Music music = response.body();
        try {
          setContentOnActivity(music);
        } catch (ParseException e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onFailure(Call<Music> call, Throwable throwable) {
        Toast.makeText(getApplicationContext(), "Ocorreu um erro ao buscar dados do Spotify", Toast.LENGTH_SHORT);
      }
    });

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
          setPlayButton(pauseButtonImage, "PAUSE PREVIEW");
        }
      }
    });
  }

  public void setContentOnActivity(Music music) throws ParseException {
    musicData = music;
    AlbumTracks[] tracks = music.getTracks();
    String nameMusic = tracks[0].getName();
    Album album = tracks[0].getAlbum();
    Artists[] artists = album.getArtists();
    String nameArtist = artists[0].getName();
    int popularityNumber = tracks[0].getPopularity();
    String releaseDate = timeString.formatDate(album.getRelease_date());
    long durationMs = tracks[0].getDuration_ms();
    String durationSong = timeString.convertMsInMinutes(durationMs);
    Images[] imagesData = album.getImages();
    String urlImage = imagesData[0].getUrl();
    Uri urlSong = Uri.parse(tracks[0].getPreview_url());

    nameMusicTextView.setText(nameMusic);
    nameArtistTextView.setText(nameArtist);
    popularityNumberTextView.setText(String.valueOf(popularityNumber));
    releaseDateTextView.setText(releaseDate);
    durationMusicTextView.setText(durationSong);
    new DownloadImageTask(musicImage).execute(urlImage);
    try {
      mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
      mediaPlayer.setDataSource(getApplicationContext(), urlSong);
      mediaPlayer.prepare();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      progressScreen.setVisibility(View.GONE);
    }
  }

  public void setPlayButton(Drawable iconImage, String buttonText) {
    playAndPauseButton.setText(buttonText);
    playAndPauseButton.setCompoundDrawables(iconImage, null, null, null);
  }

  public void openExternalURL(View view){
      AlbumTracks[] tracks = musicData.getTracks();
      ExternalURLS externalUrls = tracks[0].getExternal_urls();
      String urlSpotify = externalUrls.getSpotify_url();
      System.out.println(urlSpotify);
      Uri spotifyPage = Uri.parse(urlSpotify);
      Intent intent = new Intent(Intent.ACTION_VIEW, spotifyPage);
      startActivity(intent);
  }

}