package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.CategoryItemBean;
import com.shopping.app.jdmall.ui.activity.QueryCargoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by panpan on 2017/4/7.
 */

public class HomeListItemView extends RelativeLayout {
    @BindView(R.id.image1)
    ImageView mImage1;
    @BindView(R.id.text1)
    TextView mText1;
    @BindView(R.id.image2)
    ImageView mImage2;
    @BindView(R.id.text2)
    TextView mText2;
    @BindView(R.id.image3)
    ImageView mImage3;
    @BindView(R.id.text3)
    TextView mText3;
    @BindView(R.id.image4)
    ImageView mImage4;
    @BindView(R.id.text4)
    TextView mText4;
    @BindView(R.id.ll_01)
    LinearLayout mLl01;
    @BindView(R.id.ll_02)
    LinearLayout mLl02;
    @BindView(R.id.ll_03)
    LinearLayout mLl03;
    @BindView(R.id.ll_04)
    LinearLayout mLl04;

    public HomeListItemView(Context context) {
        this(context, null);
    }

    public HomeListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.view_home_list_item, this);
        ButterKnife.bind(this, this);
    }


    public void bindView(List<CategoryItemBean.CategoryBean> categoryBeen) {
        String host = "http://10.0.2.2:8080/market";
        Glide.with(getContext()).load(host + categoryBeen.get(0).getPic()).into(mImage1);
        mText1.setText(categoryBeen.get(0).getName());

        Glide.with(getContext()).load(host + categoryBeen.get(1).getPic()).into(mImage2);
        mText2.setText(categoryBeen.get(1).getName());

        Glide.with(getContext()).load(host + categoryBeen.get(2).getPic()).into(mImage3);
        mText3.setText(categoryBeen.get(2).getName());

        Glide.with(getContext()).load(host + categoryBeen.get(3).getPic()).into(mImage4);
        mText4.setText(categoryBeen.get(3).getName());
    }

    @OnClick({R.id.ll_01, R.id.ll_02, R.id.ll_03, R.id.ll_04})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_01:
               getContext().startActivity(new Intent(getContext(), QueryCargoActivity.class));
                break;
            case R.id.ll_02:
                getContext().startActivity(new Intent(getContext(), QueryCargoActivity.class));
                break;
            case R.id.ll_03:
                getContext().startActivity(new Intent(getContext(), QueryCargoActivity.class));
                break;
            case R.id.ll_04:
                getContext().startActivity(new Intent(getContext(), QueryCargoActivity.class));
                break;
        }
    }
}
