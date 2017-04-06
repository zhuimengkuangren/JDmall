package com.shopping.app.jdmall.ui.activity;

import android.widget.RadioGroup;
import android.widget.Toast;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.CargoBean;
import com.shopping.app.jdmall.network.JDRetrofit;
import com.shopping.app.jdmall.widget.pullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 龚浩 on 2017/4/6.
 */
public class QueryCargoActivity extends BaseActivity {
    @BindView(R.id.tab_container)
    RadioGroup mTabContainer;
    @BindView(R.id.pull_fresh)
    pullToRefreshView mPullFresh;

    private List<CargoBean> mList = new ArrayList<>();
    private CargoBean mBean;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_query_cargo;
    }

    @Override
    protected void init() {
        super.init();
        startLoadData();
        mTabContainer.setOnCheckedChangeListener(mListener);
    }

    private void startLoadData() {
        JDRetrofit.getInstance().getApi().listProductlist(1, 10, 125, "saleDown").enqueue(new Callback<CargoBean>() {
            @Override
            public void onResponse(Call<CargoBean> call, Response<CargoBean> response) {
                mBean = response.body();
                mPullFresh.setData(mBean);
                initData();
            }

            @Override
            public void onFailure(Call<CargoBean> call, Throwable t) {
                Toast.makeText(QueryCargoActivity.this, "请求失败了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {

    }

    private RadioGroup.OnCheckedChangeListener mListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (mBean != null) {
                mPullFresh.setData(mBean);
            }
        }
    };
}
