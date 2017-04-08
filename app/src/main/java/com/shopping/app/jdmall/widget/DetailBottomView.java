package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.SaleBean;
import com.shopping.app.jdmall.network.JDRetrofit;

import retrofit2.Call;

/**
 * Created by user on 2017/4/6.
 */

public class DetailBottomView extends RelativeLayout {
    public DetailBottomView(Context context) {
        this(context,null);
    }

    public DetailBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        loadData();

    }
    private void loadData() {
        Call<SaleBean> beanCall = JDRetrofit.getInstance().getApi().listSale(1, 8);
        /*beanCall.enqueue(new Callback<SaleBean>() {


            @Override
            public void onResponse(Call<SaleBean> call, Response<SaleBean> response) {
                mTopic = response.body().getTopic();
                mSaleAdapter = new SaleAdapter(SalesActivity.this, mTopic);
                mLvSale.setAdapter(mSaleAdapter);
                mLvSale.setOnScrollListener(scrollListernr);
            }

            @Override
            public void onFailure(Call<SaleBean> call, Throwable t) {

            }
        });*/
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_detail_bottom_viewpager, this);

    }
}
