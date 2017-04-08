package com.shopping.app.jdmall.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.adapter.SaleAdapter;
import com.shopping.app.jdmall.bean.SaleBean;
import com.shopping.app.jdmall.network.JDRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 2017/4/7.
 */

public class SalesActivity extends AppCompatActivity {
    @BindView(R.id.iv_back_sale)
    ImageView mIvBackSale;
    @BindView(R.id.lv_sale)
    ListView mLvSale;
    private List<SaleBean.TopicBean> mTopic;
    private SaleAdapter mSaleAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        ButterKnife.bind(this);
        loadData();
    }

    private void loadData() {
        Call<SaleBean> beanCall = JDRetrofit.getInstance().getApi().listSale(1, 8);
        beanCall.enqueue(new Callback<SaleBean>() {


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
        });
    }
    AbsListView.OnScrollListener scrollListernr= new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (scrollState == SCROLL_STATE_IDLE && mLvSale.getLastVisiblePosition() == getLoadMorePosition()) {
                //如果是最后一个,加载更多
                startLoadMoreData();
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }


    };
    private int getLoadMorePosition() {
        return mSaleAdapter.getCount() - 1 + mLvSale.getHeaderViewsCount();//注意这是+一个头
    }
    private void startLoadMoreData() {
        int count=1;
        Call<SaleBean> beanCall = JDRetrofit.getInstance().getApi().listSale(count++, 8);
        beanCall.enqueue(new Callback<SaleBean>() {


            @Override
            public void onResponse(Call<SaleBean> call, Response<SaleBean> response) {
                mTopic.addAll(response.body().getTopic());
                mSaleAdapter = new SaleAdapter(SalesActivity.this, mTopic);
                mLvSale.setAdapter(new SaleAdapter(SalesActivity.this,mTopic));
                mLvSale.setOnScrollListener(scrollListernr);
            }

            @Override
            public void onFailure(Call<SaleBean> call, Throwable t) {

            }
        });
    }
    @OnClick(R.id.iv_back_sale)
    public void onClick() {
        finish();
    }
}
