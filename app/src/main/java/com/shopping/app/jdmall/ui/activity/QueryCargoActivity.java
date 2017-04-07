package com.shopping.app.jdmall.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.RadioGroup;
import android.widget.TextView;
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
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    private List<CargoBean> mList = new ArrayList<>();
    private CargoBean mBean;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_query_cargo;
    }

    @Override
    protected void init() {
        super.init();
        initToolBar();
        startLoadData();
        mTabContainer.setOnCheckedChangeListener(mListener);
        mTabContainer.check(R.id.tab_composite);
    }

    private void initToolBar() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("tag");
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        TextView textView = new TextView(this);
        textView.setTextColor(Color.RED);
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);
        textView.setText(title);
        //设置显示返回按钮
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        //定义actionbar的位置
        ActionBar.LayoutParams lp =new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER);
        //设置显示customview
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(textView,lp);
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
