package com.uygaruyaniksoy.moviedb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.Map;

public class AsyncImageTask extends AsyncTask<String, Void, Bitmap> {
    private final Map<String, Bitmap> cache;
    ImageView image;
    private String url;

    public AsyncImageTask(ImageView image, Map<String, Bitmap> cache) {
        this.image = image;
        this.cache = cache;
    }

    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        this.url = url;
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
        image.setVisibility(View.VISIBLE);
        cache.put(url, result);
    }
}
