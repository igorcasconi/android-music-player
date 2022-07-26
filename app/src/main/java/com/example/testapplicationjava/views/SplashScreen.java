package com.example.testapplicationjava.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.testapplicationjava.R;

public class SplashScreen extends AppCompatActivity {
  private static int SPLASH_TIME_OUT = 3000;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);

    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent intentSplashScreen = new Intent(SplashScreen.this,
              HomeActivity.class);
        startActivity(intentSplashScreen);

        finish();
      }
    }, SPLASH_TIME_OUT);
  }
}