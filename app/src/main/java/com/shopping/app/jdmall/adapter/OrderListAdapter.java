package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.view.View;

import com.shopping.app.jdmall.bean.OrderBean;
import com.shopping.app.jdmall.widget.OrderListItemView;

import java.util.List;

/**
 * Created by xuanxuan on 2017/4/8.
 */

public class OrderListAdapter extends BaseListAdapter<OrderBean.OrderListBean> {
    private static final String TAG = "OrderListAdapter";
    Context context;
    List<OrderBean.OrderListBean> mDataList;

    public OrderListAdapter(Context context, List<OrderBean.OrderListBean> list) {
        super(context, list);
    }


    @Override
    protected View onCreateView(int position) {

        return new OrderListItemView(getContext());
    }

    @Override
    protected void onBindView(int position, View convertView) {
        ((OrderListItemView)convertView).bindView(mDataList.get(position));
    }

    public void setData(List<OrderBean.OrderListBean> orderList) {
        mDataList = orderList;
      //  Log.d(TAG, "setData: " + mDataList.get(0).getStatus());
    }
}
