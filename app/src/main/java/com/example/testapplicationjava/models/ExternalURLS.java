package com.example.testapplicationjava.models;

import com.google.gson.annotations.SerializedName;

public class ExternalURLS {
  @SerializedName("spotify")
  String spotify_url;

  public String getSpotify_url() {
    return spotify_url;
  }

  public void setSpotify_url(String spotify_url) {
    this.spotify_url = spotify_url;
  }
}
