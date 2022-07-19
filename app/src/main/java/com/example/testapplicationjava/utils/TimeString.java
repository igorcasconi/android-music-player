package com.example.testapplicationjava.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeString {
  public String convertMsInMinutes(long milliseconds){
    long minutes = (milliseconds / 1000) / 60;
    long seconds = (milliseconds / 1000) % 60;
    String secondText = String.valueOf(seconds);

    if (seconds <= 9) {
      secondText = "0" + seconds;
    }

    return minutes + ":" + secondText;
  }

  public String formatDate(String date) throws ParseException {
    Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
    String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(parsedDate);
    return formattedDate;
  }
}
