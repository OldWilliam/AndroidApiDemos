package me.wx.demo.apidemos;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationActivity extends BaseActivity {
    @OnClick(R.id.noti)
    void noti() {
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Title")
                .setContentText("Content")
                .setTicker("It is me!");

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationActivity.class);
        stackBuilder.addNextIntent(new Intent(this, NotificationActivity.class));
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
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
}
