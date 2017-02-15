package me.wx.demo.apidemos.View.Demo002;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 下载文件的Service
 */
public class DownloadService extends IntentService {
    private static final String ACTION_DOWNLOAD = "me.wx.demo.apidemos.View.Demo002.action.FOO";

    private static final String DOWNLOAD_URL = "me.wx.demo.apidemos.View.Demo002.extra.DOWNLOAD_URL";

    public DownloadService() {
        super("DownloadService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionDownload(Context context, String url) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.setAction(ACTION_DOWNLOAD);
        intent.putExtra(DOWNLOAD_URL, url);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_DOWNLOAD.equals(action)) {
                final String url = intent.getStringExtra(DOWNLOAD_URL);
                handleActionDownload(url);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionDownload(String url) {
        String fileName = "inke.apk";
        File directory = Environment.getExternalStorageDirectory();
        File file = new File(directory, fileName);
        HttpURLConnection connection = null;
        FileOutputStream out = null;
        InputStream in = null;
        try {
            URL link = new URL(url);
            connection = (HttpURLConnection) link.openConnection();
            in = connection.getInputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            out = new FileOutputStream(file);
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer,0,len);
            }
            out.flush();
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //调用系统安装APK文件
        Uri installUri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(installUri, "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
