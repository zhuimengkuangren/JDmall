package com.shopping.app.jdmall.adapter;

import android.content.Context;

import com.shopping.app.jdmall.bean.LimitBuyBean;
import com.shopping.app.jdmall.widget.LimitBuyView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 * 限时抢购的adapter
 */

public class LimitBuyAdapter extends BaseLoadMoreAdapter<LimitBuyBean.ProductListBean> {

    public LimitBuyAdapter(Context context, List<LimitBuyBean.ProductListBean> list) {
        super(context, list);
    }

    @Override
    protected void onBindNormalViewHolder(ViewHolder viewHolder, int position) {
        ((LimitBuyView)viewHolder.mView).bindView(getList().get(position));
    }

    @Override
    protected ViewHolder onCreateNormalViewHolder() {
        return new ViewHolder(new LimitBuyView(getContext()));
    }
}
