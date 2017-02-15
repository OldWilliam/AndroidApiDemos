package me.wx.demo.apidemos.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ein on 2017/2/15.
 */

public abstract class BaseFragment extends Fragment {
    private View root;

    protected void setContentView(int layoutResId) {
        root = LayoutInflater.from(getContext()).inflate(layoutResId, null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return root;
    }

    protected View findViewById(int idRes) {
        return root.findViewById(idRes);
    }

    protected abstract void initViews();

    protected abstract void loadData();

}
