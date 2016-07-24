package me.wx.demo.apidemos;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewAnimationActivity extends BaseActivity {
    @BindView(R.id.animation_ball)
    ImageView ball;

    @OnClick(R.id.bt_alpha)
    void alphaAnim() {
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(1000);
        ball.startAnimation(aa);
    }

    @OnClick(R.id.bt_rotate)
    void rotateAnim() {
        RotateAnimation ra = new RotateAnimation(0, 360, 100, 100);
        ra.setDuration(1000);
        ball.startAnimation(ra);
    }

    @OnClick(R.id.bt_scale)
    void scaleAnim() {
        ScaleAnimation sa = new ScaleAnimation(0, 2, 0, 2);
        sa.setDuration(1000);
        ball.startAnimation(sa);
    }

    @OnClick(R.id.bt_translate)
    void translateAnim() {
        TranslateAnimation ta = new TranslateAnimation(0, 200, 0, 300);
        ta.setDuration(1000);
        ball.startAnimation(ta);
    }

    @OnClick(R.id.bt_rotate_self)
    void rotateSelfAnim() {
        RotateAnimation ra = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5F, RotateAnimation.RELATIVE_TO_SELF, 0.5F);
        ra.setDuration(1000);
        ball.startAnimation(ra);
    }

    @OnClick(R.id.bt_scale_self)
    void scaleSelfAnim() {
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        sa.setDuration(1000);
        ball.startAnimation(sa);
    }

    @OnClick(R.id.bt_anim_set)
    void animSet() {
        AnimationSet as = new AnimationSet(true);
        as.setDuration(1000);

        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        sa.setDuration(1000);

        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(1000);

        RotateAnimation ra = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5F, RotateAnimation.RELATIVE_TO_SELF, 0.5F);
        ra.setDuration(1000);

        as.addAnimation(sa);
        as.addAnimation(aa);
        as.addAnimation(ra);

        ball.startAnimation(as);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
        ButterKnife.bind(this);
    }
}
