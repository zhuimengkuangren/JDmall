package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.shopping.app.jdmall.R;

/**
 * Created by 龚浩 on 2017/4/5.
 */
public class CategoryView  extends LinearLayout{
    public CategoryView(Context context) {
        this(context,null);
    }

    public CategoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_category,this);
    }
}
