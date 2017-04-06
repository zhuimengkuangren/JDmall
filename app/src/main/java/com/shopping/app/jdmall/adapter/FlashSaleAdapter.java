package com.shopping.app.jdmall.adapter;

import android.content.Context;

import com.shopping.app.jdmall.widget.FlashSaleView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 * 限时抢购的adapter
 */

public class FlashSaleAdapter extends BaseLoadMoreAdapter {

    public FlashSaleAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    protected void onBindNormalViewHolder(ViewHolder viewHolder, int position) {

    }

    @Override
    protected ViewHolder onCreateNormalViewHolder() {
        return new ViewHolder(new FlashSaleView(getContext()));
    }
}
