package com.shopping.app.jdmall.ui.fragment;

import android.widget.AbsListView;

/**
 * 持加载更多的listview布局的基类fragment
 */

public abstract class BaseListLoadMoreFragment extends BaseListFragment {


    @Override
    protected void initListView() {
        getListview().setOnScrollListener(mOnScrollListener);
    }

    AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

            if (scrollState == SCROLL_STATE_IDLE && getListview().getLastVisiblePosition() == getLoadMorePosition()) {
                //如果是最后一个,加载更多
                startLoadMoreData();
            }

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    };

    private int getLoadMorePosition() {
        return getAdapter().getCount() - 1 + getListview().getHeaderViewsCount();//注意这是+一个头
    }

    protected abstract void startLoadMoreData();

}
