package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;

/**
 * Created by panpan on 2017/4/7.
 */

public class HomeListItemView extends RelativeLayout {
    public HomeListItemView(Context context) {
        this(context,null);
    }

    public HomeListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.view_home_list_item,this);
    }
}
