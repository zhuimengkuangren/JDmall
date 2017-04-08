package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;

/**
 * Created by panpan on 2017/4/6.
 */

public class SecondKillView extends RelativeLayout {
    @BindView(R.id.cv_countdownViewTest1)
    CountdownView mCvCountdownViewTest1;

    public SecondKillView(Context context) {
        this(context, null);
    }

    public SecondKillView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.view_second_kill, this);
        ButterKnife.bind(this, this);
        init();
    }

    private void init() {
        CountdownView mCvCountdownView = (CountdownView) findViewById(R.id.cv_countdownViewTest1);
        mCvCountdownView.start(3423523);
    }
}
