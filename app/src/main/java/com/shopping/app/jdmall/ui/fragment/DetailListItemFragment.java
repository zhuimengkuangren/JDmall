package com.shopping.app.jdmall.ui.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.leon.loopviewpagerlib.CirclePageIndicator;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.widget.DetailBottomView;
import com.shopping.app.jdmall.widget.DetailInfoView;
import com.shopping.app.jdmall.widget.DetailTalkView;
import com.shopping.app.jdmall.widget.PopupView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.tv_collect)
    TextView mCollect;
    @BindView(R.id.tv_buy_car)
    TextView mBuyCar;
    @BindView(R.id.buy_now)
    TextView mBuyNow;
    private FindBean.ProductListBean mBean;
    private String mUrl;
    private PopupWindow mWindow;
    private View mRoot;

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
        EventBus.getDefault().register(this);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String event){
        switch (event){
            case "clickclose":
            case "animationcompleted":
                mWindow.dismiss();
                break;
        }
    }

    View.OnClickListener listerner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            View convertView = View.inflate(getContext(), R.layout.view_image, null);
            ImageView largeIcon = (ImageView) convertView.findViewById(R.id.img);
            largeIcon.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(getContext()).load(mUrl).into(largeIcon);
            mWindow = new PopupWindow(convertView,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWindow.dismiss();
                }
            });
            mWindow.setFocusable(true);
            //设置动画样式
            mWindow.setAnimationStyle(R.style.pop_anim);
            mWindow.showAtLocation(mViewPager, Gravity.START, 0, 0);//显示在指定位置,在0,0的位置

        }
    };

    //底部的viewpager
    PagerAdapter bottomAdapter = new PagerAdapter() {
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



    @OnClick({R.id.tv_collect, R.id.tv_buy_car, R.id.buy_now})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_collect:
                //收藏
                Toast.makeText(getContext(), "已添加到购物车", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_buy_car:
                startPopupWindow();
                break;
            case R.id.buy_now:
                break;
        }
    }



    private void startPopupWindow() {
        PopupView popupView = new PopupView(getContext());
        popupView.bindView(mBean);
        mWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mWindow.dismiss();
            }
        });
        mWindow.setFocusable(true);
        mWindow.setOutsideTouchable(true);
        //设置动画样式
        mWindow.setAnimationStyle(R.style.pop_buycar);
        mWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);//显示在指定位置,在0,0的位置
    }
}
