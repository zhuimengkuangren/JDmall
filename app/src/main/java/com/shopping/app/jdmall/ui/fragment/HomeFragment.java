package com.shopping.app.jdmall.ui.fragment;


import android.view.View;


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

    @Override
    protected View onCreateContentView() {
        HomeView homeView = new HomeView(getContext());
        homeView.bindView(mBannerBean);
        return homeView;
    }
}
