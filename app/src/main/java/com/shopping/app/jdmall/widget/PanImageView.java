package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;

/**
 * Created by panpan on 2017/4/6.
 */

public class PanImageView extends RelativeLayout {
    public PanImageView(Context context) {
        this(context,null);
    }

    public PanImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.view_image_view,this);
    }
}
