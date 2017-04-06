package com.shopping.app.jdmall.adapter;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.shopping.app.jdmall.ui.activity.BaseListActivity;

/**
 * Created by Administrator on 2017/4/6.
 */

public abstract class BaseGirdActivity extends BaseListActivity {
    private BaseAdapter mAdapter;
    private GridView mGridView;

    @Override
    protected View onCreateContentView(){
        mGridView = new GridView(this);
        mGridView.setOnItemClickListener(mOnItemClickListener);
        mAdapter = oncreateAdapter();//子类重写,创建adapter
        mGridView.setAdapter(mAdapter);
        initGridView();
        return mGridView;
    }

    protected abstract void initGridView();

    @Override
    protected BaseAdapter oncreateAdapter() {
        return null;
    }

    @Override
    protected void startLoadData() {

    }
}
