package com.example.sofie.galgeleg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


/**
 * Created by Sofie on 7/01/2018.
 */

public class Help extends Fragment {
    private WebView webView2;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Help", "shown");

        View src = inflater.inflate(R.layout.activity_help, container, false);

        webView2 = (WebView) src.findViewById(R.id.WebViews);
        String help = "https://en.wikipedia.org/wiki/Hangman_(game)";
        WebSettings webSettings = webView2.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView2.setWebViewClient(new WebViewClient());
        webView2.loadUrl(help);

        return src;
    }

}
