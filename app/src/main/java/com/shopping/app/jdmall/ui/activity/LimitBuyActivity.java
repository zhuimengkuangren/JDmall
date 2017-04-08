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

    private List<LimitBuyBean.ProductListBean> mDataList;
    private LimitBuyBean mLimitBuyBean;

    @Override
    protected void startLoadMoreData() {
        Call<LimitBuyBean> listCall = JDRetrofit.getInstance().getApi().listLimitBuy(mDataList.size(), mLimitBuyBean.getListCount());
        listCall.enqueue(new Callback<LimitBuyBean>() {
            @Override
            public void onResponse(Call<LimitBuyBean> call, Response<LimitBuyBean> response) {
                //mLimitBuyBean.setListCount(mDataList.size());
                mDataList.addAll(response.body().getProductList());
                getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<LimitBuyBean> call, Throwable t) {
                onDataLoadedFailed();
            }
        });
    }

    @Override
    protected BaseAdapter oncreateAdapter() {
        return new LimitBuyAdapter(this,mDataList);
    }

    @Override
    protected void startLoadData() {
        Call<LimitBuyBean> limitBuyBeanCall = JDRetrofit.getInstance().getApi().listLimitBuy(0, 10);
        limitBuyBeanCall.enqueue(new Callback<LimitBuyBean>() {
            @Override
            public void onResponse(Call<LimitBuyBean> call, Response<LimitBuyBean> response) {
                mLimitBuyBean = response.body();
                mDataList = mLimitBuyBean.getProductList();
                //getSupportActionBar().setTitle(mLimitBuyBean.getResponse());
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<LimitBuyBean> call, Throwable t) {
                onDataLoadedFailed();
            }
        });
    }
}
