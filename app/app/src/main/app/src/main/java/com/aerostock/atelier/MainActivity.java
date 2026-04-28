package com.aerostock.atelier;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;

public class MainActivity extends Activity {

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);

WebView web = new WebView(this);

web.setWebViewClient(new WebViewClient());

WebSettings s = web.getSettings();
s.setJavaScriptEnabled(true);
s.setDomStorageEnabled(true);
s.setAllowFileAccess(true);

web.loadUrl("file:///android_asset/index.html");

setContentView(web);

}

}
