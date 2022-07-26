package com.example.testapplicationjava.models;

import com.google.gson.annotations.SerializedName;

public class Tracks {
  @SerializedName("tracks")
  TrackItems tracks;

  public TrackItems getTracks() {
    return tracks;
  }

  public void setTracks(TrackItems tracks) {
    this.tracks = tracks;
  }
}
