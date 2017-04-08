package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.shopping.app.jdmall.R;

/**
 * Created by Administrator on 2017/4/8.
 */

public class BrandFooterViewinfo extends LinearLayout {
    public BrandFooterViewinfo(Context context) {
        this(context,null);
    }

    public BrandFooterViewinfo(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {
        LayoutInflater.from(getContext()).inflate(R.layout.brand_footer_vew_info,this);
    }
}
