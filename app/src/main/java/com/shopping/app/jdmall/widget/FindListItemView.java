package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.FindBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2017/4/5.
 */

public class FindListItemView extends RelativeLayout {
    @BindView(R.id.tv_name_findfragment)
    TextView mName;
    @BindView(R.id.tv_market_price_findfragment)
    TextView mMarketPrice;
    @BindView(R.id.tv_actual_price_findfragment)
    TextView mActualPrice;
    @BindView(R.id.btn_item_list_find)
    Button mBtnItem;
    @BindView(R.id.iv_item_findfragmen)
    ImageView mIcon;

    public FindListItemView(Context context) {
        this(context, null);
    }

    public FindListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_item_list_find, this);
        ButterKnife.bind(this, this);
    }

    @OnClick({R.id.btn_item_list_find, R.id.iv_item_findfragmen})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_item_list_find:

                break;
            case R.id.iv_item_findfragmen:
                break;
        }
    }

    public void bindView(FindBean.ProductListBean productListBean) {
        mName.setText(productListBean.getName());
        mMarketPrice.setText(productListBean.getMarketPrice());
        mActualPrice.setText(productListBean.getPrice());
        String url= Constant.HOST+productListBean.getPic();
        Glide.with(getContext()).load(url).override(80,160).into(mIcon);
    }
}
