package com.shopping.app.jdmall.utils;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by panpan on 2017/4/6.
 */

public class AutoLoopUtils {

    private View mV;
    private ViewPager mViewPager;

    public AutoLoopUtils(View v, ViewPager viewPager){
        mV = v;
        mViewPager = viewPager;
    }

    public void startLoop(){
        mV.postDelayed(mTask,2000);
    }

    private Runnable mTask = new Runnable() {
        @Override
        public void run() {
            int next = mViewPager.getCurrentItem() + 1;
            mViewPager.setCurrentItem(next);
            startLoop();
        }
    };
}
