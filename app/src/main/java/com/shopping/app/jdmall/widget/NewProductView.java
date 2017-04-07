package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.FindBean;

/**
 * Created by Administrator on 2017/4/6.
 */

public class NewProductView extends LinearLayout {
    public NewProductView(Context context) {
        this(context,null);
    }

    public NewProductView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_new_product,this);
    }

    public void bindView(FindBean.ProductListBean productListBean) {

    }
}
