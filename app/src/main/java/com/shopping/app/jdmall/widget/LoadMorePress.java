package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;

/**
 * Created by Administrator on 2017/4/6.
 * 上拉加载更多的progress进度条
 */

public class LoadMorePress extends RelativeLayout {
    public LoadMorePress(Context context) {
        this(context,null);
    }

    public LoadMorePress(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_load_more_press,this);
    }
}
