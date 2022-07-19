package com.example.testapplicationjava.models;

import com.google.gson.annotations.SerializedName;

public class TrackDataItem {
  @SerializedName("id")
  String id;
  @SerializedName("name")
  String name;
  @SerializedName("artists")
  Artists artists;
  @SerializedName("duration")
  DurationSong duration;
  @SerializedName("albumOfTrack")
  AlbumOfTrack albumOfTrack;

  public AlbumOfTrack getAlbumOfTrack() {
    return albumOfTrack;
  }

  public void setAlbumOfTrack(AlbumOfTrack albumOfTrack) {
    this.albumOfTrack = albumOfTrack;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Artists getArtists() {
    return artists;
  }

  public void setArtists(Artists artists) {
    this.artists = artists;
  }

  public DurationSong getDuration() {
    return duration;
  }

  public void setDuration(DurationSong duration) {
    this.duration = duration;
  }

}
