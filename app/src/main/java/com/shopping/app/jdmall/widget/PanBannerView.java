package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.adapter.SecondBannerPagerAdapter;
import com.shopping.app.jdmall.utils.AutoLoopUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by panpan on 2017/4/6.
 */

public class PanBannerView extends RelativeLayout {
    @BindView(R.id.view_second_pager)
    ViewPager mViewSecondPager;

    private int[] arr = {R.mipmap.top_show_pic1,R.mipmap.top_show_pic2,R.mipmap.top_show_pic3,R.mipmap.top_show_pic4,R.mipmap.top_show_pic5};

    public PanBannerView(Context context) {
        this(context, null);
    }

    public PanBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.view_pan_banner, this);
        ButterKnife.bind(this, this);

        int index = Integer.MAX_VALUE / 2;
        mViewSecondPager.setCurrentItem(index);

        AutoLoopUtils autoLoopUtils = new AutoLoopUtils(this, mViewSecondPager);
        autoLoopUtils.startLoop();
        initSecondBanner();//初始化第二个轮播图
    }

    private void initSecondBanner() {

        SecondBannerPagerAdapter adapter = new SecondBannerPagerAdapter(getContext(),arr);
        mViewSecondPager.setAdapter(adapter);
    }
}
