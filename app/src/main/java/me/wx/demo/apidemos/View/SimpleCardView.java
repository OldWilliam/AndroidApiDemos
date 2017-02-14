package me.wx.demo.apidemos.View;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by ein on 2017/2/14.
 */

/**
 * 最简单的CardView，只显示文字
 */
public class SimpleCardView extends CardView implements View.OnClickListener {

    private TextView tv;
    private OnCardViewClickListener listener;

    public SimpleCardView(Context context) {
        super(context);
        init();
    }


    public SimpleCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        tv = new TextView(getContext());
        tv.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        tv.setGravity(Gravity.CENTER);
        addView(tv);

        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, (int) (width*0.618));
    }

    public void setText(String s) {
        tv.setText(s);
    }

    public void setOnCardViewClickListener(Context l) {
        listener = (OnCardViewClickListener) l;
    }

    @Override
    public void onClick(View v) {
        listener.onCardViewClick(v);
    }


    interface OnCardViewClickListener {
        void onCardViewClick(View v);
    }
}
