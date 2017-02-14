package me.wx.demo.apidemos.Notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.wx.demo.apidemos.BaseActivity;
import me.wx.demo.apidemos.R;

/**
 * 展示通知Notification效果
 */

public class NotificationActivity extends BaseActivity {
    @OnClick(R.id.noti)
    void noti() {
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Title")
                .setContentText("Content")
                .setTicker("It is me!");

        Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        manager.notify(1, builder.build());
    }

    @OnClick(R.id.noti_high)
    void notiHigh() {
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("高优先级")
                .setContentText("Content")
                .setTicker("It is me!");
        //最高优先级
        builder.setPriority(2);
        manager.notify(2, builder.build());
    }

    @OnClick(R.id.noti_low)
    void notiLow() {
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("低优先级")
                .setContentText("Content")
                .setTicker("It is me!");
        //最低优先级
        builder.setPriority(-2);
        manager.notify(3, builder.build());
    }


    NotificationCompat.Builder builder;
    NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        builder = new NotificationCompat.Builder(this);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void loadData() {

    }
}
