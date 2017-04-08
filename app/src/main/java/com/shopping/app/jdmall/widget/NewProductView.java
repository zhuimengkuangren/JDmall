package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.FindBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/6.
 */

public class NewProductView extends LinearLayout {
    @BindView(R.id.left_name)
    TextView mLeftName;
    @BindView(R.id.left_xian_chang_jia)
    TextView mLeftXianChangJia;
    @BindView(R.id.left_shi_chang_jia)
    TextView mLeftShiChangJia;
    @BindView(R.id.left_image)
    ImageView mLeftImage;
    @BindView(R.id.right_name)
    TextView mRightName;
    @BindView(R.id.right_xian_chang_jia)
    TextView mRightXianChangJia;
    @BindView(R.id.right_shi_chang_jia)
    TextView mRightShiChangJia;
    @BindView(R.id.right_image)
    ImageView mRightImage;

    public NewProductView(Context context) {
        this(context, null);
    }

    public NewProductView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_new_product, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(FindBean.ProductListBean productListBean) {

    }
}
