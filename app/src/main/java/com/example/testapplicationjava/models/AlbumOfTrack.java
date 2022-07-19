package com.example.testapplicationjava.models;

import com.google.gson.annotations.SerializedName;

public class AlbumOfTrack {

  @SerializedName("coverArt")
  CoverArt coverArt;

  public CoverArt getCoverArt() {
    return coverArt;
  }

  public void setCoverArt(CoverArt coverArt) {
    this.coverArt = coverArt;
  }

  public class SourceImage {
    @SerializedName("url")
    String url;

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }
  }

  public class CoverArt {
    @SerializedName("sources")
    SourceImage[] sources;

    public SourceImage[] getSources() {
      return sources;
    }

    public void setSources(SourceImage[] sources) {
      this.sources = sources;
    }
  }
}
