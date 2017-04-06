package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.shopping.app.jdmall.R;

/**
 * author: admin
 * created on: 2017/4/6 8:56
 * description:
 */
public class LoginAndRegisterView extends LinearLayout {

    public LoginAndRegisterView(Context context) {
        this(context,null);
    }

    public LoginAndRegisterView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.login_table,this);
    }
}
