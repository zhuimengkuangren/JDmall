package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;

/**
 * Created by user on 2017/4/6.
 */

public class FindDetailTalkView extends RelativeLayout {
    public FindDetailTalkView(Context context) {
        this(context,null);
    }

    public FindDetailTalkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_find_detail_talk, this);
    }
}