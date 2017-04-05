package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;

/**
 * Created by user on 2017/4/5.
 */

public class FindTitleView extends RelativeLayout {
    public FindTitleView(Context context) {
        this(context,null);
    }

    public FindTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
       View.inflate(getContext(), R.layout.view_title_find_fragment,this);
    }
}
