package com.shopping.app.jdmall.ui.fragment;


import android.view.View;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.network.JDRetrofit;
import com.shopping.app.jdmall.widget.CategoryFindFragmentView;
import com.shopping.app.jdmall.widget.HorizotalScrollFindFragmentView;
import com.shopping.app.jdmall.widget.ListFindFragmentView;
import com.shopping.app.jdmall.widget.TitleFindFragmentView;

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
        TitleFindFragmentView titleView = (TitleFindFragmentView) view.findViewById(R.id.title_find_fragment);
        HorizotalScrollFindFragmentView scrollView = (HorizotalScrollFindFragmentView) view.findViewById(R.id.horizotal_find_frament);
        CategoryFindFragmentView categoryView = (CategoryFindFragmentView) view.findViewById(R.id.category_find_view);
        ListFindFragmentView listView = (ListFindFragmentView) view.findViewById(R.id.list_find_fragment);

        //
        return null;
    }

}
