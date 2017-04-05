package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.shopping.app.jdmall.R;


/**
 * 下拉刷新的自定义view
 */
public class LoadingMoreView extends FrameLayout {

    public LoadingMoreView(Context context) {
        this(context,null);
    }

    public LoadingMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_loading_more,this);
    }
}
