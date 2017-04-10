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
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

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
    @BindView(R.id.ll_find_listitem)
    LinearLayout mLlFindListitem;
    private static final String TAG = "DetailListItemFragment";
    @BindView(R.id.tv_collect)
    LinearLayout mCollect;
    @BindView(R.id.tv_buy_car)
    TextView mBuyCar;
    @BindView(R.id.buy_now)
    TextView mBuyNow;
    @BindView(R.id.detail_bottom_view)
    DetailBottomView mDetailBottomView;
    @BindView(R.id.tv_customer)
    LinearLayout mTvCustomer;
    @BindView(R.id.tv_guanzhu)
    LinearLayout mTvGuanzhu;
    @BindView(R.id.indicator_circle)
    CirclePageIndicator mIndicatorCircle;
    @BindView(R.id.iv_set_guanzhu)
    ImageView mIvSetGuanzhu;
    @BindView(R.id.iv_set_collect)
    ImageView mIvSetCollect;
    private FindBean.ProductListBean mBean;
    private String mUrl;
    private PopupWindow mWindow;

    @Override
    public int getResId() {
        return R.layout.view_listitem_detail_fragment;
    }

    @Override
    public void init() {
        mBean = getActivity().getIntent().getParcelableExtra("values");
        mInfoView.bindView(mBean);//minfoview为null
        mViewPager.setAdapter(adapter);
        mIndicatorCircle.setViewPager(mViewPager);
        EventBus.getDefault().register(this);
        mInfoView.setMyOnClickListerner(new DetailInfoView.onMyClickListerner() {
            @Override
            public void share() {
                showShare();
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String event) {
        switch (event) {
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
            mWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
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

    private void showShare() {
        ShareSDK.initSDK(getContext());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(getContext());
    }

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

    boolean isGuanZhu;
    boolean isCollect;

    @OnClick({R.id.tv_collect, R.id.tv_buy_car, R.id.buy_now, R.id.tv_customer, R.id.tv_guanzhu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_collect:
                //TODO 收藏
                if (isCollect) {
                    mIvSetCollect.setEnabled(false);

                } else {
                    mIvSetCollect.setEnabled(true);
                }
                isCollect = !isCollect;
                Toast.makeText(getContext(), "您选择的物品已收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_buy_car:
                startPopupWindow(R.id.tv_buy_car);
                break;
            case R.id.buy_now:
                //将数据传给
                startPopupWindow(R.id.buy_now);
                break;
            case R.id.tv_customer:
                //TODO 客服
                Toast.makeText(getContext(), "客服不在，请稍候访问", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_guanzhu:
                //Todo 关注
                if (isGuanZhu) {
                    mIvSetGuanzhu.setEnabled(false);
                } else {
                    mIvSetGuanzhu.setEnabled(true);
                }
                isGuanZhu = !isGuanZhu;
                break;
        }
    }


    private void startPopupWindow(int id) {
        PopupView popupView = new PopupView(getContext());
        popupView.bindView(mBean, id);
        mWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
