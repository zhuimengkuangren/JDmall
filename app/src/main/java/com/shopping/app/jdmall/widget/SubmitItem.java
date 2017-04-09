package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
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
    @BindView(R.id.more)
    ImageView mMore;

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
            case 5:
                mTvTitle.setText("支付方式");
                mDesc.setText("支付宝");
                break;
            case 4:
                mTvTitle.setText("送货时间");
                mDesc.setText("时间不限，工作日双休日及公众假期均可送货");
                break;
            case 3:
                mTvTitle.setText("发票类型");
                mDesc.setText("个人");
                break;
            case 2:
                mTvTitle.setText("发票抬头");
                mDesc.setText("传智慧播客教育科技有限公司");
                mMore.setVisibility(View.GONE);
                break;
            case 1:
                mTvTitle.setText("发票内容");
                mDesc.setText("服装");
                mMore.setVisibility(View.GONE);
                break;
        }
    }

    public void setTextData(String text) {
        mDesc.setText(text);
    }
}
