package me.wx.demo.apidemos;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.wx.demo.apidemos.Service.StartedService;

public class ServiceActivity extends BaseActivity {
    @OnClick(R.id.start)
    void start() {
        Intent intent = new Intent(ServiceActivity.this, StartedService.class);
        startService(intent);
    }

    @OnClick(R.id.stop)
    void stop() {
        Intent intent = new Intent(ServiceActivity.this, StartedService.class);
        stopService(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
    }
}
