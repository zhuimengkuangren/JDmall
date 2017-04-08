package com.shopping.app.jdmall.ui.fragment;

import android.widget.BaseAdapter;

import com.shopping.app.jdmall.adapter.OrderListAdapter;
import com.shopping.app.jdmall.bean.OrderListsBean;

import java.util.List;

/**
 * Created by xuanxuan on 2017/4/8.
 */

public class AllOrderFragment extends BaseListFragment {

    private List<OrderListsBean.OrderListBean> mOrderListBean;

    @Override
    protected void startLoadData() {


    }

    @Override
    protected BaseAdapter oncreateAdapter() {
        return new OrderListAdapter(getContext(),mOrderListBean);
    }

}
