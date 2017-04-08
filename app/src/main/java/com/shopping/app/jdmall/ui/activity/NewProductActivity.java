package com.shopping.app.jdmall.ui.activity;

import android.view.View;
import android.widget.BaseAdapter;

import com.leon.loopviewpagerlib.FunBanner;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.network.JDRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/4/6.
 * 新品上架    跟wuweihua的做重复了  名为 findRecommandActivaty
 */

public class NewProductActivity extends BaseListLoadMoreActivity {

    private FindBean mDatalist;

    protected View onCreatHolderView() {
        FunBanner funBanner = new FunBanner.Builder(NewProductActivity.this)
                .setHeightWidthRatio(0.377f)
                .setEnableAutoLoop(true)
                .setImageUrlHost(Constant.HOST)
                .build();
        return funBanner;
    }
    @Override
    protected void startLoadMoreData() {
        Call<FindBean> saleDown = JDRetrofit.getInstance().getApi().listFind(0, 10, "saleDown");
        saleDown.enqueue(new Callback<FindBean>() {
            @Override
            public void onResponse(Call<FindBean> call, Response<FindBean> response) {
                mDatalist = response.body();
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<FindBean> call, Throwable t) {
                onDataLoadedFailed();
            }
        });
    }

    @Override
    protected BaseAdapter oncreateAdapter() {
        return null;
    }

    @Override
    protected String getTitleFromSon() {
        return null;
    }

    @Override
    protected void startLoadData() {
        Call<FindBean> saleDown = JDRetrofit.getInstance().getApi().listFind(0, 10, "saleDown");
        saleDown.enqueue(new Callback<FindBean>() {
            @Override
            public void onResponse(Call<FindBean> call, Response<FindBean> response) {
                mDatalist = response.body();
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<FindBean> call, Throwable t) {
                onDataLoadedFailed();
            }
        });
    }
}
