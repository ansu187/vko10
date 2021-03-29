package com.example.vko10;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView index;
    TextView amount;
    WebView web;
    EditText url_input;
    private String url = "index.html";
    String current_url = url;
    History history;
    boolean html = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url_input = (EditText) findViewById(R.id.url_input);
        web = (WebView) findViewById(R.id.WebView);
        index = (TextView) findViewById(R.id.index);
        amount = (TextView) findViewById(R.id.amount);



        web.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url2) {

                try {
                    if (url2 != current_url){
                        history.addUrl(url2);
                        url_input.setText(url2);

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

        });




        web.getSettings().setJavaScriptEnabled(true);



        if(!url.contains("http://") || !url.contains("https://") && url != "index.html"){
            if(url == "index.html"){
                url = "file:///android_asset/index.html";
            }else {
               url = "https://" + url;
            }
        }

        web.loadUrl(url);
        history = new History(web.getUrl());



        url_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = url_input.getText().toString();

                if(url.contains("index.html")) {
                    web.loadUrl("file:///android_asset/index.html");
                    html = true;
                    history.addUrl("file:///android_asset/index.html");
                }
                if(!html) {
                    if (!url.contains("http://") || !url.contains("https://") || !url.contains("https//")) {

                        url = "https://" + url;
                        web.loadUrl(url);
                        history.addUrl(web.getUrl());
                    }
                }
                html = false;


                index.setText(""+history.getIndex());
                amount.setText(""+history.getAmount());
            }
        });

    }

    public void reflesh(View v){
        web.loadUrl(web.getUrl());
        index.setText(""+history.getIndex());
        amount.setText(""+history.getAmount());


    }

    public void previousPage(View v){
        web.loadUrl(history.previous());
        index.setText(""+history.getIndex());
        amount.setText(""+history.getAmount());
        url_input.setText(web.getUrl());
    }

    public void nextPage(View v){
        web.loadUrl(history.next());
        index.setText(""+history.getIndex());
        amount.setText(""+history.getAmount());
        url_input.setText(web.getUrl());
    }


    public void shoutOut(View v){
        web.evaluateJavascript("javascript:shoutOut()", null);
    }

    public void initialize(View v){
        web.evaluateJavascript("javascript:initialize()", null);
    }

/*
    public class MyWebViewClient extends WebViewClient {



        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);



            //Log.i("Listener", "Start");

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if(url != current_url) {
                System.out.println("osoite numero" + kierros++ + url);
                history.addUrl(url);
                current_url = url;
            }
            super.onPageFinished(view, url);

        }

    }*/

}

