package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.ui.activity.FindRecommandActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/4/5.
 */

public class FindHorizotalScrollView extends RelativeLayout {

    private static final String TAG = "FindHorizotalScrollView";
    @BindView(R.id.iv_collect)
    ImageView mIvCollect;
    @BindView(R.id.iv_lottery)
    ImageView mIvLottery;
    @BindView(R.id.iv_history)
    ImageView mIvHistory;
    private LinearLayout mLinearLayout;
    private ImageView mStar;

    public static final String PRODUCT = "product";
    //public static final String NEW_PRODUCT = "newproduct";

    public FindHorizotalScrollView(Context context) {
        this(context, null);
    }

    public FindHorizotalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_horizotal_scrollview_findfragment, this);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.ll_horizotal_find_fragment);
        ButterKnife.bind(this, this);
        view.setOnClickListener(mOnClickListener);

        //星星按钮点击，弹出热门商品界面
        mIvCollect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FindRecommandActivity.class);
                intent.putExtra(PRODUCT, "热门商品");
                getContext().startActivity(intent);
            }
        });

        mIvHistory.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FindRecommandActivity.class);
                intent.putExtra(PRODUCT, "新品上架");
                getContext().startActivity(intent);
            }
        });
        //mLinearLayout.setOnClickListener(mOnClickListener);


    }

    //View里面没有实现点击的接口回调，因此只能每一个控件进行监听
    OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.iv_collect:
                    Intent intent = new Intent(getContext(), FindRecommandActivity.class);
                    intent.putExtra("hotproduct", "热门商品");
                    getContext().startActivity(intent);
                    break;
            }
        }
    };

}
