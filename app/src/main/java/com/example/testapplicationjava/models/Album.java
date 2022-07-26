package com.example.testapplicationjava.models;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Album {

  @SerializedName("artists")
  Artists[] artists;
  @SerializedName("release_date")
  String release_date;
  @SerializedName("name")
  String name;
  @SerializedName("id")
  String id;
  @SerializedName("images")
  Images[] images;

  public Images[] getImages() {
    return images;
  }

  public void setImages(Images[] images) {
    this.images = images;
  }

  public Artists[] getArtists() {
    return artists;
  }

  public void setArtists(Artists[] artists) {
    this.artists = artists;
  }

  public String getRelease_date() {
    return release_date;
  }

  public void setRelease_date(String release_date) {
    this.release_date = release_date;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
