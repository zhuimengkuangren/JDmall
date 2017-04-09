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
import com.shopping.app.jdmall.bean.SaleBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/4/7.
 */

public class SaleItemView extends RelativeLayout {
    @BindView(R.id.iv_item_sale)
    ImageView mIvItemSale;
    @BindView(R.id.tv_item_sale)
    TextView mTvSale;

    public SaleItemView(Context context) {
        this(context, null);
    }

    public SaleItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_item_sale, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(SaleBean.TopicBean bean) {
        String url= Constant.HOST+bean.getPic();
        mIvItemSale.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(getContext()).load(url).into(mIvItemSale);
        mTvSale.setText(bean.getName());
    }
}
