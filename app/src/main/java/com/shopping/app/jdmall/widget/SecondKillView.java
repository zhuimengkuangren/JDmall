package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.ui.activity.TempImageActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;

/**
 * Created by panpan on 2017/4/6.
 */

public class SecondKillView extends RelativeLayout {
    @BindView(R.id.cv_countdownViewTest1)
    CountdownView mCvCountdownViewTest1;
    @BindView(R.id.tv_second_kill)
    TextView mTvSecondKill;

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

    @OnClick(R.id.tv_second_kill)
    public void onClick() {

        getContext().startActivity(new Intent(getContext(),TempImageActivity.class));
    }
}
