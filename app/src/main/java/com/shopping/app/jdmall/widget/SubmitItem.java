package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;

import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/4/8.
 */

public class SubmitItem extends RelativeLayout {

    public SubmitItem(Context context) {
        this(context,null);
    }

    public SubmitItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.view_sumbit_item,this);
        ButterKnife.bind(this,this);
    }
}
