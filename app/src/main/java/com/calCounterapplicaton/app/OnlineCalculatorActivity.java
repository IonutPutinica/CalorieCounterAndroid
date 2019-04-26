package com.calCounterapplicaton.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class OnlineCalculatorActivity extends Activity {
    private WebView mWebView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_calculator);

        mWebView=findViewById(R.id.activity_main_webview);

        //Force links and redirects to open in the WebView instead of in a browser

        mWebView.setWebViewClient(new WebViewClient());

        //Enable Javascript

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //Remote Resource
        mWebView.loadUrl("https://www.freedieting.com/calorie-calculator");
        mWebView.setWebViewClient(new WebViewClient());
    }

    //prevent the back-button from closing the application
    @Override
    public void onBackPressed()
    {
        if(mWebView.canGoBack())
        {
            mWebView.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }

}
