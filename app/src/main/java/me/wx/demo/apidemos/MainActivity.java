package me.wx.demo.apidemos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private String[] items;
    private Class[] clss;
    private android.support.v7.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clss = new Class[]{ServiceActivity.class, NotificationActivity.class, DrawActivity.class,AnimationActivity.class,ScrollActivity.class};

        listView = (ListView) findViewById(R.id.listView);
        items = getResources().getStringArray(R.array.main_item);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new MyListener());
        //加入向上滑动，ActionBar隐藏
        actionBar = getSupportActionBar();
        View.OnTouchListener mTouchListener = new View.OnTouchListener() {
            int mTouchSlop = ViewConfiguration.get(MainActivity.this).getScaledTouchSlop();
            float mFirstY, mCurrentY;
            int direction;
            boolean mShow = true;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mFirstY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurrentY = event.getY();
                        if (mCurrentY - mFirstY > mTouchSlop) {
                            direction = 0;
                        } else if (mFirstY - mCurrentY > mTouchSlop) {
                            direction = 1;
                        }

                        if (direction == 1) {
                            if (mShow) {
                                actionBar.hide();
                                mShow = !mShow;
                            }
                        } else if (direction == 0) {
                            if (!mShow) {
                                actionBar.show();
                                mShow = !mShow;
                            }

                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;

                }
                return false;
            }
        };
        listView.setOnTouchListener(mTouchListener);

    }

    class MyListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this, clss[position]);
            startActivity(intent);
        }
    }

    private void actionBarAnim(int flag) {

    }

    void Test() {

    }
}
