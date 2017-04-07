package com.shopping.app.jdmall.ui.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.leon.loopviewpagerlib.CirclePageIndicator;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.widget.DetailBottomView;
import com.shopping.app.jdmall.widget.DetailInfoView;
import com.shopping.app.jdmall.widget.DetailTalkView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/4/6.
 */

public class DetailListItemFragment extends BaseNotLoadDataFragment {
    @BindView(R.id.view_pager_find)
    ViewPager mViewPager;
    @BindView(R.id.info_view)
    DetailInfoView mInfoView;
    @BindView(R.id.talk_view)
    DetailTalkView mTalkView;
    @BindView(R.id.view_pager_bottom)
    ViewPager mViewPagerBottom;
    @BindView(R.id.ll_find_listitem)
    LinearLayout mLlFindListitem;
    private static final String TAG = "DetailListItemFragment";
    @BindView(R.id.indicator_circle)
    CirclePageIndicator mIndicator;
    private FindBean.ProductListBean mBean;
    private String mUrl;
    private PopupWindow mWindow;

    @Override
    public int getResId() {
        return R.layout.view_listitem_detail_fragment;
    }

    @Override
    public void init() {
        mBean = (FindBean.ProductListBean) getActivity().getIntent().getSerializableExtra("values");
        mInfoView.bindView(mBean);//minfoview为null
        mViewPager.setAdapter(adapter);
        mIndicator.setViewPager(mViewPager);
        mViewPagerBottom.setAdapter(bottomAdapter);
        mViewPager.setOnClickListener(new listenrers());
    }
    public class listenrers implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: dddd");
            View contentView = View.inflate(getContext(), R.layout.view_image, null);
            ButterKnife.bind(this,contentView);

            ImageView photoView = (ImageView) contentView.findViewById(R.id.img);
            int widthPixels = getResources().getDisplayMetrics().widthPixels;
            int heightPixels = getResources().getDisplayMetrics().heightPixels;
            Glide.with(getContext()).load(mUrl).override(widthPixels,heightPixels).into(photoView);
            ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1.0f, 0, 1.0f,
                    Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
            scaleAnimation.setDuration(2000);
            //引用第三方
            photoView.startAnimation(scaleAnimation);
            scaleAnimation.start();
        }
    }
    View.OnClickListener listerner=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // 启用图片缩放功能
            /*  photoView.enable();
            // 获取图片信息
            Info info = photoView.getInfo();
            // 从一张图片信息变化到现在的图片，用于图片点击后放大浏览，具体使用可以参照 demo 的使用
            photoView.animaFrom(info);
            // 从现在的图片变化到所给定的图片信息，用于图片放大后点击缩小到原来的位置，具体使用可以参照 demo 的使用
            photoView.animaTo(info,new Runnable() {
                @Override
                public void run() {
                    //动画完成监听
                }
            });
            // 获取/设置 动画持续时间
            photoView.setAnimaDuring(2000);
            // 获取/设置 最大缩放倍数
            photoView.setMaxScale(2.0f);
            float maxScale = photoView.getMaxScale();
            // 设置动画的插入器
            photoView.setInterpolator(new OvershootInterpolator());*/


            /*largeIcon.setScaleType(ImageView.ScaleType.FIT_XY);
            int widthPixels = getResources().getDisplayMetrics().widthPixels*//*+left+right*//*;
            int heightPixels = getResources().getDisplayMetrics().heightPixels;
            *//*RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) largeIcon.getLayoutParams( );
            layoutParams.height=heightPixels;
            layoutParams.width=widthPixels;
            largeIcon.setLayoutParams(layoutParams);*//*
            //Log.d(TAG, "onClick: "+"左边距离"+left+"，右边距离"+right+""+"，顶部距离："+top+"控件宽度"+width+"，屏幕宽度："+widthPixels);
            Glide.with(getContext()).load(mUrl).override(widthPixels,heightPixels).into(largeIcon);
            mWindow = new PopupWindow(contentView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            contentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWindow.dismiss();
                }
            });
            mWindow.setFocusable(true);
            //window.setOutsideTouchable(true);//设置外围点击
            //设置动画样式
            mWindow.setAnimationStyle(R.style.pop_anim);
            mWindow.showAtLocation(mViewPager, Gravity.CENTER, 0, 0);//显示在指定位置
*/
        }
    };

    //底部的viewpager
    PagerAdapter bottomAdapter=new PagerAdapter() {
        @Override
        public int getCount() {
            return 2;//直接写死数量，每张图片一样的
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            DetailBottomView bottomView = new DetailBottomView(getContext());
            container.addView(bottomView);
            return bottomView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };
    PagerAdapter adapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getContext());
            imageView.setClickable(true);
            imageView.setOnClickListener(listerner);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mUrl = Constant.HOST + mBean.getPic();
            Glide.with(getContext()).load(mUrl).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };

}
