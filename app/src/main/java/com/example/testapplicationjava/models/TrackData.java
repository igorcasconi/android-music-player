package com.example.testapplicationjava.models;

import com.google.gson.annotations.SerializedName;

public class TrackData {

  @SerializedName("data")
  TrackDataItem data;

  public TrackDataItem getData() {
    return data;
  }

  public void setData(TrackDataItem data) {
    this.data = data;
  }
}
