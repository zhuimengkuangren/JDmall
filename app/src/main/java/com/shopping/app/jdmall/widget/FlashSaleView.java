package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;

/**
 * Created by Administrator on 2017/4/6.
 * FlashSaleAdapter中使用的内容填充模块
 */

public class FlashSaleView extends RelativeLayout {
    public FlashSaleView(Context context) {
        this(context,null);
    }

    public FlashSaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_flash_sale,this);
    }
}
