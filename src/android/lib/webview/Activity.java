package com.graymatterforge.crucible;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

/** receive string data from an intent and load it in a webview */

@SuppressWarnings("unused")
public class WebViewActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView wv = new WebView(getApplicationContext());
        wv.loadData(getIntent().getExtras().getString("info"),"text/html","utf-8");
        setContentView(wv);
    }
}
