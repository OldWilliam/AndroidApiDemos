package me.wx.demo.apidemos.Service;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.wx.demo.apidemos.BaseActivity;
import me.wx.demo.apidemos.R;

/**
 * 展示各种Service
 */

public class ServiceActivity extends BaseActivity {
    @BindView(R.id.textView)
    TextView show;

    private static class ServiceHandler extends Handler implements Serializable {
        WeakReference<ServiceActivity> acty;

        ServiceHandler(ServiceActivity activity) {
            acty = new WeakReference<ServiceActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            ServiceActivity activity = acty.get();
            activity.show.setText(msg.arg1);
        }
    }

    @OnClick(R.id.start)
    void start() {
        Intent intent = new Intent(ServiceActivity.this, StartedService.class);
        ServiceHandler handler = new ServiceHandler(this);
        intent.putExtra("handler", handler);
        startService(intent);
    }

    @OnClick(R.id.stop)
    void stop() {
        Intent intent = new Intent(ServiceActivity.this, StartedService.class);
        stopService(intent);
    }


    @OnClick(R.id.foreground)
    void foreground() {
        Intent intent = new Intent(this, ForegroundService.class);
        startService(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void loadData() {

    }
}
