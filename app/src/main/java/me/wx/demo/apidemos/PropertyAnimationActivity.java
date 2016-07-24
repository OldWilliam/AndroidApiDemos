package me.wx.demo.apidemos;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PropertyAnimationActivity extends BaseActivity {
    @BindView(R.id.bt_scale_obj)
    Button scaleBt;

    @OnClick(R.id.bt_scale_obj)
    void scaleObjAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(scaleBt, "scaleX", 2);
        animator.setDuration(300);
        animator.start();
    }

    @BindView(R.id.bt_translate_obj)
    Button transBt;

    @OnClick(R.id.bt_translate_obj)
    void transObjAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(transBt, "translationX", 300);
        animator.setDuration(300);
        animator.start();
    }

    @BindView(R.id.bt_rotate_obj)
    Button rotateBt;

    @OnClick(R.id.bt_rotate_obj)
    void rotateAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(rotateBt, "rotation", 360);
        animator.setDuration(300);
        animator.start();
    }

    @BindView(R.id.bt_alpha_obj)
    Button alphaBt;

    @OnClick(R.id.bt_alpha_obj)
    void alphaAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(alphaBt, "alpha", 0.1f);
        animator.setDuration(300);
        animator.start();
    }


    @BindView(R.id.bt_pivot_obj)
    Button pivotBt;

    @OnClick(R.id.bt_pivot_obj)
    void pivotAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(pivotBt, "pivotX", 100f);
        animator.setDuration(300);
        animator.start();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(pivotBt, "pivotY", 100f);
        animator1.setDuration(300);
        animator1.start();
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(pivotBt, "rotation", 360);
        animator2.setDuration(300);
        animator2.start();
    }

    @BindView(R.id.bt_x)
    Button xBt;

    @OnClick(R.id.bt_x)
    void xAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(xBt, "x", 100);
        animator.setDuration(300);
        animator.start();
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(xBt, "y", 100);
        animator2.setDuration(300);
        animator2.start();
    }

    @BindView(R.id.bt_property_values_holder)
    Button propertyBt;

    @OnClick(R.id.bt_property_values_holder)
    void propertyHolderAnim() {
        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("rotation", 360);
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
        PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(propertyBt, pvh1, pvh2, pvh3).setDuration(1000).start();
    }

    @BindView(R.id.bt_anim_set)
    Button animBt;

    @OnClick(R.id.bt_anim_set)
    void setAnim() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(animBt, "rotation", 360);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(animBt, "scaleX", 1f, 0, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(animBt, "scaleY", 1f, 0, 1f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.playSequentially(animator1, animator2, animator3);
        set.start();

        animator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
    }

    @BindView(R.id.bt_load_anim_from_xml)
    Button xmlBt;

    @OnClick(R.id.bt_load_anim_from_xml)
    void loadAnim() {
        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.anim);
        anim.setTarget(xmlBt);
        anim.start();
    }

    @BindView(R.id.bt_self_animate)
    Button animateSelfBt;

    @OnClick(R.id.bt_self_animate)
    void animateSelf() {
        animateSelfBt.animate().setDuration(1000).alpha(0).rotation(180).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();
    }


    @BindView(R.id.custom_anim)
    Button customAnimBt;

    @OnClick(R.id.custom_anim)
    void customAnim() {
        class CustomTV extends Animation {

            private int mCenterWidth;
            private int mCenterHeight;

            @Override
            public void initialize(int width, int height, int parentWidth, int parentHeight) {
                super.initialize(width, height, parentWidth, parentHeight);
                setDuration(1000);// 设置默认时长
                setFillAfter(true);// 动画结束后保留状态
                setInterpolator(new AccelerateInterpolator());// 设置默认插值器
                mCenterWidth = width / 2;
                mCenterHeight = height / 2;
            }

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                final Matrix matrix = t.getMatrix();
                matrix.preScale(1, 1 - interpolatedTime, mCenterWidth, mCenterHeight);
            }
        }
        CustomTV c = new CustomTV();
        customAnimBt.startAnimation(c);
    }


    @BindView(R.id.custom_3d_anim)
    Button threeDAnimBt;

    @OnClick(R.id.custom_3d_anim)
    void cus3DAnim() {
        class ThreeDAnim extends Animation {
            private int mCenterWidth;
            private int mCenterHeight;
            private Camera mCamera = new Camera();
            private float mRotateY = 0.0f;

            @Override
            public void initialize(int width, int height, int parentWidth, int parentHeight) {
                super.initialize(width, height, parentWidth, parentHeight);
                setDuration(2000);
                setFillAfter(true);
                setInterpolator(new BounceInterpolator());
                mCenterWidth = width / 2;
                mCenterHeight = height / 2;
            }

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                final Matrix matrix = t.getMatrix();
                mCamera.save();
                //使用Camera设置角度
                mCamera.rotateY(mRotateY * interpolatedTime);
                // 将旋转变换作用到matrix上
                mCamera.getMatrix(matrix);
                mCamera.restore();
                // 通过pre方法设置矩阵作用前的偏移量来改变旋转中心
                matrix.preTranslate(mCenterWidth, mCenterHeight);
                matrix.preTranslate(-mCenterWidth, -mCenterHeight);
            }
        }
        threeDAnimBt.startAnimation(new ThreeDAnim());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        ButterKnife.bind(this);
        //布局动画
        LinearLayout ll = (LinearLayout) findViewById(R.id.layout_anim);
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1);
        sa.setDuration(200);
        sa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(PropertyAnimationActivity.this, "这是布局动画", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        LayoutAnimationController lac = new LayoutAnimationController(sa, 0.5f);
        lac.setOrder(LayoutAnimationController.ORDER_REVERSE);
        ll.setLayoutAnimation(lac);
    }
}
