package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.view.View;

import com.shopping.app.jdmall.bean.CategoryItemBean;
import com.shopping.app.jdmall.widget.CategoryRightView;

import java.util.List;

/**
 * Created by 龚浩 on 2017/4/5.
 */

public class CategoryRithtListAdapter extends BaseListAdapter<CategoryItemBean.CategoryBean> {
    private List<CategoryItemBean.CategoryBean> mList;

    public CategoryRithtListAdapter(Context context, List<CategoryItemBean.CategoryBean> list) {
        super(context, list);
        mList = list;
    }

    @Override
    protected View onCreateView(int position) {
        return new CategoryRightView(getContext());
    }

    @Override
    protected void onBindView(int position, View convertView) {
        CategoryRightView view = (CategoryRightView) convertView;
        view.bindView(mList.get(position));
    }
}
