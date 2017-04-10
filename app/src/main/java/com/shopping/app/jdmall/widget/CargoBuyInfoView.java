package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.FindBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by 龚浩 on 2017/4/8.
 */

public class CargoBuyInfoView extends LinearLayout {
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_label)
    TextView mTvLabel;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_count)
    TextView mTvCount;

    public CargoBuyInfoView(Context context) {
        this(context, null);
    }

    public CargoBuyInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.view_cargo_buy, this);
        ButterKnife.bind(this, this);
    }

    public void setData(FindBean.ProductListBean bean) {
        String pic = Constant.HOST + bean.getPic();
        Glide.with(getContext()).load(pic).bitmapTransform(new CropCircleTransformation(getContext())).crossFade(1000).into(mIvIcon);
        mTvLabel.setText(bean.getName());
        mTvPrice.setText("¥: "+bean.getPrice());
        mTvCount.setText("x " + bean.getBuyCounts());
    }
}
