package com.shopping.app.jdmall.ui.fragment;


import android.view.View;
import android.widget.TextView;


/**
 * 分类fragment
 */
public class CategoryFragment extends BaseFragment {


    @Override
    protected void startLoadData() {
        onDataLoadedSuccess();
    }

    @Override
    protected View onCreateContentView() {
        TextView textView = new TextView(getContext());
        textView.setText("分类刷新成功!!");
        return textView;
    }
}
