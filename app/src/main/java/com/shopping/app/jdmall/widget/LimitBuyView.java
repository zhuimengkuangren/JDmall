package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.LimitBuyBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;

/**
 * Created by Administrator on 2017/4/6.
 * FlashSaleAdapter中使用的内容填充模块
 */

public class LimitBuyView extends RelativeLayout {
    @BindView(R.id.commodity_name)
    TextView mCommodityName;
    @BindView(R.id.end_time)
    TextView mEndTime;
    @BindView(R.id.buy_price)
    TextView mBuyPrice;
    @BindView(R.id.panic_buy_btn)
    Button mPanicBuyBtn;
    @BindView(R.id.image_view)
    ImageView mImageView;
    @BindView(R.id.cv_countdownViewTest1)
    CountdownView mCvCountdownViewTest1;

    public LimitBuyView(Context context) {
        this(context, null);
    }

    public LimitBuyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {
        View inflate = View.inflate(getContext(), R.layout.view_limit_buy, this);
        ButterKnife.bind(this, this);
    }

    @OnClick(R.id.panic_buy_btn)
    public void onClick() {
        Toast.makeText(getContext(), "需要升级付费版", Toast.LENGTH_SHORT).show();
    }

    public void bindView(LimitBuyBean.ProductListBean productListBean) {
        String name = productListBean.getName();
        mCommodityName.setText(name);
        long leftTime = productListBean.getLeftTime();
        mCvCountdownViewTest1.start(leftTime);
        mCvCountdownViewTest1.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {

            }
        });
        int limitPrice = productListBean.getLimitPrice();
        String end = " 限时抢购价￥%s";
        String format = String.format(end, String.valueOf(limitPrice));
        mBuyPrice.setText(format);
        Glide.with(getContext()).load(Constant.HOST + productListBean.getPic()).into(mImageView);

    }
}
