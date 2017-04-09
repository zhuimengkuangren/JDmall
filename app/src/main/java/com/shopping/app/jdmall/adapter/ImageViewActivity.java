package com.shopping.app.jdmall.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopping.app.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2017/4/7.
 */

public class ImageViewActivity extends AppCompatActivity {
    @BindView(R.id.img)
    ImageView mImg;
    @BindView(R.id.tv_back)
    TextView mTvBack;
    @BindView(R.id.rl_image_view)
    RelativeLayout mRlImageView;
    private String mImageurl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_image);
        ButterKnife.bind(this);
        mImageurl = getIntent().getStringExtra("imageurl");
        mImg.setScaleType(ImageView.ScaleType.FIT_XY);
        mImg.setVisibility(View.VISIBLE);
        synchronized (ImageViewActivity.class) {
            Glide.with(this).load(mImageurl).into(mImg);
            startScaleAnimations(mImg, mRlImageView, 0, 1, 0, 1, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
        }
    }

    private void startScaleAnimations(final View targetView, final View view2, float fromX, float toX, float fromY, float toY, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue) {

        ScaleAnimation anim = new ScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue);
        anim.setDuration(1000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        targetView.startAnimation(anim);//启动动画前设置监听
    }


    @OnClick({R.id.img, R.id.tv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img:
                closeAnimations(mImg, mRlImageView, 1, 0, 1, 0, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
                break;
            case R.id.tv_back:
                closeAnimations(mImg, mRlImageView, 1, 0, 1, 0, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
                break;
        }
    }


    private void closeAnimations(final View targetView, final View view2, float fromX, float toX, float fromY, float toY, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue) {

        ScaleAnimation anim = new ScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue);
        anim.setDuration(1000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view2.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setVisibility(View.GONE);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        targetView.startAnimation(anim);//启动动画前设置监听
    }
}
