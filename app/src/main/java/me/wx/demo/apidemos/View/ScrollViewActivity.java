package me.wx.demo.apidemos.View;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

import me.wx.demo.apidemos.BaseActivity;
import me.wx.demo.apidemos.R;

/**
 * 展示如何让View进行滑动
 */
public class ScrollViewActivity extends BaseActivity {
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        ll = (LinearLayout) findViewById(R.id.scroll_layout);
        //Layout方法
        View view1 = new View(this) {
            int lastX, lastY;

            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                setMeasuredDimension(200, 100);
            }

            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                canvas.drawColor(Color.BLUE);
            }

            @Override
            public boolean onTouchEvent(MotionEvent event) {
                int rawX = (int) event.getRawX();
                int rawY = (int) event.getRawY();


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //记录触摸点坐标
                        lastX = rawX;
                        lastY = rawY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //计算偏移量
                        int offsetX = rawX - lastX;
                        int offsetY = rawY - lastY;
                        //在当前left、top、right、bottom家上偏移量
                        layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
                        //重设初始坐标
                        lastX = rawX;
                        lastY = rawY;
                        break;
                }
                return true;
            }
        };
        //offsetLeftAndRight和offsetTopAndBottom方法
        View view2 = new View(this) {
            int lastX, lastY;

            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                setMeasuredDimension(200, 100);
            }

            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                canvas.drawColor(Color.RED);
            }

            @Override
            public boolean onTouchEvent(MotionEvent event) {
                int rawX = (int) event.getRawX();
                int rawY = (int) event.getRawY();


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //记录触摸点坐标
                        lastX = rawX;
                        lastY = rawY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //计算偏移量
                        int offsetX = rawX - lastX;
                        int offsetY = rawY - lastY;
                        //offsetLeftAndRight和offsetTopAndBottom方法
                        offsetLeftAndRight(offsetX);
                        offsetTopAndBottom(offsetY);
                        //重设初始坐标
                        lastX = rawX;
                        lastY = rawY;
                        break;
                }
                return true;
            }
        };
        //LayoutParams
        View view3 = new View(this) {
            int lastX, lastY;

            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                setMeasuredDimension(200, 100);
            }

            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                canvas.drawColor(Color.BLACK);
            }

            @Override
            public boolean onTouchEvent(MotionEvent event) {
                int rawX = (int) event.getRawX();
                int rawY = (int) event.getRawY();


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //记录触摸点坐标
                        lastX = rawX;
                        lastY = rawY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //计算偏移量
                        int offsetX = rawX - lastX;
                        int offsetY = rawY - lastY;

                        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
                        layoutParams.leftMargin = getLeft() + offsetX;
                        layoutParams.topMargin = getTop() + offsetY;
                        setLayoutParams(layoutParams);
                        /**
                         * 不用考虑父布局类型的写法
                         * ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
                         * layoutParams.leftMargin = getLeft() + offestX;
                         * layoutParams.topMargin = getTop() + offsetY;
                         * setLayoutParams(layoutParams);
                         */
                        //重设初始坐标
                        lastX = rawX;
                        lastY = rawY;
                        break;
                }
                return true;
            }
        };
        //scrollTo和scrollBy
        View view4 = new View(this) {
            int lastX, lastY;

            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                setMeasuredDimension(200, 100);
            }

            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                canvas.drawColor(Color.GRAY);
            }

            @Override
            public boolean onTouchEvent(MotionEvent event) {
                int rawX = (int) event.getRawX();
                int rawY = (int) event.getRawY();


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //记录触摸点坐标
                        lastX = rawX;
                        lastY = rawY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //计算偏移量
                        int offsetX = rawX - lastX;
                        int offsetY = rawY - lastY;
                        //scrollTo适用于绝对坐标，scrollBy适用于偏移量,并且都是移动View的内容
                        //偏移量取反
                        ((View) getParent()).scrollBy(-offsetX, -offsetY);
                        //重设初始坐标
                        lastX = rawX;
                        lastY = rawY;
                        break;
                }
                return true;
            }
        };
        //Scroller
        final Scroller mScroller = new Scroller(this);
        View view5 = new View(this) {
            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                setMeasuredDimension(200, 100);
            }

            @Override
            protected void onDraw(Canvas canvas) {
                canvas.drawColor(Color.GREEN);
            }

            @Override
            public void computeScroll() {
                super.computeScroll();
                //判断Scroller是否执行完毕
                if (mScroller.computeScrollOffset()) {
                    ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
                    //通过不断重绘来不断调用computeScroll
                    invalidate();
                }
            }

            int lastX, lastY;

            @Override
            public boolean onTouchEvent(MotionEvent event) {
                int rawX = (int) event.getRawX();
                int rawY = (int) event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //记录触摸点坐标
                        lastX = rawX;
                        lastY = rawY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //计算偏移量
                        int offsetX = rawX - lastX;
                        int offsetY = rawY - lastY;
                        ((View) getParent()).scrollBy(-offsetX, -offsetY);
                        //重设初始坐标
                        lastX = rawX;
                        lastY = rawY;
                        break;

                    //加入效果，手指离开时，执行滑动过程
                    case MotionEvent.ACTION_UP:
                        View viewGroup = (View) getParent();
                        mScroller.startScroll(viewGroup.getScrollX(), viewGroup.getScrollY(), -viewGroup.getScrollX(), -viewGroup.getScrollY());
                        invalidate();
                        break;
                }
                return true;
            }
        };
        //属性动画 未实现
        View view6 = new View(this) {
            int lastX, lastY;

            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                setMeasuredDimension(200, 100);
            }

            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                canvas.drawColor(Color.YELLOW);
            }

            @Override
            public boolean onTouchEvent(MotionEvent event) {
                int rawX = (int) event.getRawX();
                int rawY = (int) event.getRawY();


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //记录触摸点坐标
                        lastX = rawX;
                        lastY = rawY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //计算偏移量
                        int offsetX = rawX - lastX;
                        int offsetY = rawY - lastY;
                        //在当前left、top、right、bottom加上偏移量
                        Log.e("TAG", "offsetX:"+offsetX+"  "+offsetY);
//                        Log.e("TAG", "rawX:" + rawX + " " + rawY);
                        ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "translationX", 100);
                        ObjectAnimator animatorY = ObjectAnimator.ofFloat(this, "translationY", 100);
                        AnimatorSet set = new AnimatorSet();
                        animatorX.setFloatValues(offsetX);
                        animatorY.setFloatValues(offsetY);
                        set.playTogether(animatorX, animatorY);
                        set.start();
                        //重设初始坐标
                        lastX = rawX;
                        lastY = rawY;
                        break;
                }
                return true;
            }
        };

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll.addView(view3, layoutParams);
        ll.addView(view1);
        ll.addView(view2);
        ll.addView(view4);
        ll.addView(view5);
        ll.addView(view6);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void loadData() {

    }
}
