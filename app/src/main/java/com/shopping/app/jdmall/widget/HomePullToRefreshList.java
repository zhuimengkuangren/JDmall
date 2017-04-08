package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.Toast;

import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.shopping.app.jdmall.adapter.HomeAdapter;
import com.shopping.app.jdmall.bean.BannerBean;
import com.shopping.app.jdmall.network.JDRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by panpan on 2017/4/7.
 */

public class HomePullToRefreshList extends PullToRefreshListView {

    private List mList;
    private BannerBean mBannerBean;
    private HomeHeadBanner mHomeHeadBanner;

    public HomePullToRefreshList(Context context) {
        this(context, null);
    }

    public HomePullToRefreshList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomePullToRefreshList(Context context, List list, int flag) {
        super(context);
        mList = list;
        loadDatas();
    }

    private void loadDatas() {
        Call<BannerBean> listHome = JDRetrofit.getInstance().getApi().listHome();
        listHome.enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                mBannerBean = response.body();
                getRefreshableView().removeHeaderView(mHomeHeadBanner);
                init();
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {

            }
        });
    }

    private void init() {
        setAdapter(new HomeAdapter(getContext(), mList, mBannerBean));
        setMode(Mode.BOTH); //设置技能下拉刷新,又能上拉加载更多
        setOnRefreshListener(mOnRefreshListener2);
        mHomeHeadBanner = new HomeHeadBanner(getContext());
        mHomeHeadBanner.bindView(mBannerBean);
        getRefreshableView().addHeaderView(mHomeHeadBanner);
        setBackground(new ColorDrawable(Color.WHITE));
    }


    private OnRefreshListener2 mOnRefreshListener2 = new OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase pullToRefreshBase) {

            loadDatas();
            Toast.makeText(getContext(), "数据刷新成功", Toast.LENGTH_SHORT).show();
            post(new Runnable() {
                @Override
                public void run() {
                    onRefreshComplete();
                }
            });
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase pullToRefreshBase) {
            Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
            post(new Runnable() {
                @Override
                public void run() {
                    onRefreshComplete();
                }
            });
        }
    };
}
