package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/7.
 */

public class BrandViewInfo extends LinearLayout {
    @BindView(R.id.image_view)
    ImageView mImageView;
    @BindView(R.id.text_view)
    TextView mTextView;

    public BrandViewInfo(Context context) {
        this(context, null);
    }

    public BrandViewInfo(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_brand_info, this);
        ButterKnife.bind(this,this);
    }

    public void bindView(String name, String pic) {
        Glide.with(getContext()).load(Constant.HOST + pic).into(mImageView);
        mTextView.setText(name);
    }
}
