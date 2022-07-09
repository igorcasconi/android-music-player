package com.example.testapplicationjava.classes;

import android.graphics.Bitmap;

public class Music {
    private String nameArtist;
    private String nameSong;
    private int durationSong;
    private String imageSong;

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public int getDurationSong() {
        return durationSong;
    }

    public void setDurationSong(int durationSong) {
        this.durationSong = durationSong;
    }

    public String getImageSong() {
        return imageSong;
    }

    public void setImageSong(String imageSong) {
        this.imageSong = imageSong;
    }
}
