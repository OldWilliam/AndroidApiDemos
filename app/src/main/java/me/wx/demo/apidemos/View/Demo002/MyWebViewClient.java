package me.wx.demo.apidemos.View.Demo002;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by ein on 2017/2/15.
 */
public class MyWebViewClient extends WebViewClient {
    private String TAG = MyWebViewClient.this.toString();
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        Log.d(TAG, "onPageStarted: "+url);
    }
}
