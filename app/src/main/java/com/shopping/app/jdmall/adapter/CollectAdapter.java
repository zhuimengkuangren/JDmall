package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.view.View;

import com.shopping.app.jdmall.bean.CollectionBean;
import com.shopping.app.jdmall.widget.CollectionListItemView;

import java.util.List;

/**
 * Created by xuanxuan on 2017/4/9.
 */

public class CollectAdapter extends BaseListAdapter<CollectionBean.ProductListBean> {
    List<CollectionBean.ProductListBean> mDataList;
    public CollectAdapter(Context context, List<CollectionBean.ProductListBean> list) {
        super(context, list);
        mDataList = list;
    }

    @Override
    protected View onCreateView(int position) {
        return new CollectionListItemView(getContext());
    }

    @Override
    protected void onBindView(int position, View convertView) {
        ((CollectionListItemView)convertView).bindView(mDataList.get(position));
    }
}
