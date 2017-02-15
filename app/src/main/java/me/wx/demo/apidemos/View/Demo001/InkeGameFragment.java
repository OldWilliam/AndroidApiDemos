package me.wx.demo.apidemos.View.Demo001;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.wx.demo.apidemos.R;

/**
 * Inke 游戏大厅界面
 */
public class InkeGameFragment extends Fragment {

    public InkeGameFragment() {
    }

    public static InkeGameFragment newInstance() {
        InkeGameFragment fragment = new InkeGameFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inke_game, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
