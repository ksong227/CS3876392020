package com.example.asynctaskproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    String url = "https://raw.githubusercontent.com/ksong227/CS3876392020/master/AsyncTaskProject/asyncimgdl.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncTask task = new asyncTask();
                task.execute(url);
            }
        });
    }

    private class asyncTask extends AsyncTask<String, Void, Bitmap>
    {
        ImageView iView;
        Bitmap bMap;

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                if(con.getResponseCode() !=200)
                    throw new Exception("Connection Failed");
                InputStream is = con.getInputStream();
                bMap = BitmapFactory.decodeStream(is);
                is.close();
                return bMap;
            } catch (Exception e) {
                Log.e("Image", "Failed to load", e);
                Log.e("Error", Objects.requireNonNull(e.getMessage()));
            }
            /*
            try {
                InputStream is = new URL(params[0]).openStream();
                bMap = BitmapFactory.decodeStream(is);
                is.close();
                return bMap;
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            return bMap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            iView = (ImageView) findViewById(R.id.imageView);
            iView.setImageBitmap(bMap);
        }
    }
}