package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;

/**
 * Created by panpan on 2017/4/6.
 */

public class RecommendScrollView extends RelativeLayout {
    public RecommendScrollView(Context context) {
        this(context,null);
    }

    public RecommendScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_recommend_scroll,this);
    }

}
