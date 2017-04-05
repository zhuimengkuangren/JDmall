package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;

/**
 * Created by user on 2017/4/5.
 */

public class HorizotalScrollFindFragmentView extends RelativeLayout {

    private LinearLayout mLinearLayout;

    public HorizotalScrollFindFragmentView(Context context) {
        this(context, null);
    }

    public HorizotalScrollFindFragmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_horizotal_scrollview_findfragment, this);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.ll_horizotal_find_fragment);
    }

}
