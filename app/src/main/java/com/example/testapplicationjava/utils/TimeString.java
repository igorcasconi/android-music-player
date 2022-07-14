package com.example.testapplicationjava.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeString {
  public String convertMsInMinutes(long milliseconds){
    long minutes = (milliseconds / 1000) / 60;
    long seconds = (milliseconds / 1000) % 60;

    return minutes + ":" + seconds;
  }

  public String formatDate(String date) throws ParseException {
    Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
    String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(parsedDate);
    return formattedDate;
  }
}
