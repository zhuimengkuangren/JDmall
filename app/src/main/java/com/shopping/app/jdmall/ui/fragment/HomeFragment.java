package com.shopping.app.jdmall.ui.fragment;


import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;

import com.shopping.app.jdmall.bean.CategoryItemBean;
import com.shopping.app.jdmall.network.JDRetrofit;
import com.shopping.app.jdmall.widget.HomePullToRefreshList;
import com.shopping.app.jdmall.widget.HomeSearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 主页fragment
 */
public class HomeFragment extends BaseFragment {

    private CategoryItemBean mCategoryItemBean;
    private HomeSearchView mHomeSearchView;

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


    @Override
    protected View onCreateContentView() {
        List<List<CategoryItemBean.CategoryBean>> parent = new ArrayList<>();
        List<CategoryItemBean.CategoryBean> child = null;

        List<CategoryItemBean.CategoryBean> beanList = mCategoryItemBean.getCategory();


        for (int i = 0; i < beanList.size(); i++) {
            if (i % 5 == 0) {
                child = new ArrayList<>();
                parent.add(child);
            }
            child.add(beanList.get(i));
        }

        HomePullToRefreshList homePullToRefreshList = new HomePullToRefreshList(getContext(), parent, 0);

        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.addView(homePullToRefreshList);
        mHomeSearchView = new HomeSearchView(getContext());
        frameLayout.addView(mHomeSearchView);

        return frameLayout;
    }


    //搜索栏实现透明动画功能
    public void stratAlphaAnimation(){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
        mHomeSearchView.startAnimation(alphaAnimation);
    }

}
