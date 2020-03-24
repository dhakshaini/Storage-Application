package com.example.evernote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.webkit.WebViewClient;
import android.webkit.WebView;
import android.widget.EditText;

public class Links extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);
        webView=findViewById(R.id.etwb);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://m.facebook.com/");
        webView.loadUrl("https://www.google.com");
    }
}
