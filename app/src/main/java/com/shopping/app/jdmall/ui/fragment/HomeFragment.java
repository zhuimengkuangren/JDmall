package com.shopping.app.jdmall.ui.fragment;


import android.view.View;

import com.shopping.app.jdmall.bean.BannerBean;
import com.shopping.app.jdmall.network.JDRetrofit;
import com.shopping.app.jdmall.widget.HomePullToRefreshList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 主页fragment
 */
public class HomeFragment extends BaseFragment {


    private BannerBean mBannerBean;

    @Override
    protected void startLoadData() {
        Call<BannerBean> listHome = JDRetrofit.getInstance().getApi().listHome();
        listHome.enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                mBannerBean = response.body();
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {
                onDataLoadedFailed();
            }
        });
    }

    /*@Override
    protected View onCreateContentView() {
        HomeView homeView = new HomeView(getContext());
        homeView.bindView(mBannerBean);
        return homeView;
    }*/

    @Override
    protected View onCreateContentView() {
        List arr = new ArrayList();
        for (int i = 0; i < 10; i++) {
            arr.add(i + "");
        }

        HomePullToRefreshList homePullToRefreshList = new HomePullToRefreshList(getContext(),arr,mBannerBean);
        //listView.addHeaderView(new LoadingMoreView(getContext()));
       // listView.setDividerHeight(0);
       // listView.setAdapter(new HomeAdapter(getContext(), arr, mBannerBean));
        //homePullToRefreshList.setDatas(getContext(),arr,mBannerBean);
        // setListener(listView);
        return homePullToRefreshList;
    }

}
