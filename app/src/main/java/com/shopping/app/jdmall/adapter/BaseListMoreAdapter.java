package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 * 自己的写的listview基类--rennb
 */

public abstract class BaseListMoreAdapter<T> extends BaseListAdapter<T> {
    public BaseListMoreAdapter(Context context, List list) {
        super(context, list);
    }
    class ViewHolder {
        View mView;

        public ViewHolder(View root) {
            mView = root;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = onCreateViewHolder(position);
            viewHolder.mView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        onBindViewHolder(viewHolder, position);
        return viewHolder.mView;
    }


    protected abstract void onBindViewHolder(ViewHolder viewHolder, int position);

    protected abstract ViewHolder onCreateViewHolder(int position);

    @Override
    protected View onCreateView(int position) {
        return null;
    }

    @Override
    protected void onBindView(int position, View convertView) {

    }
}
