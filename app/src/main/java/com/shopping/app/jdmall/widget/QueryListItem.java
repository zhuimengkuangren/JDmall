package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;

/**
 * Created by 龚浩 on 2017/4/6.
 */

public class QueryListItem extends RelativeLayout {
    public QueryListItem(Context context) {
        this(context,null);
    }

    public QueryListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.view_query_item,this);
    }
}
