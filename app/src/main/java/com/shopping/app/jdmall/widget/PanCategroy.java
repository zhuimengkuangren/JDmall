package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;

/**
 * Created by panpan on 2017/4/5.
 */

public class PanCategroy extends RelativeLayout {
    public PanCategroy(Context context) {
        this(context,null);
    }

    public PanCategroy(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_pan_categroy,this);
    }
}
