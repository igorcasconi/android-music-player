package com.example.testapplicationjava.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrackItems {

  @SerializedName("items")
  List<TrackData> items;

  public List<TrackData> getItems() {
    return items;
  }

  public void setItems(List<TrackData> items) {
    this.items = items;
  }
}
