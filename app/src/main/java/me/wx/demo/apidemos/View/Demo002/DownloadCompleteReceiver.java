package me.wx.demo.apidemos.View.Demo002;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.File;

public class DownloadCompleteReceiver extends BroadcastReceiver {
    private static final String TAG = DownloadCompleteReceiver.class.getSimpleName();

    public DownloadCompleteReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        autoInstallApk(context,Constants.getFullApkName());
    }

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
