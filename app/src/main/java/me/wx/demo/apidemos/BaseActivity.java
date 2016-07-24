package me.wx.demo.apidemos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ein on 2016/7/20.
 */
public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String name = getClass().getSimpleName();
        String title = name.substring(0, name.length() - 8);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
