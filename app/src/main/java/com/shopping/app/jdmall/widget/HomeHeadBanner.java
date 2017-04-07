package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.BannerBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by panpan on 2017/4/6.
 */

public class HomeHeadBanner extends RelativeLayout {
    @BindView(R.id.view_page)
    ViewPager mViewPage;
    @BindView(R.id.dots_container)
    LinearLayout mDotsContainer;
    private ArrayList<String> mImageUrlList;
    private int mDotSize;

    public HomeHeadBanner(Context context) {
        this(context, null);
    }

    public HomeHeadBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.view_home_head_banner, this);
        ButterKnife.bind(this, this);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs) {
        //获取自定义的属性， attrs配置的所有属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BannerStyle);
        //拿到点的大小属性 5dp-->转换对应的像素
        mDotSize = typedArray.getDimensionPixelSize(R.styleable.BannerStyle_dot_size, 20);
        typedArray.recycle();
    }

    public void bindView(BannerBean bannerBean) {
        initBanner(bannerBean);//初始化轮播图
    }

    private void initBanner(BannerBean bannerBean) {

        mDotsContainer = (LinearLayout) findViewById(R.id.dots_container);
        initDots(bannerBean);

        //ListView -> setAdapter -> 创建Adapter --> 创建数据集合
        mViewPage.setAdapter(mPagerAdapter);

        //添加viewpager监听器
        mViewPage.addOnPageChangeListener(mOnPageChangeListener);

        //设置viewpager初始化页面的位置
        mViewPage.setCurrentItem(0);//触发mOnPageChangeListene
    }


    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
           /* if (mImageUrlList != null) {
                return mImageUrlList.size();
            }*/
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position % mImageUrlList.size();// 5 %５＝０ ６％５＝１ [0, 4]   只能在0到4之间循环
            ImageView imageView = new ImageView(getContext());
            Glide.with(getContext()).load(mImageUrlList.get(position)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            removeView((View) object);
        }
    };


    /**
     * 动态添加点
     */
    private void initDots(BannerBean bean) {

        mImageUrlList = new ArrayList<String>();
        List<BannerBean.HomeTopicBean> homeTopic = bean.getHomeTopic();
        for (int i = 0; i < homeTopic.size(); i++) {
            String pic = homeTopic.get(i).getPic();
            String url = Constant.HOST + pic;
            mImageUrlList.add(url);
        }
        //有多少个页面就有多少点
        for (int i = 0; i < mImageUrlList.size(); i++) {
            //动态的添加点
            View dot = new View(getContext());

            //创建点的父容器对应的布局参数
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mDotSize, mDotSize);
            layoutParams.rightMargin = 8;

            dot.setLayoutParams(layoutParams);

            //默认第一个点是选中
            if (i == 0) {
                //第一个点背景是红色
                dot.setBackgroundResource(R.drawable.dot_selected);

            } else {
                dot.setBackgroundResource(R.drawable.dot_normal);

            }

            //添加到父容器
            mDotsContainer.addView(dot);
        }
    }

    private int mLastPosition = 0;
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {


            //调整位置
            position = position % mImageUrlList.size();// 5 %５＝０ ６％５＝１ [0, 4]   只能在0到4之间循环
//            Log.d(TAG, "onPageSelected: " + position);
            //当前位置和上次选中的位置是一样，不做任何处理
            if (position == mLastPosition) {
                return;
            }


            //切换点，将选中点变红
            View dot = mDotsContainer.getChildAt(position);
            dot.setBackgroundResource(R.drawable.dot_selected);
            //将上一个选中的点变白
            View preDot = mDotsContainer.getChildAt(mLastPosition);
            preDot.setBackgroundResource(R.drawable.dot_normal);

            //更新上次选中的位置
            mLastPosition = position;

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    /**
     * 当控件添加到phonewindow时的回调
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //启动控件的定时任务
        startLoop();
    }

    /**
     * 当控件从phone detach时的回调
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //停止循环
        removeCallbacks(mTicker);
    }


    /**
     * 启动自动轮播
     */
    public void startLoop() {
        //定时任务
        postDelayed(mTicker, 2000);
    }

    private Runnable mTicker = new Runnable() {
        @Override
        public void run() {
            //viewpager切换到下一页
            int next = mViewPage.getCurrentItem() + 1;
            mViewPage.setCurrentItem(next);
            startLoop();
        }
    };
}
