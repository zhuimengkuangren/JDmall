package com.shopping.app.jdmall.ui.activity;

import android.widget.BaseAdapter;

import com.shopping.app.jdmall.adapter.FlashSaleAdapter;
import com.shopping.app.jdmall.bean.FlashSaleBean;
import com.shopping.app.jdmall.network.JDRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/4/6.
 * 限时抢购的activity
 */

public class FlashSaleActivity extends BaseListLoadMoreActivity {

    private List<FlashSaleBean> mDataList;

    @Override
    protected void startLoadMoreData() {
        Call<List<FlashSaleBean>> listCall = JDRetrofit.getInstance().getApi().listLimitBuy(mDataList.size(), 10);
        listCall.enqueue(new Callback<List<FlashSaleBean>>() {
            @Override
            public void onResponse(Call<List<FlashSaleBean>> call, Response<List<FlashSaleBean>> response) {
                mDataList = response.body();
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<List<FlashSaleBean>> call, Throwable t) {
                onDataLoadedFailed();
            }
        });
    }

    @Override
    protected BaseAdapter oncreateAdapter() {
        return new FlashSaleAdapter(FlashSaleActivity.this,mDataList);
    }

    @Override
    protected void startLoadData() {
        Call<List<FlashSaleBean>> listCall = JDRetrofit.getInstance().getApi().listLimitBuy(0, 10);
        listCall.enqueue(new Callback<List<FlashSaleBean>>() {
            @Override
            public void onResponse(Call<List<FlashSaleBean>> call, Response<List<FlashSaleBean>> response) {
                mDataList = response.body();
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<List<FlashSaleBean>> call, Throwable t) {
                onDataLoadedFailed();
            }
        });
    }
}
