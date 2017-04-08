package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.shopping.app.jdmall.R;

import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/4/8.
 */

public class AddressView extends LinearLayout{
    public AddressView(Context context) {
        this(context,null);
    }

    public AddressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.view_address,this);
        ButterKnife.bind(this,this);
    }
}
