package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.FindBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/4/8.
 */

public class DetailBottomItemView extends RelativeLayout {
    @BindView(R.id.iv_image_left)
    ImageView mIvImageLeft;
    @BindView(R.id.tv_name_left)
    TextView mTvNameLeft;
    @BindView(R.id.tv_realorice_left)
    TextView mTvRealoriceLeft;
    @BindView(R.id.tv_marketprice_left)
    TextView mTvMarketpriceLeft;
    @BindView(R.id.iv_image_right)
    ImageView mIvImageRight;
    @BindView(R.id.tv_name_right)
    TextView mTvNameRight;
    @BindView(R.id.tv_detail_realprice_right)
    TextView mTvDetailRealpriceRight;
    @BindView(R.id.tv_detail_marketprice_right)
    TextView mTvDetailMarketpriceRight;
    private int mWidth;

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
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        int paddingRight = getPaddingRight();
        int paddingLeft = getPaddingLeft();
        mWidth = (widthPixels)/2;

        String url = Constant.HOST + bean.getPic();
        Glide.with(getContext()).load(url).override(mWidth,mWidth).into(mIvImageLeft);
        mTvNameLeft.setText(bean.getName());
        mTvRealoriceLeft.setText(bean.getPrice() + "");
        mTvMarketpriceLeft.setText(bean.getMarketPrice() + "");

    }

    public void bindRightView(FindBean.ProductListBean rightbean) {
        //右边的
        String righturl= Constant.HOST+rightbean.getPic();
        Glide.with(getContext()).load(righturl).override(mWidth,mWidth).into(mIvImageRight);
        mTvNameRight.setText(rightbean.getName());
        mTvDetailRealpriceRight.setText(rightbean.getPrice()+"");
        mTvDetailMarketpriceRight.setText(rightbean.getMarketPrice()+"");
    }
}
