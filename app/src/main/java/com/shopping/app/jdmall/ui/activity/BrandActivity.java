package com.shopping.app.jdmall.ui.activity;

import android.widget.BaseAdapter;

import com.shopping.app.jdmall.adapter.BrandAdapter;
import com.shopping.app.jdmall.bean.BrandRenBean;
import com.shopping.app.jdmall.network.JDRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/4/6.
 * 推荐品牌
 */

public class BrandActivity extends BaseListActivity {

    private List<BrandRenBean.BrandBean> mDataList;

    @Override
    protected BaseAdapter oncreateAdapter() {
        return new BrandAdapter(BrandActivity.this,mDataList);
    }

    @Override
    protected void startLoadData() {
        Call<BrandRenBean> brandRenBeanCall =
                JDRetrofit.getInstance().getApi().listBrandRen();
        brandRenBeanCall.enqueue(new Callback<BrandRenBean>() {
            @Override
            public void onResponse(Call<BrandRenBean> call, Response<BrandRenBean> response) {
                mDataList = response.body().getBrand();
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<BrandRenBean> call, Throwable t) {
                onDataLoadedFailed();
            }
        });
    }
}
