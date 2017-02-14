package me.wx.demo.apidemos.Animation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import me.wx.demo.apidemos.BaseActivity;
import me.wx.demo.apidemos.R;

public class AnimationActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ListView lv = (ListView) findViewById(R.id.lv_animation);
        final Class[] cls = new Class[]{ViewAnimationActivity.class, PropertyAnimationActivity.class};
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"视图动画", "属性动画"}));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AnimationActivity.this, cls[position]);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void loadData() {

    }
}
