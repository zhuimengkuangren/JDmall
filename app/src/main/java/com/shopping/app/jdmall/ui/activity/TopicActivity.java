package com.shopping.app.jdmall.ui.activity;

import android.widget.BaseAdapter;

import com.shopping.app.jdmall.adapter.TopicAdapter;
import com.shopping.app.jdmall.bean.TopicRenBean;
import com.shopping.app.jdmall.network.JDRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/4/6.
 * 促销快报
 */

public class TopicActivity extends BaseListLoadMoreActivity {

    private List<TopicRenBean.TopicBean> mDataList;
    private TopicRenBean mTopicRenBean;

    @Override
    protected BaseAdapter oncreateAdapter() {
        return new TopicAdapter(TopicActivity.this, mDataList);
    }

    @Override
    protected String getTitleFromSon() {
        return "促销快报";
    }

    @Override
    protected void startLoadData() {
        Call<TopicRenBean> topicRenBeanCall = JDRetrofit.getInstance().getApi().listTopicRen(0, 10);
        topicRenBeanCall.enqueue(new Callback<TopicRenBean>() {
            @Override
            public void onResponse(Call<TopicRenBean> call, Response<TopicRenBean> response) {
                mTopicRenBean = response.body();
                mDataList = mTopicRenBean.getTopic();
                //getSupportActionBar().setTitle(mTopicRenBean.getResponse());
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<TopicRenBean> call, Throwable t) {
                onDataLoadedFailed();
            }
        });
    }

    @Override
    protected void startLoadMoreData() {
        Call<TopicRenBean> topicRenBeanCall = JDRetrofit.getInstance().getApi().listTopicRen(mDataList.size(), mTopicRenBean.getListCount());
        topicRenBeanCall.enqueue(new Callback<TopicRenBean>() {
            @Override
            public void onResponse(Call<TopicRenBean> call, Response<TopicRenBean> response) {
                mDataList.addAll(response.body().getTopic());
                getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TopicRenBean> call, Throwable t) {
                onDataLoadedFailed();
            }
        });
    }
}
