package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.view.View;

import com.shopping.app.jdmall.bean.SaleBean;
import com.shopping.app.jdmall.widget.SaleItemView;

import java.util.List;

/**
 * Created by user on 2017/4/7.
 */

public class SaleAdapter extends BaseListLoadAdapter {
    public SaleAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    protected View oncreateNormalView() {
        SaleItemView saleItemView = new SaleItemView(getContext());
        return saleItemView;
    }

    @Override
    protected void onBindNormalView(int position, View convertView) {
        SaleBean.TopicBean bean  = (SaleBean.TopicBean) getList().get(position);
        SaleItemView convertView1 = (SaleItemView) convertView;
        convertView1.bindView(bean);
    }
}
