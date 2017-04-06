package com.shopping.app.jdmall.ui.activity;

import android.widget.AbsListView;

/**
 * Created by Administrator on 2017/4/6.
 * 自己写的,用于上拉加载更多的activity,对listview进行滚动监听
 */

public abstract class BaseListLoadMoreActivity extends BaseListActivity {
    @Override
    protected void initListView() {
        super.initListView();
        getListView().setOnScrollListener(mOnScrollListener);
    }
    AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if(scrollState == SCROLL_STATE_IDLE) {
                if(getListView().getLastVisiblePosition() == getLoadMorePoistion()) {
                    startLoadMoreData();
                }
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    };
    private int getLoadMorePoistion() {
        return getAdapter().getCount() - 1;
    }
    protected abstract void startLoadMoreData();
}
