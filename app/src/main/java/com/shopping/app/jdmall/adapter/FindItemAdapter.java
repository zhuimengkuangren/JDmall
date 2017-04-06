package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.widget.FindListItemView;

import java.util.List;

/**
 * Created by user on 2017/4/5.
 */

public class FindItemAdapter extends BaseListAdapter {
    private static final String TAG = "FindItemAdapter";
    private List<FindBean.ProductListBean> mList;

    public FindItemAdapter(Context context, List<FindBean.ProductListBean> list) {
        super(context, list);
    }

    @Override//这是每个条目的bean,
    protected View onCreateView(int position) {
        mList = getList();
        Log.d(TAG, "onCreateView: 8888");
        FindListItemView itemView = new FindListItemView(getContext());
        return itemView;
    }

    @Override
    protected void onBindView(int position, View convertView) {
        FindBean.ProductListBean productListBean = mList.get(position);
        FindListItemView findListItemView= (FindListItemView) convertView;
        findListItemView.bindView(productListBean);
    }
}
