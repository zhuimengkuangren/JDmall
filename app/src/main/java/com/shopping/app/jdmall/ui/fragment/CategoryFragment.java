package com.shopping.app.jdmall.ui.fragment;


import android.view.View;

import com.shopping.app.jdmall.widget.CategoryView;


/**
 * 分类fragment
 */
public class CategoryFragment extends BaseFragment {


    @Override
    protected void init() {

    }

    @Override
    protected void startLoadData() {
        onDataLoadedSuccess();
    }

    @Override
    protected View onCreateContentView() {
        CategoryView categoryView = new CategoryView(getContext());
        return categoryView;
    }
}
