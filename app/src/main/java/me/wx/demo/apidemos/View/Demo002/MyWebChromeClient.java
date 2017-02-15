package me.wx.demo.apidemos.View.Demo002;

import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by ein on 2017/2/15.
 */
public class MyWebChromeClient extends WebChromeClient {
    private  String TAG = MyWebChromeClient.this.toString();
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        Log.d(TAG, "onProgressChanged: "+newProgress);
    }
}
