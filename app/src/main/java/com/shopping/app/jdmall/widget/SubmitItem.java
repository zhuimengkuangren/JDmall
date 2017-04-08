package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shopping.app.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/4/8.
 */

public class SubmitItem extends RelativeLayout {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.desc)
    TextView mDesc;

    public SubmitItem(Context context) {
        this(context, null);
    }

    public SubmitItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.view_sumbit_item, this);
        ButterKnife.bind(this, this);
    }

    public void setData(int i) {
        switch (i) {
            case 0:
                mTvTitle.setText("支付方式");
                break;
            case 1:
                mTvTitle.setText("送货时间");
                break;
            case 2:
                mTvTitle.setText("发票类型");
                break;
            case 3:
                mTvTitle.setText("发票抬头");
                break;
            case 4:
                mTvTitle.setText("发票内容");
                break;
        }
    }
}
