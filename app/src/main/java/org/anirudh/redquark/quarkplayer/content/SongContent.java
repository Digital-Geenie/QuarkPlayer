package org.anirudh.redquark.quarkplayer.content;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.anirudh.redquark.quarkplayer.SongListActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by anirshar on 1/14/2018.
 * AsyncTask to get the songs from REST endpoint
 */

public class SongContent extends AsyncTask<Void, Void, String> {

    @SuppressLint("StaticFieldLeak")
    private Activity activity;
    private String url;
    private ProgressDialog dialog;
    private final static String TAG = SongContent.class.getSimpleName();

    public SongContent(Activity activity, String url) {
        super();
        this.activity = activity;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Create a progress dialog
        dialog = new ProgressDialog(activity);
        // Set progress dialog title
        dialog.setTitle("Getting Awesome Music");
        // Set progress dialog message
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(false);
        // Show progress dialog
        dialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {

        // call load JSON from url method
        return loadJSON(this.url);
    }

    @Override
    protected void onPostExecute(String result) {
        ((SongListActivity) activity).parseJsonResponse(result);
        dialog.dismiss();
        Log.i(TAG, result);
    }

    private String loadJSON(String url) {
        // Creating JSON Parser instance
        JSONGetter jParser = new JSONGetter();

        // getting JSON string from URL
        return jParser.getJSONFromUrl(url);
    }

    private class JSONGetter {

        String getJSONFromUrl(String url) {

            HttpURLConnection conn;
            InputStream in = null;
            String jsonString = null;

            try {
                URL newsUrl = new URL(url);
                conn = (HttpURLConnection) newsUrl.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                in = new BufferedInputStream(conn.getInputStream());

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                BufferedReader reader = null;
                if (in != null) {
                    reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"), 8);
                }
                StringBuilder sb = new StringBuilder();
                String line;
                if (reader != null) {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                }
                if (in != null) {
                    in.close();
                }
                jsonString = sb.toString();
            } catch (Exception e) {
                Log.e("Buffer Error", "Error converting result " + e.toString());
            }
            // return JSON String
            return jsonString;

        }
    }
}

