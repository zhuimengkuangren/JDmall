package com.shopping.app.jdmall.ui.fragment;


import android.util.Log;
import android.view.View;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.network.JDRetrofit;
import com.shopping.app.jdmall.widget.FindCategoryView;
import com.shopping.app.jdmall.widget.FindHorizotalScrollView;
import com.shopping.app.jdmall.widget.FindListView;
import com.shopping.app.jdmall.widget.FindTitleView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 发现fragment
 */
public class FindFragment extends BaseFragment {

    private static final String TAG = "FindFragment";
    private List<FindBean.ProductListBean> mProductList;

    @Override
    protected void startLoadData() {
        //加载数据
        Log.d(TAG, "startLoadData: 11");
        Call<FindBean> saleDown = JDRetrofit.getInstance().getApi().listFind(1, 10, "saleDown");
        saleDown.enqueue(new Callback<FindBean>() {
            @Override
            public void onResponse(Call<FindBean> call, Response<FindBean> response) {
                Log.d(TAG, "startLoadData: 22"+response.body().getProductList().get(0).getPic());
                mProductList = response.body().getProductList();//已经拿到数据了,子线程不能弹土司
                //Toast.makeText(getContext(), "数据加载成功", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "startLoadData: 2332"+response.body().getProductList().get(0).getPic());
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<FindBean> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
                onDataLoadedFailed();
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
        listView.bindView(mProductList);

        return view;
    }

}
