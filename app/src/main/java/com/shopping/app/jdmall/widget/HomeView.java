package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.adapter.SecondBannerPagerAdapter;
import com.shopping.app.jdmall.bean.BannerBean;
import com.shopping.app.jdmall.utils.AutoLoopUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by panpan on 2017/4/6.
 */

public class HomeView extends ScrollView {
    @BindView(R.id.home_header_banner)
    HomeHeadBanner mHomeHeaderBanner;
    @BindView(R.id.view_second_pager)
    ViewPager mViewSecondPager;

    private int[] arr = {R.mipmap.top_show_pic1,R.mipmap.top_show_pic2,R.mipmap.top_show_pic3,R.mipmap.top_show_pic4,R.mipmap.top_show_pic5};
    private float mDownX;
    private float mDownY;

    public HomeView(Context context) {
        this(context, null);
    }

    public HomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.view_home, this);
        ButterKnife.bind(this, this);
        init();
    }



    private void init() {
        AutoLoopUtils autoLoopUtils = new AutoLoopUtils(this, mViewSecondPager);
        autoLoopUtils.startLoop();
        initSecondBanner();//初始化第二个轮播图
    }

    public void bindView(BannerBean bannerBean) {
        mHomeHeaderBanner.bindView(bannerBean);
    }

    private void initSecondBanner() {

        SecondBannerPagerAdapter adapter = new SecondBannerPagerAdapter(getContext(),arr);
        mViewSecondPager.setAdapter(adapter);

    }


}
