package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import com.shopping.app.jdmall.R;

/**
 * Created by 龚浩 on 2017/4/8.
 */

public class IdentInfoView extends ScrollView {
    public IdentInfoView(Context context) {
        this(context,null);
    }

    public IdentInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.view_ident_info,this);
        init();
    }

    private void init() {
    }
}
