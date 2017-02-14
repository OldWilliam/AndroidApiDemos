package me.wx.demo.apidemos.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class StartedService extends Service {
    private static final String TAG = "StartedService";

    public StartedService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final Handler handler = (Handler) intent.getSerializableExtra("handle");
        Log.d(TAG, "onStartCommand: ");
        new Thread(new Runnable() {
            int i = 0;
            @Override
            public void run() {
                while (true) {
                    Message msg = handler.obtainMessage();
                    msg.arg1 = i++;
                    handler.sendMessage(msg);
                }

            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
