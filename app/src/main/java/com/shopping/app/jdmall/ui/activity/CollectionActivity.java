package com.shopping.app.jdmall.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.adapter.CollectAdapter;
import com.shopping.app.jdmall.bean.CollectionBean;
import com.shopping.app.jdmall.network.JDRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xuanxuan on 2017/4/8.
 */

public class CollectionActivity extends BaseActivity {
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.list_view_collection)
    ListView listView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_collection;
    }

    @Override
    protected void init() {
        super.init();
        startLoadData();
    }

    private void startLoadData() {
        Call<CollectionBean> call = JDRetrofit.getInstance().getApi().getFavorList(1, 10);
        call.enqueue(new Callback<CollectionBean>() {
            @Override
            public void onResponse(Call<CollectionBean> call, Response<CollectionBean> response) {
                List<CollectionBean.ProductListBean> productList = response.body().getProductList();
                listView.setAdapter(new CollectAdapter(CollectionActivity.this,productList));
            }

            @Override
            public void onFailure(Call<CollectionBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.icon_back)
    public void onViewClicked() {
    }
}
