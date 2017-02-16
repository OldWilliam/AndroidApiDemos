package me.wx.demo.apidemos.View.Demo002;


import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import java.util.List;

import me.wx.demo.apidemos.R;

/**
 * WebView下载文件
 */
public class WebViewDownloadFragment extends Fragment {

    public static final String TAG = WebViewDownloadFragment.class.getSimpleName();

    public static final String h5Url = "http://act.inke.cn/banner/201701/game.html";

    private WebView mWebView;

    public WebViewDownloadFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view_download, container, false);
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
            Log.e(TAG, "onDownloadStart: " + url);
            Toast.makeText(getContext(), "开始下载", Toast.LENGTH_SHORT).show();
            if (false) {
                dlByDownloadManager(url);
            } else {
                DownloadService.startActionDownload(getContext(), url);
            }
        }
    }

    /**
     * >>>>>>>>>>>>>>>>>>>>>>>DownloadManager相关>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     */
    /**
     * @param context used to check the device version and DownloadManager information
     * @return true if the download manager is available
     */
    public boolean isDownloadManagerAvailable(Context context) {
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setClassName("com.android.providers.downloads.ui", "com.android.providers.downloads.ui.DownloadList");
            List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent,
                    PackageManager.MATCH_DEFAULT_ONLY);
            return list.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private void dlByDownloadManager(String url) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("手游直播");
        request.setTitle("映客");
        request.setMimeType("application/vnd.android.package-archive");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        // in order for this if to run, you must use the android 3.2 to compile your app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.getExternalStorageDirectory().toString(), "inke.apk");
        // get download service and enqueue file
        DownloadManager manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }


    /**
     * <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
     */

    /**
     * 本来想用ResultReceiver来接收Service的更新，然后再去更新Notification
     *
     * 不用ResultReceiver来更新信息了，因为Service也可以创建Notification并更新。
     *
     * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/1125/2057.html
     */
//    public class DownloadReceiver extends ResultReceiver {
//
//        /**
//         * Create a new ResultReceive to receive results.  Your
//         * {@link #onReceiveResult} method will be called from the thread running
//         * <var>handler</var> if given, or from an arbitrary thread if null.
//         *
//         * @param handler
//         */
//        public DownloadReceiver(Handler handler) {
//            super(handler);
//        }
//
//        @Override
//        protected void onReceiveResult(int resultCode, Bundle resultData) {
//            super.onReceiveResult(resultCode, resultData);
//            if (resultCode == DownloadService.UPDATE_PROGRESS) {
//                int progress = resultData.getInt("progress");
//                //TODO：更新UI进度操作
//            }
//        }
//    }
}
