package com.shopping.app.jdmall.ui.fragment;


import android.view.View;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.network.JDRetrofit;
import com.shopping.app.jdmall.widget.FindCategoryView;
import com.shopping.app.jdmall.widget.FindHorizotalScrollView;
import com.shopping.app.jdmall.widget.FindListView;
import com.shopping.app.jdmall.widget.FindTitleView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 发现fragment
 */
public class FindFragment extends BaseFragment {


    @Override
    protected void startLoadData() {
        //加载数据
        Call<FindBean> saleDown = JDRetrofit.getInstance().getApi().listFind(1, 10, "saleDown");
        saleDown.enqueue(new Callback<FindBean>() {
            @Override
            public void onResponse(Call<FindBean> call, Response<FindBean> response) {

            }

            @Override
            public void onFailure(Call<FindBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected View onCreateContentView() {
        View view = View.inflate(getContext(), R.layout.fragment_find, null);
        //标题的创建
        FindTitleView titleView = (FindTitleView) view.findViewById(R.id.title_find_fragment);
        FindHorizotalScrollView scrollView = (FindHorizotalScrollView) view.findViewById(R.id.horizotal_find_frament);
        FindCategoryView categoryView = (FindCategoryView) view.findViewById(R.id.category_find_view);
        FindListView listView = (FindListView) view.findViewById(R.id.list_find_fragment);

        //
        return null;
    }

}
