package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.ui.activity.BrandActivity;
import com.shopping.app.jdmall.ui.activity.FindRecommandActivity;
import com.shopping.app.jdmall.ui.activity.LimitBuyActivity;
import com.shopping.app.jdmall.ui.activity.TopicActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by panpan on 2017/4/5.
 */

public class PanCategroyView extends RelativeLayout {
    @BindView(R.id.buy)
    ImageView mBuy;
    @BindView(R.id.sell)
    ImageView mSell;
    @BindView(R.id.news)
    ImageView mNews;
    @BindView(R.id.hot)
    ImageView mHot;
    @BindView(R.id.recommend)
    ImageView mRecommend;

    public PanCategroyView(Context context) {
        this(context, null);
    }

    public PanCategroyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        ButterKnife.bind(this, this);
    }

    private static final String TAG = "PanCategroy";

    private void init() {
        inflate(getContext(), R.layout.view_pan_categroy, this);
    }

    @OnClick({R.id.buy, R.id.sell, R.id.news, R.id.hot, R.id.recommend})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buy:
                getContext().startActivity(new Intent(getContext(), LimitBuyActivity.class));
                break;
            case R.id.sell:
                getContext().startActivity(new Intent(getContext(), TopicActivity.class));
                break;
            case R.id.news:
                Intent intent1 = new Intent(getContext(), FindRecommandActivity.class);
                intent1.putExtra("product","新品上架");
                getContext().startActivity(intent1);
                break;
            case R.id.hot:
                Intent intent2 = new Intent(getContext(), FindRecommandActivity.class);
                intent2.putExtra("product", "热门商品");
                getContext().startActivity(intent2);
                break;
            case R.id.recommend:
                getContext().startActivity(new Intent(getContext(), BrandActivity.class));
                break;
        }
    }
}
