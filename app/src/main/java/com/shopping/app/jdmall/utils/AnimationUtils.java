package com.shopping.app.jdmall.utils;

import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by user on 2017/4/6.
 */

public class AnimationUtils {
    private static final String TAG = "AnimationUtils";
    //做拉伸动画
    public static void animationViewHeight(final View view, int start, int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
        //添加监听器，获取动画需要的数据数据
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate: " + value);
                //拿到变化的数据设置容器的高度
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();//获取布局参数
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        //启动animator
        valueAnimator.start();
    }
}
