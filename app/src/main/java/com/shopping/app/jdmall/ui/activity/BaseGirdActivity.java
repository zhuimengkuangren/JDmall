package com.shopping.app.jdmall.ui.activity;

import android.view.View;
import android.widget.BaseAdapter;

/**
 * Created by Administrator on 2017/4/6.
 */

public abstract class BaseGirdActivity extends BaseListActivity {
    private BaseAdapter mAdapter;

    @Override
    protected View onCreateContentView(){
        return null;
    }

    protected abstract View onCreatHolderView();

    protected abstract void initGridView();

    @Override
    protected BaseAdapter oncreateAdapter() {
        return null;
    }

    @Override
    protected void startLoadData() {

    }
}
