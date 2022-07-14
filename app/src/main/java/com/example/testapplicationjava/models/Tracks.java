package com.example.testapplicationjava.models;

import com.google.gson.annotations.SerializedName;

public class Tracks {
  @SerializedName("duration_ms")
  long duration_ms;
  @SerializedName("album")
  Album album;
  @SerializedName("explicit")
  Boolean explicit;
  @SerializedName("id")
  String id;
  @SerializedName("name")
  String name;
  @SerializedName("preview_url")
  String preview_url;
  @SerializedName("popularity")
  int popularity;
  @SerializedName("external_urls")
  ExternalURLS external_urls;

  public long getDuration_ms() {
    return duration_ms;
  }

  public void setDuration_ms(long duration_ms) {
    this.duration_ms = duration_ms;
  }

  public Album getAlbum() {
    return album;
  }

  public void setAlbum(Album album) {
    this.album = album;
  }

  public Boolean getExplicit() {
    return explicit;
  }

  public void setExplicit(Boolean explicit) {
    this.explicit = explicit;
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

  public String getPreview_url() {
    return preview_url;
  }

  public void setPreview_url(String preview_url) {
    this.preview_url = preview_url;
  }

  public int getPopularity() {
    return popularity;
  }

  public void setPopularity(int popularity) {
    this.popularity = popularity;
  }

  public ExternalURLS getExternal_urls() {
    return external_urls;
  }

  public void setExternal_urls(ExternalURLS external_urls) {
    this.external_urls = external_urls;
  }
}
