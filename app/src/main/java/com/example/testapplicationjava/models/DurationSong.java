package com.example.testapplicationjava.models;

import com.google.gson.annotations.SerializedName;

public class DurationSong {

  @SerializedName("totalMilliseconds")
  long totalMilliseconds;

  public long getTotalMilliseconds() {
    return totalMilliseconds;
  }

  public void setTotalMilliseconds(long totalMilliseconds) {
    this.totalMilliseconds = totalMilliseconds;
  }
}
