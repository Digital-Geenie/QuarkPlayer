package org.anirudh.redquark.quarkplayer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import org.anirudh.redquark.quarkplayer.constant.Constants;

public class ProfileActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);

        onNewIntent(getIntent());

        try {
            webView.setWebViewClient(new ProfileActivity.MyWebClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(Constants.portfolioURL);
            webView.setHorizontalScrollBarEnabled(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class MyWebClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    protected void onNewIntent(Intent intent){
        String postUrl = intent.getDataString();

        try{
            webView.setWebViewClient(new ProfileActivity.MyWebClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(postUrl);
            webView.setHorizontalScrollBarEnabled(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(ProfileActivity.this, SongListActivity.class);
        startActivity(i);
    }
}
