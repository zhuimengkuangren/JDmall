package com.shopping.app.jdmall.ui.fragment;

import android.widget.AbsListView;

import com.shopping.app.jdmall.bean.HomeTopicbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 */

public abstract class BaseShopListFragment extends BaseListFragment {

    private static final String TAG = "BaseShopListFragment";

    @Override
    protected void initListView() {
        super.initListView();
        getListview().setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (view.getLastVisiblePosition() == getLoadMorePosition()){
                    onStartLoadMore();
                }
            }


            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private int getLoadMorePosition() {
        return getAdapter().getCount() - 1 + getListview().getHeaderViewsCount();
    }

    protected abstract void onStartLoadMore();
    List<HomeTopicbean> mDataList = new ArrayList<HomeTopicbean>();
    public List<HomeTopicbean> getDataList() {
        return mDataList;
    }



}
