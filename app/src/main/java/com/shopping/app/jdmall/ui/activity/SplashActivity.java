package com.shopping.app.jdmall.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/5.
 */

public class SplashActivity extends BaseActivity {
    private final int SPLASH_TIME = 4000;

    @BindView(R.id.image_view)
    ImageView mImageView;
    @BindView(R.id.liu_xing)
    ImageView mLiuXing;
    @BindView(R.id.relatie_layout)
    RelativeLayout mRelatieLayout;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        super.init();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(mLiuXing, "translationX", 0,320);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mImageView, "scaleX", 0, 1.2f,1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mImageView, "scaleY", 0, 1.2f,1);
        AnimatorSet animatorSet1 = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet1.playTogether(scaleX,scaleY);
        animatorSet1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mRelatieLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet2.playSequentially(animatorSet1,translationX);
        animatorSet2.setDuration(SPLASH_TIME);
        animatorSet2.start();
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        animatorSet2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (SPUtils.getBoolean(SplashActivity.this, "Home", false)) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, TutorialActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.liu_xing)
    public void onViewClicked() {
    }
}
