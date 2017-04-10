package com.shopping.app.jdmall.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.CargoBean;
import com.shopping.app.jdmall.network.JDRetrofit;
import com.shopping.app.jdmall.widget.pullToRefreshView;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 龚浩 on 2017/4/6.
 */
public class QueryCargoActivity extends BaseActivity {
    @BindView(R.id.pull_fresh)
    pullToRefreshView mPullFresh;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_composite)
    TextView mTabComposite;
    @BindView(R.id.tab_sale_volume)
    TextView mTabSaleVolume;
    @BindView(R.id.tab_price)
    TextView mTabPrice;
    @BindView(R.id.tab_Query)
    TextView mTabQuery;
    @BindView(R.id.ll_container)
    LinearLayout mLlContainer;

    private int currentPosition = 0;

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
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER);
        //设置显示customview
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(textView, lp);
    }

    //设置toolbar的返回回调
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
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
                Toast.makeText(QueryCargoActivity.this, "抱歉,网络不怎么好!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {

    }


    @OnClick({R.id.tab_composite, R.id.tab_sale_volume, R.id.tab_price, R.id.tab_Query})
    public void onClick(View view) {
        TextView textView = (TextView) mLlContainer.getChildAt(currentPosition);
        textView.setTextColor(Color.BLACK);
        String olderby = "saleDown";
        switch (view.getId()) {
            case R.id.tab_composite:
                mTabComposite.setTextColor(Color.RED);
                currentPosition = 0;
                olderby = "saleDown";
                break;
            case R.id.tab_sale_volume:
                mTabSaleVolume.setTextColor(Color.RED);
                currentPosition = 1;
                olderby = "priceUp";
                break;
            case R.id.tab_price:
                olderby = "priceDown";
                mTabPrice.setTextColor(Color.RED);
                currentPosition = 2;
                break;
            case R.id.tab_Query:
                mTabQuery.setTextColor(Color.RED);
                currentPosition = 3;
                olderby = "shelvesDown";
                break;
        }

        loadData(1, 20, 125, olderby);
    }

    private void loadData(int page, int pageNum, int cid, String olderby) {
        Call<CargoBean> beanCall = JDRetrofit.getInstance().getApi().listProductlist(page, pageNum, cid, olderby);
        beanCall.enqueue(new Callback<CargoBean>() {
            @Override
            public void onResponse(Call<CargoBean> call, Response<CargoBean> response) {
                mBean = response.body();
                mPullFresh.setData(mBean);
            }

            @Override
            public void onFailure(Call<CargoBean> call, Throwable t) {
                Toast.makeText(QueryCargoActivity.this, "抱歉,网络不怎么好!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
