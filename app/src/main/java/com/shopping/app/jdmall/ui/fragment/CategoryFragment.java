package com.shopping.app.jdmall.ui.fragment;


import android.view.View;
<<<<<<< HEAD
=======

import com.shopping.app.jdmall.bean.CategoryItemBean;
import com.shopping.app.jdmall.network.JDRetrofit;
import com.shopping.app.jdmall.widget.CategoryView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
>>>>>>> feature/gongh


/**
 * 分类fragment
 */
public class CategoryFragment extends BaseFragment {


    private static final String TAG = "CategoryFragment";
    private List<CategoryItemBean> mDataList;
    private CategoryItemBean mBean;

    @Override
    protected void init() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected void startLoadData() {
<<<<<<< HEAD
=======
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

>>>>>>> feature/gongh
    }



    @Override
    protected View onCreateContentView() {
<<<<<<< HEAD

        return null;
=======
        CategoryView categoryView = new CategoryView(getContext());
        categoryView.setData(mBean);
        return categoryView;
>>>>>>> feature/gongh
    }

}
