package com.shopping.app.jdmall.ui.fragment;

import android.content.Intent;
import android.util.Log;
import android.widget.BaseAdapter;

import com.shopping.app.jdmall.adapter.FindItemAdapter;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.network.JDRetrofit;
import com.shopping.app.jdmall.ui.activity.DetailListItemActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 2017/4/6.
 */

public class FindListFragment extends BaseListLoadMoreFragment {
    private static final String TAG = "FindListFragment";
    private List<FindBean.ProductListBean> mProductList;

    @Override
    protected void startLoadData() {
        //加载数据
        Call<FindBean> saleDown = JDRetrofit.getInstance().getApi().listFind(1, 10, "saleDown");
        saleDown.enqueue(new Callback<FindBean>() {
            @Override
            public void onResponse(Call<FindBean> call, Response<FindBean> response) {
                mProductList = response.body().getProductList();//已经拿到数据了,子线程不能弹土司
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
    protected BaseAdapter oncreateAdapter() {
        FindItemAdapter itemAdapter = new FindItemAdapter(getContext(), mProductList);
        return itemAdapter;
    }

    @Override
    protected void startLoadMoreData() {
        //加载数据
        int pages=mProductList.size()/10+1;
        Call<FindBean> saleDown = JDRetrofit.getInstance().getApi().listFind(pages, 10, "saleDown");
        saleDown.enqueue(new Callback<FindBean>() {
            @Override
            public void onResponse(Call<FindBean> call, Response<FindBean> response) {
                mProductList.addAll(response.body().getProductList());//已经拿到数据了,子线程不能弹土司
                 getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FindBean> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
                onDataLoadedFailed();
            }
        });
    }

    @Override
    protected void onListItemClick(int position) {
        Intent intent = new Intent(getContext(), DetailListItemActivity.class);
        startActivity(intent);
    }
}
