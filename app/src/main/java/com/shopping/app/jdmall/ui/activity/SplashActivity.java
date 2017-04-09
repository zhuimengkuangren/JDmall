package com.shopping.app.jdmall.ui.activity;

import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.utils.SPUtils;

import butterknife.BindView;

import static com.shopping.app.jdmall.app.JDApplication.getContext;

/**
 * Created by Administrator on 2017/4/5.
 */

public class SplashActivity extends BaseActivity {
    private final int SPLASH_TIME = 2000;

    @BindView(R.id.image_view)
    ImageView mImageView;

    @Override
    protected int getLayoutResId() {
        SpeechUtility.createUtility(getContext(), SpeechConstant.APPID + "=58e88588");
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        super.init();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(SPLASH_TIME);
        mImageView.setAnimation(alphaAnimation);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        alphaAnimation.setAnimationListener(mAnimationListener);
    }
    private Animation.AnimationListener mAnimationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(SPUtils.getBoolean(SplashActivity.this,"Home",false)){
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(SplashActivity.this,TutorialActivity.class);
                startActivity(intent);
                finish();
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
}
