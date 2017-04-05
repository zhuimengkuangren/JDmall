package com.shopping.app.jdmall.ui.fragment;


import android.view.View;

import com.shopping.app.jdmall.bean.CategoryItemBean;
import com.shopping.app.jdmall.network.JDRetrofit;
import com.shopping.app.jdmall.widget.CategoryView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 分类fragment
 */
public class CategoryFragment extends BaseFragment {


    private static final String TAG = "CategoryFragment";
    private List<CategoryItemBean> mDataList;
    private CategoryItemBean mBean;



    @Override
    protected void startLoadData() {

        Call<CategoryItemBean> listCategory = JDRetrofit.getInstance().getApi().listCategory();
        listCategory.enqueue(new Callback<CategoryItemBean>() {
            @Override
            public void onResponse(Call<CategoryItemBean> call, Response<CategoryItemBean> response) {
                mBean = response.body();
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<CategoryItemBean> call, Throwable t) {

            }
        });


    }



    @Override
    protected View onCreateContentView() {
        CategoryView categoryView = new CategoryView(getContext());
        categoryView.setData(mBean);
        return categoryView;
    }

}
