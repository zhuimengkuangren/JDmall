package com.shopping.app.jdmall.adapter;

import android.content.Context;

import com.shopping.app.jdmall.widget.TopicView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */

public class TopicAdapter extends BaseLoadMoreAdapter {
    public TopicAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    protected void onBindNormalViewHolder(ViewHolder viewHolder, int position) {

    }

    @Override
    protected ViewHolder onCreateNormalViewHolder() {
        return new ViewHolder(new TopicView(getContext()));
    }
}
