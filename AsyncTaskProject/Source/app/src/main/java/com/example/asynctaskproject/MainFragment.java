package com.example.asynctaskproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    String url = "https://raw.githubusercontent.com/ksong227/CS3876392020/master/AsyncTaskProject/asyncimgdl.jpg";

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflate
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //button onclick
        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragment.asyncTask task = new MainFragment.asyncTask();
                task.execute(url);
            }
        });
        return rootView;
    }

    //asyncTask
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
            /*alternative
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
            iView = (ImageView) Objects.requireNonNull(getView()).findViewById(R.id.imageView);
            iView.setImageBitmap(bMap);
        }
    }
}