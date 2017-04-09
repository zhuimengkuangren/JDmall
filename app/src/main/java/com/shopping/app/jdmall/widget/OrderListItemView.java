package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.OrderBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuanxuan on 2017/4/8.
 */

public class OrderListItemView extends RelativeLayout {
    @BindView(R.id.order_status)
    TextView mOrderStatus;
    @BindView(R.id.order_id)
    TextView mOrderId;
    @BindView(R.id.date)
    TextView mDate;
    @BindView(R.id.total_price)
    TextView mTotalPrice;

    public OrderListItemView(Context context) {
        this(context, null);
    }

    public OrderListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_orderlist_item, this);
        ButterKnife.bind(this,this);
    }

    public void bindView(OrderBean.OrderListBean orderListBean) {
        mOrderStatus.setText("状态：" + orderListBean.getStatus());
        mOrderId.setText("编号：" + orderListBean.getOrderId());
        mDate.setText("时间：" + orderListBean.getTime());
        mTotalPrice.setText("金额：¥" + orderListBean.getPrice());
    }
}
