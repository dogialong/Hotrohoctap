package com.example.main;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class NewsActivity extends Activity {

	private WebView webView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);

		webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://www.poly.edu.vn");

	}

}
