package com.example.testapplicationjava.models;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Music {

    @SerializedName("tracks")
    Tracks[] tracks;

    public Tracks[] getTracks() {
        return tracks;
    }

    public void setTracks(Tracks[] tracks) {
        this.tracks = tracks;
    }
}
