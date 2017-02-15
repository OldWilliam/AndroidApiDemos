package me.wx.demo.apidemos.View.Demo002;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import me.wx.demo.apidemos.R;
import me.wx.demo.apidemos.View.BaseFragment;

/**
 *  WebView下载文件
 */
public class WebViewDownloadFragment extends Fragment {

    public static final String h5Url = "http://act.inke.cn/banner/201701/game.html";
    private WebView mWebView;

    public WebViewDownloadFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view_download, container,false);
        mWebView = (WebView) view.findViewById(R.id.web_view);
        mWebView.removeAllViews();
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setDownloadListener(new MyDownloadListener());
        mWebView.getSettings().setJavaScriptEnabled(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mWebView.loadUrl(h5Url);
    }

    private class MyDownloadListener implements DownloadListener {
        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            Log.e("wx", "onDownloadStart: "+url);
            DownloadService.startActionDownload(getContext(),url);
        }
    }
}
