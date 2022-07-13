package com.example.testapplicationjava.utils;

public class TimeString {
  public String convertMsInMinutes(long milliseconds){
    long minutes = (milliseconds / 1000) / 60;
    long seconds = (milliseconds / 1000) % 60;

    return minutes + ":" + seconds;
  }
}
