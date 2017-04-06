package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;

/**
 * Created by panpan on 2017/4/5.
 */

public class HomeSearchView extends RelativeLayout {
    public HomeSearchView(Context context) {
        this(context,null);
    }

    public HomeSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.view_serach,this);
        init();
    }

    private void init() {

    }
}
