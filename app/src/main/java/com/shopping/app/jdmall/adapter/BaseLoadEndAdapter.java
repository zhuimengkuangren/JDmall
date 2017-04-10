package com.shopping.app.jdmall.adapter;

import android.content.Context;

import com.shopping.app.jdmall.widget.BrandFooterViewinfo;

import java.util.List;

/**
 * Created by Administrator on 2017/4/8.
 */

public abstract class BaseLoadEndAdapter<T> extends BaseListMoreAdapter<T> {
    private static final int ITEM_TYPE_NORMAL = 0;
    private static final int ITEM_TYPE_LOAD_MORE = 1;

    public BaseLoadEndAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    protected void onBindViewHolder(ViewHolder viewHolder, int position) {
        if (ITEM_TYPE_NORMAL == getItemViewType(position)) {
            onBindNormalViewHolder(viewHolder, position);//子类来实现普通类型的item的绑定
        }
    }

    protected abstract void onBindNormalViewHolder(ViewHolder viewHolder, int position);

    @Override
    protected ViewHolder onCreateViewHolder(int position) {
        if(getItemViewType(position) == ITEM_TYPE_NORMAL) {
            return onCreateNormalViewHolder();
        }else {
            return new ViewHolder(new BrandFooterViewinfo(getContext()));
        }
    }

    protected abstract ViewHolder onCreateNormalViewHolder();

    @Override
    public int getCount() {
        if(getList() == null) {
            return 0;
        }
        return getList().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == getCount() - 1){
            return ITEM_TYPE_LOAD_MORE;
        }
        return ITEM_TYPE_NORMAL;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
