package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.ui.activity.DetailListItemActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2017/4/8.
 */

public class DetailBottomItemView extends RelativeLayout {
    @BindView(R.id.tv_name_left)
    TextView mTvNameLeft;
    @BindView(R.id.tv_realorice_left)
    TextView mTvRealoriceLeft;
    @BindView(R.id.tv_marketprice_left)
    TextView mTvMarketpriceLeft;

    @BindView(R.id.tv_name_right)
    TextView mTvNameRight;
    @BindView(R.id.tv_detail_realprice_right)
    TextView mTvDetailRealpriceRight;
    @BindView(R.id.tv_detail_marketprice_right)
    TextView mTvDetailMarketpriceRight;
    @BindView(R.id.iv_image_left)
    ImageView mIvImageLeft;
    @BindView(R.id.iv_image_right)
    ImageView mIvImageRight;
    private int mWidth;
    private FindBean.ProductListBean mLeftBean;
    private FindBean.ProductListBean mRightBean;

    public DetailBottomItemView(Context context) {
        this(context, null);
    }

    public DetailBottomItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_detail_bottom_scrollview, this);
        ButterKnife.bind(this, this);

    }

    public void bindLeftView(FindBean.ProductListBean bean) {
        //左边的
        mLeftBean=bean;
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        mWidth = (widthPixels) / 2;

        String url = Constant.HOST + bean.getPic();
        Glide.with(getContext()).load(url).override(mWidth, mWidth).into(mIvImageLeft);
        mTvNameLeft.setText(bean.getName());
        mTvRealoriceLeft.setText(bean.getPrice() + "");
        mTvMarketpriceLeft.setText(bean.getMarketPrice() + "");

    }

    public void bindRightView(FindBean.ProductListBean rightbean) {
        //右边的
        mRightBean =rightbean;
        String righturl = Constant.HOST + rightbean.getPic();
        Glide.with(getContext()).load(righturl).override(mWidth, mWidth).into(mIvImageRight);
        mTvNameRight.setText(rightbean.getName());
        mTvDetailRealpriceRight.setText("￥"+rightbean.getPrice() + "");
        mTvDetailMarketpriceRight.setText(rightbean.getMarketPrice() + "");
    }

    @OnClick({R.id.iv_image_left, R.id.iv_image_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_image_left:
                startDetailItemActivity(mLeftBean);
                break;
            case R.id.iv_image_right:
                startDetailItemActivity(mRightBean);
                break;
        }
    }

    private void startDetailItemActivity(FindBean.ProductListBean bean) {
        Intent intent = new Intent(getContext(), DetailListItemActivity.class);
        intent.putExtra("values",bean);
        getContext().startActivity(intent);
        //todo

    }
}
