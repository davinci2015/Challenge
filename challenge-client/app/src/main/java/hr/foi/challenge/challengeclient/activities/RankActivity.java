package hr.foi.challenge.challengeclient.activities;

import android.os.Bundle;
import android.webkit.WebView;

import hr.foi.challenge.challengeclient.R;

public class RankActivity extends BaseActivity {

    private static final String URL = "http://46.101.207.199/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl(URL);
    }
}
