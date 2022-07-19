package com.example.testapplicationjava.models;

import com.google.gson.annotations.SerializedName;

public class ProfileArtist {
  @SerializedName("name")
  String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
