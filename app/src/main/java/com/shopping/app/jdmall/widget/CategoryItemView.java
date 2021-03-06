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
import com.shopping.app.jdmall.bean.CategoryItemBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by 龚浩 on 2017/4/5.
 */

public class CategoryItemView extends LinearLayout {
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_name)
    TextView mTvName;

    public CategoryItemView(Context context) {
        this(context, null);
    }

    public CategoryItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_category_item, this);
        ButterKnife.bind(this);
    }

    public void setData(CategoryItemBean.CategoryBean bean) {

        String name = bean.getName();
        String pic = bean.getPic();
        mTvName.setText(name);
        String url = Constant.HOST + pic;
       Glide.with(getContext()).load(url).bitmapTransform(new CropCircleTransformation(getContext())).crossFade(1000).into(mIvIcon);


    }
}
