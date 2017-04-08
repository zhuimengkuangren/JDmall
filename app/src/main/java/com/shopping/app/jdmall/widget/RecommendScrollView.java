package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shopping.app.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by panpan on 2017/4/6.
 */

public class RecommendScrollView extends RelativeLayout {
    @BindView(R.id.one)
    ImageView mOne;
    @BindView(R.id.two)
    ImageView mTwo;
    @BindView(R.id.three)
    ImageView mThree;
    @BindView(R.id.four)
    ImageView mFour;
    @BindView(R.id.five)
    ImageView mFive;
    @BindView(R.id.six)
    ImageView mSix;

    public RecommendScrollView(Context context) {
        this(context, null);
    }

    public RecommendScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        ButterKnife.bind(this, this);
    }

    private void init() {
        inflate(getContext(), R.layout.view_recommend_scroll, this);
    }

    @OnClick({R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one:
                Toast.makeText(getContext(), "美女被点击了吗", Toast.LENGTH_SHORT).show();
                break;
            case R.id.two:
                break;
            case R.id.three:
                break;
            case R.id.four:
                break;
            case R.id.five:
                break;
            case R.id.six:
                break;
        }
    }
}
