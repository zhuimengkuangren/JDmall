package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.view.View;

import com.shopping.app.jdmall.widget.CategoryListItem;

import java.util.List;

/**
 * Created by 龚浩 on 2017/4/5.
 */

public class CategoryListAdapter extends BaseListAdapter<String> {
    private List<String> mList;

    public CategoryListAdapter(Context context, List<String> list) {
        super(context, list);
        mList = list;
    }

    @Override
    protected View onCreateView(int position) {
        return new CategoryListItem(getContext());
    }

    @Override
    protected void onBindView(int position, View convertView) {
        CategoryListItem view = (CategoryListItem) convertView;
        view.setData(mList.get(position));
    }
}
