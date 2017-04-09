package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.view.View;

import com.shopping.app.jdmall.widget.LoadingMoreView;

import java.util.List;

/**
 * 支持下拉刷新的listview adapter
 */
public abstract class BaseListLoadAdapter<T> extends BaseListAdapter<T> {
    private static final int ITEM_TYPE_NORMAL = 0;
    private static final int ITEM_TYPE_LOADING = 1;

    public BaseListLoadAdapter(Context context, List<T> list) {
        super(context, list);
    }

    @Override
    public int getCount() {
        if (getList() == null) {
            return 0;
        }
        return getList().size() + 1;//增加一个位置给loading视图
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {
            return ITEM_TYPE_LOADING;//最后一个item为loading视图
        }
        return ITEM_TYPE_NORMAL;
    }


    @Override
    protected View onCreateView(int position) {
        if (getItemViewType(position) == ITEM_TYPE_NORMAL) {
            return oncreateNormalView(); //子类实现,创建一般视图
        }
        return new LoadingMoreView(getContext());//创建loading视图
    }

    protected abstract View oncreateNormalView();


    @Override
    protected void onBindView(int position, View convertView) {
        if (getItemViewType(position) == ITEM_TYPE_NORMAL) {
            onBindNormalView(position, convertView);//子类实现,绑定一般视图
        }
    }

    protected abstract void onBindNormalView(int position, View convertView);
}
