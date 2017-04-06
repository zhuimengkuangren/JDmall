package com.shopping.app.jdmall.adapter;

import android.content.Context;

import com.shopping.app.jdmall.bean.FlashSaleBean;
import com.shopping.app.jdmall.widget.FlashSaleView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 * 限时抢购的adapter
 */

public class FlashSaleAdapter extends BaseLoadMoreAdapter<FlashSaleBean> {

    public FlashSaleAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    protected void onBindNormalViewHolder(ViewHolder viewHolder, int position) {
        ((FlashSaleView)viewHolder.mView).bindView(getList().get(position));
    }

    @Override
    protected ViewHolder onCreateNormalViewHolder() {
        return new ViewHolder(new FlashSaleView(getContext()));
    }
}
