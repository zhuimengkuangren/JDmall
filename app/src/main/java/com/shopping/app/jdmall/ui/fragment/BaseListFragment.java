package com.shopping.app.jdmall.ui.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * 布局为listview的基类fragment
 */

public abstract class BaseListFragment extends BaseFragment {

    private ListView mListview;
    private BaseAdapter mAdapter;



    public ListView getListview() {
        return mListview;
    }

    public BaseAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    protected View onCreateContentView() {
        mListview = new ListView(getContext());
        mAdapter = oncreateAdapter();//子类重写,创建adapter
        mListview.setAdapter(mAdapter);
        mListview.setOnItemClickListener(mOnItemClickListener);
        mListview.setDivider(null);//去掉分割线
        View header = onCreateHeader();//子类重写,创建头
        if (header != null) {
            mListview.addHeaderView(header);
        }
        //子类重写,初始化listview
        initListView();
        return mListview;
    }

    protected abstract BaseAdapter oncreateAdapter();

    protected View onCreateHeader() {
        return null;
    }

    protected void initListView() {
    }

    AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            position -= getListview().getHeaderViewsCount();
            onListItemClick(position);//子类重写,item点击处理
        }
    };

    protected void onListItemClick(int position) {


    }


}
