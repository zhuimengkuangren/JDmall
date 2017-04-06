package com.shopping.app.jdmall.ui.fragment;


import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;

import com.leon.loopviewpagerlib.FunBanner;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.HomeTopicbean;
import com.shopping.app.jdmall.network.HeiMaRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 主页fragment
 */
public class HomeFragment extends BaseShopListFragment {

    private String mPicture;
    private static final String TAG = "HomeFragment";

    @Override
    protected View onCreateHeader() {
        Log.d(TAG, "onCreateHeader: =====zoule");
        return new FunBanner.Builder(getContext())
                .setHeightWidthRatio(0.377f)
                .setEnableAutoLoop(true)
                .setImageUrlHost(Constant.HOST+"home/")
                .setImageUrls(new String[]{mPicture})
                .build();
     

    }


    @Override
    protected void startLoadData() {
        Call<HomeTopicbean> homeBeanCall = HeiMaRetrofit.getInstance().getApi().listHome();
        homeBeanCall.enqueue(new Callback<HomeTopicbean>() {
            @Override
            public void onResponse(Call<HomeTopicbean> call, Response<HomeTopicbean> response) {
                //将应用列表数据加入数据集合中
                Log.d(TAG, "onresponse: =="+ response.body());

                //保存轮播图数据
                mPicture = response.body().getHomeTopic().get(getId()).getPic();
                onDataLoadedSuccess();
            }
            @Override
            public void onFailure(Call<HomeTopicbean> call, Throwable t) {
                Log.d(TAG, "onFailure: =="+t.getLocalizedMessage());
                onDataLoadedFailed();
            }
        });


    }


    @Override
    protected void onStartLoadMore() {

    }
    @Override
    protected BaseAdapter oncreateAdapter() {
        return null;
    }
}
