package com.example.testapplicationjava.models;

import com.google.gson.annotations.SerializedName;

public class ItemsArtists {
  @SerializedName("profile")
  ProfileArtist profile;

  public ProfileArtist getProfile() {
    return profile;
  }

  public void setProfile(ProfileArtist profile) {
    this.profile = profile;
  }
}
