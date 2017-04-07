package com.shopping.app.jdmall.ui.fragment;


import android.util.Log;
import android.view.View;

import com.shopping.app.jdmall.bean.BannerBean;
import com.shopping.app.jdmall.bean.CategoryItemBean;
import com.shopping.app.jdmall.network.JDRetrofit;
import com.shopping.app.jdmall.widget.HomePullToRefreshList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 主页fragment
 */
public class HomeFragment extends BaseFragment {


    private BannerBean mBannerBean;
    private CategoryItemBean mCategoryItemBean;

    @Override
    protected void startLoadData() {
        Call<CategoryItemBean> categoryItemBeanCall = JDRetrofit.getInstance().getApi().listCategory();
        categoryItemBeanCall.enqueue(new Callback<CategoryItemBean>() {
            @Override
            public void onResponse(Call<CategoryItemBean> call, Response<CategoryItemBean> response) {
                mCategoryItemBean = response.body();
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<CategoryItemBean> call, Throwable t) {
                onDataLoadedFailed();
            }
        });

    }

    private static final String TAG = "HomeFragment";
    @Override
    protected View onCreateContentView() {
        List<List<CategoryItemBean.CategoryBean>> parent = new ArrayList<>();
        List<CategoryItemBean.CategoryBean> child = null;

        List<CategoryItemBean.CategoryBean> beanList = mCategoryItemBean.getCategory();


        for (int i = 0; i < beanList.size(); i++) {
            if(i % 5 == 0){
                child = new ArrayList<>();
                parent.add(child);
            }
            child.add(beanList.get(i));
        }
        Log.d(TAG, "onCreateContentView: " + parent.size() + "=====" +child.size());

        HomePullToRefreshList homePullToRefreshList = new HomePullToRefreshList(getContext(),parent,0);
        return homePullToRefreshList;
    }

}
