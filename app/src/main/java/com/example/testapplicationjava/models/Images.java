package com.example.testapplicationjava.models;

import com.google.gson.annotations.SerializedName;

public class Images {
  @SerializedName("height")
  int height;
  @SerializedName("width")
  int width;
  @SerializedName("url")
  String url;

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
