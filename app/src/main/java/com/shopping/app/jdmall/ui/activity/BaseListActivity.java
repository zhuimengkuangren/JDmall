package com.shopping.app.jdmall.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/4/6.
 * 自己写的list activity -----rennb
 */

public abstract class BaseListActivity extends Base2Activity {

    private BaseAdapter mAdapter;
    private ListView mListView;

    protected ListView getListView() {
        return mListView;
    }

    protected BaseAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    protected View onCreateContentView() {
        mListView = new ListView(this);
        mListView.setOnItemClickListener(mOnItemClickListener);
        mAdapter = oncreateAdapter();//子类重写,创建adapter
        mListView.setAdapter(mAdapter);
        mListView.setDivider(null);
        View holder = onCreatHolderView();
        if(holder != null) {
            mListView.addFooterView(holder);
        }
        initListView();
        return mListView;
    }

    protected View onCreatHolderView(){
        return null;
    }

    protected void initListView(){}

    protected abstract BaseAdapter oncreateAdapter();

    protected AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            onListItemClick(position);
        }
    };

    protected void onListItemClick(int position){

    }
}
