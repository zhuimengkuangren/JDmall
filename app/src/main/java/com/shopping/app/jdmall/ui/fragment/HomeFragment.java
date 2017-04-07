package com.shopping.app.jdmall.ui.fragment;


import android.view.View;

import com.shopping.app.jdmall.bean.BannerBean;
import com.shopping.app.jdmall.widget.HomePullToRefreshList;

import java.util.ArrayList;
import java.util.List;


/**
 * 主页fragment
 */
public class HomeFragment extends BaseFragment {


    private BannerBean mBannerBean;

    @Override
    protected void startLoadData() {
       onDataLoadedSuccess();
    }

    @Override
    protected View onCreateContentView() {
        List arr = new ArrayList();
        for (int i = 0; i < 10; i++) {
            arr.add(i + "");
        }

        HomePullToRefreshList homePullToRefreshList = new HomePullToRefreshList(getContext(),arr,0);
        return homePullToRefreshList;
    }

}
