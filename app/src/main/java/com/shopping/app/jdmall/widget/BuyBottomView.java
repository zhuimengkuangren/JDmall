package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shopping.app.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/4/7.
 */

public class BuyBottomView extends LinearLayout {
    @BindView(R.id.buy_car)
    ImageView mBuyCar;
    @BindView(R.id.buy_count)
    TextView mBuyCount;

    public BuyBottomView(Context context) {
        this(context, null);
    }

    public BuyBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_bottom, this);
        ButterKnife.bind(this,this);
    }
}
