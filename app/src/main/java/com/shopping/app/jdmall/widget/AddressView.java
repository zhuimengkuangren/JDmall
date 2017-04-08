package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.LocationBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/4/8.
 */

public class AddressView extends LinearLayout {
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;

    public AddressView(Context context) {
        this(context, null);
    }

    public AddressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.view_address, this);
        ButterKnife.bind(this, this);
    }

    public void setData(LocationBean.AddressListBean bean) {
        mTvName.setText(bean.getName());
        mTvPhone.setText(bean.getPhoneNumber());
        mTvAddress.setText(bean.getProvince()+bean.getCity()+bean.getAddressArea()+bean.getAddressDetail());
    }
}
