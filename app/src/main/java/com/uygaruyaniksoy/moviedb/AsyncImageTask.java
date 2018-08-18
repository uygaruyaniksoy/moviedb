package com.uygaruyaniksoy.moviedb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;

public class AsyncImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView image;

    public AsyncImageTask(ImageView image) {
        this.image = image;
    }

    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap bitmap = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap result) {
        image.setImageBitmap(result);
    }
}
