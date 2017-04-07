package com.shopping.app.jdmall.ui.activity;

import android.widget.BaseAdapter;

import com.shopping.app.jdmall.bean.LimitBuyBean;
import com.shopping.app.jdmall.network.JDRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/4/6.
 * 限时抢购的activity
 */

public class LimitBuyActivity extends BaseListLoadMoreActivity {

    private LimitBuyBean mDataList;
    int count = 0;
    @Override
    protected void startLoadMoreData() {
        Call<LimitBuyBean> listCall = JDRetrofit.getInstance().getApi().listLimitBuy(++count, 10);
        listCall.enqueue(new Callback<LimitBuyBean>() {
            @Override
            public void onResponse(Call<LimitBuyBean> call, Response<LimitBuyBean> response) {
                mDataList = response.body();
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<LimitBuyBean> call, Throwable t) {
                onDataLoadedFailed();
            }
        });
    }

    @Override
    protected BaseAdapter oncreateAdapter() {
        return null;
    }

    @Override
    protected void startLoadData() {
        Call<LimitBuyBean> listCall = JDRetrofit.getInstance().getApi().listLimitBuy(0, 10);
        listCall.enqueue(new Callback<LimitBuyBean>() {
            @Override
            public void onResponse(Call<LimitBuyBean> call, Response<LimitBuyBean> response) {
                mDataList = response.body();
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<LimitBuyBean> call, Throwable t) {
                onDataLoadedFailed();
            }
        });
    }
}
