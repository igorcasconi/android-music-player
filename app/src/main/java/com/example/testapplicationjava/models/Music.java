package com.example.testapplicationjava.models;

import android.net.Uri;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Music {
    private String nameArtist;
    private String nameSong;
    private String durationSong;
    private String imageSong;
    private Uri urlSong;
    private int popularity;
    private String releaseDate;
    private String musicPage;

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

    public String getDurationSong() {
        return durationSong;
    }

    public void setDurationSong(String durationSong) {
        this.durationSong = durationSong;
    }

    public String getImageSong() {
        return imageSong;
    }

    public void setImageSong(String imageSong) {
        this.imageSong = imageSong;
    }

    public Uri getUrlSong() {
        return urlSong;
    }

    public void setUrlSong(Uri urlSong) {
        this.urlSong = urlSong;
    }

    public void setPopularity(int popularity){
        this.popularity = popularity;
    }

    public int getPopularity(){
        return popularity;
    }

    public void setReleaseDate(String releaseDate) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(releaseDate);
        String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
        this.releaseDate = formattedDate;
    }

    public String getReleaseDate(){
        return this.releaseDate;
    }

    public String getMusicPage(){
        return this.musicPage;
    }

    public void setMusicPage(String musicPage){
        this.musicPage = musicPage;
    }


}
