package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.ui.activity.FindRecommandActivity;

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
                Toast.makeText(getContext(), "点击了吗", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sell:
                break;
            case R.id.news:
                break;
            case R.id.hot:
                Intent intent = new Intent(getContext(), FindRecommandActivity.class);
                intent.putExtra("product", "热门商品");
                getContext().startActivity(intent);
                break;
            case R.id.recommend:
                break;
        }
    }
}
