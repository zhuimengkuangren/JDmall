package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.ui.activity.HomeMsgActivity;
import com.shopping.app.jdmall.ui.activity.TestScanActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2017/4/5.
 */

public class FindTitleView extends RelativeLayout {
    @BindView(R.id.sao_yi_sao_find_title)
    ImageView mSaoYiSaoFindTitle;
    @BindView(R.id.message_find)
    ImageView mMessageFind;
    @BindView(R.id.bar_person_find_title)
    ImageView mBarPersonFindTitle;

    public FindTitleView(Context context) {
        this(context, null);
    }

    public FindTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_title_find_fragment, this);
        ButterKnife.bind(this, this);
    }

    @OnClick({R.id.sao_yi_sao_find_title, R.id.message_find, R.id.bar_person_find_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sao_yi_sao_find_title:
                getContext().startActivity(new Intent(getContext(), TestScanActivity.class));
                break;
            case R.id.message_find:
                getContext().startActivity(new Intent(getContext(),HomeMsgActivity.class));
                break;
            case R.id.bar_person_find_title:
                //个人信息

                break;
        }
    }
}
