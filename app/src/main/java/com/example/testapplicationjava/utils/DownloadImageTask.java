package com.example.testapplicationjava.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    ImageView imageResult;

    public DownloadImageTask(ImageView image){
      this.imageResult = image;
    }

    protected Bitmap doInBackground(String... urls) {
      String urlDisplay = urls[0];
      Bitmap bitmapImage = null;
      try {
        InputStream inputStreamImage = new URL(urlDisplay).openStream();
        bitmapImage = BitmapFactory.decodeStream(inputStreamImage);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return bitmapImage;
    }

    protected void onPostExecute(Bitmap result) {
      imageResult.setImageBitmap(result);
    }

}
