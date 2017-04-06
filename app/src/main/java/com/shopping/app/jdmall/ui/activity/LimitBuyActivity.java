package com.shopping.app.jdmall.ui.activity;

import android.widget.BaseAdapter;

import com.shopping.app.jdmall.adapter.LimitBuyAdapter;
import com.shopping.app.jdmall.bean.LimitBuyBean;
import com.shopping.app.jdmall.network.JDRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/4/6.
 * 限时抢购的activity
 */

public class LimitBuyActivity extends BaseListLoadMoreActivity {

    private List<LimitBuyBean> mDataList;
    int count = 0;
    @Override
    protected void startLoadMoreData() {
        Call<List<LimitBuyBean>> listCall = JDRetrofit.getInstance().getApi().listLimitBuy(++count, 10);
        listCall.enqueue(new Callback<List<LimitBuyBean>>() {
            @Override
            public void onResponse(Call<List<LimitBuyBean>> call, Response<List<LimitBuyBean>> response) {
                mDataList = response.body();
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<List<LimitBuyBean>> call, Throwable t) {
                onDataLoadedFailed();
            }
        });
    }

    @Override
    protected BaseAdapter oncreateAdapter() {
        return new LimitBuyAdapter(LimitBuyActivity.this,mDataList);
    }

    @Override
    protected void startLoadData() {
        Call<List<LimitBuyBean>> listCall = JDRetrofit.getInstance().getApi().listLimitBuy(0, 10);
        listCall.enqueue(new Callback<List<LimitBuyBean>>() {
            @Override
            public void onResponse(Call<List<LimitBuyBean>> call, Response<List<LimitBuyBean>> response) {
                mDataList = response.body();
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<List<LimitBuyBean>> call, Throwable t) {
                onDataLoadedFailed();
            }
        });
    }
}
