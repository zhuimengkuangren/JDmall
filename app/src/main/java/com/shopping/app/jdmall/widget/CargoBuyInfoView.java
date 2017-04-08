package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.shopping.app.jdmall.R;

/**
 * Created by 龚浩 on 2017/4/8.
 */

public class CargoBuyInfoView extends LinearLayout {
    public CargoBuyInfoView(Context context) {
        this(context,null);
    }

    public CargoBuyInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.view_cargo_buy,this);
    }
}
