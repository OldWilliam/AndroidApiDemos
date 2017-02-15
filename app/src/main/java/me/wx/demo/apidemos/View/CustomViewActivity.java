package me.wx.demo.apidemos.View;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import me.wx.demo.apidemos.BaseActivity;
import me.wx.demo.apidemos.View.Demo001.InkeGameFragment;
import me.wx.demo.apidemos.R;
import me.wx.demo.apidemos.View.Demo002.WebViewDownloadFragment;

/**
 * 展示自定义的View
 */
public class CustomViewActivity extends BaseActivity implements SimpleCardView.OnCardViewClickListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
    }

    @Override
    protected void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void loadData() {
        String[] mDataSet = new String[]{
                "测试1", "测试2", "测试3"
        };
        mAdapter = new CustomViewAdapter(mDataSet);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCardViewClick(View v) {
        getSupportActionBar().hide();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.activity_view, InkeGameFragment.newInstance());
        transaction.add(R.id.activity_view, new WebViewDownloadFragment());
        transaction.addToBackStack("name");
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportActionBar().show();
    }
}
