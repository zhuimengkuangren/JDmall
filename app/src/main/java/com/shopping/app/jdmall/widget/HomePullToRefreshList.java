package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.shopping.app.jdmall.adapter.HomeAdapter;
import com.shopping.app.jdmall.bean.BannerBean;

import java.util.List;

/**
 * Created by panpan on 2017/4/7.
 */

public class HomePullToRefreshList extends PullToRefreshListView {

    private List mList;
    private BannerBean mBean;

    public HomePullToRefreshList(Context context) {
        this(context,null);
    }

    public HomePullToRefreshList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomePullToRefreshList(Context context,List list,BannerBean bean){
        super(context);
        mList = list;
        mBean = bean;
        init();
    }

    private void init() {
        setAdapter(new HomeAdapter(getContext(),mList,mBean));
        setMode(Mode.BOTH); //设置技能下拉刷新,又能上拉加载更多
        setOnRefreshListener(mOnRefreshListener2);
    }


    private OnRefreshListener2 mOnRefreshListener2 = new OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase pullToRefreshBase) {

            post(new Runnable() {
                @Override
                public void run() {
                    onRefreshComplete();
                }
            });
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase pullToRefreshBase) {
            post(new Runnable() {
                @Override
                public void run() {
                    onRefreshComplete();
                }
            });

        }
    };

  /*  //加载初始化需要的数据
    public void setDatas(List arr, BannerBean bannerBean) {
        mArr = arr;
        mBannerBean = bannerBean;
    }*/
}
