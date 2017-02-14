package me.wx.demo.apidemos.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

public class ForegroundService extends Service {
    private static final int NOTIFY_ID = 123;
    @Override
    public void onCreate() {
        super.onCreate();
        showNotification();
    }

    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.support.v7.appcompat.R.drawable.notification_template_icon_bg)
                .setContentTitle("前台服务")
                .setContentText("Content");
        //创建通知被点击时触发的Intent
        Intent intent = new Intent(this, ServiceActivity.class);
        //创建TaskStackBuilder，管理任务栈
        TaskStackBuilder taskBuilder = TaskStackBuilder.create(this);
        taskBuilder.addParentStack(ServiceActivity.class);
        taskBuilder.addNextIntent(intent);
        //通知的事件触发具体要用PendingIntent,获取PendingIntent
        PendingIntent pi = taskBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pi);
        //获取NotificationManager，并启动通知

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = builder.build();
        manager.notify(NOTIFY_ID,notification);
        //启动为前台服务
        startForeground(NOTIFY_ID,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAG", "onStartCommand: ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
