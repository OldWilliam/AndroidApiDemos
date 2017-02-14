package me.wx.demo.apidemos.View;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import me.wx.demo.apidemos.BaseActivity;
import me.wx.demo.apidemos.R;

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
        Toast.makeText(this, "click me", Toast.LENGTH_SHORT).show();
    }


}
