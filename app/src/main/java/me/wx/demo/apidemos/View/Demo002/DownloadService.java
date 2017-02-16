package me.wx.demo.apidemos.View.Demo002;

import android.app.DownloadManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ResultReceiver;
import android.util.Log;
import android.widget.RemoteViews;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import me.wx.demo.apidemos.R;

/**
 * 下载文件的Service,下载完成后自动安装
 */
public class DownloadService extends IntentService {

    public static final String TAG = DownloadService.class.getSimpleName();

    public static final String ACTION_DOWNLOAD = "me.wx.demo.apidemos.View.Demo002.action.FOO";
    public static final String DOWNLOAD_URL = "me.wx.demo.apidemos.View.Demo002.extra.DOWNLOAD_URL";
    public static final int UPDATE_PROGRESS = 8848;

    private Notification notification;
    private NotificationManager manager;


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
        Log.d(TAG, "startActionDownload: ");
        Intent intent = new Intent(context, DownloadService.class);
        intent.setAction(ACTION_DOWNLOAD);
        intent.putExtra(DOWNLOAD_URL, url);
        context.startService(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotification();
        return super.onStartCommand(intent, flags, startId);
    }

    private void createNotification() {
        Notification.Builder builder = new Notification.Builder(DownloadService.this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentText("映客下载中");

        notification = builder.build();
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        RemoteViews contentView = new RemoteViews(getPackageName(),R.layout.notification_content);
        contentView.setTextViewText(R.id.notification_title,"映客直播");
        contentView.setProgressBar(R.id.pBar, 100, 0, false);
        notification.contentView = contentView;

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(R.layout.notification_content, notification);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: ");
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
        Log.d(TAG, "handleActionDownload: " + url);
        HttpURLConnection connection = null;
        FileOutputStream out = null;
        InputStream in = null;
        //文件长度、已下载长度
        int flen = 0;
        int done = 0;

        try {
            URL link = new URL(url);
            connection = (HttpURLConnection) link.openConnection();
            flen = connection.getContentLength();
            in = connection.getInputStream();
            byte[] buffer = new byte[4096];
            int count = 0;
            out = new FileOutputStream(new File(Constants.getFullApkName()));
            while ((count = in.read(buffer)) != -1) {
                out.write(buffer, 0, count);
                done += count;
                //发布下载进度
                publishProgress((int) done * 100 / flen);
            }
            out.flush();
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        publishProgress(100);
        autoInstallApk(getApplicationContext(), Constants.getFullApkName());
    }

    private void publishProgress(int progress) {
        Log.d(TAG, "publishProgress: " + progress);
        notification.contentView.setProgressBar(R.id.pBar, 100, progress, false);
        manager.notify(R.layout.notification_content, notification);
    }

    /**
     * @param context
     * @param fileName The absolute path add the filename
     */
    private void autoInstallApk(Context context, String fileName) {
        Log.d(TAG, "autoInstallApk: " + fileName);
        Uri installUri = Uri.fromFile(new File(fileName));
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(installUri, "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
