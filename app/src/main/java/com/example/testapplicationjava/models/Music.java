package com.example.testapplicationjava.models;

import com.google.gson.annotations.SerializedName;

public class Music {

    @SerializedName("tracks")
    AlbumTracks[] tracks;

    public AlbumTracks[] getTracks() {
        return tracks;
    }

    public void setTracks(AlbumTracks[] tracks) {
        this.tracks = tracks;
    }
}
