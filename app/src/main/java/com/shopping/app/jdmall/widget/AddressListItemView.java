package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.AddressBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuanxuan on 2017/4/8.
 */

public class AddressListItemView extends RelativeLayout {
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.total_address)
    TextView totalAddress;

    public AddressListItemView(Context context) {
        this(context, null);
    }

    public AddressListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_address_list_item, this);
        ButterKnife.bind(this,this);
    }

    public void bindView(AddressBean.AddressListBean addressListBean) {
            name.setText(addressListBean.getName());
        phone.setText(addressListBean.getPhoneNumber());
        String address = addressListBean.getProvince()+addressListBean.getCity() + addressListBean.getAddressArea()+ addressListBean.getAddressDetail();
        totalAddress.setText(address);
    }
}
