package com.shopping.app.jdmall.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.adapter.SumbitCargoTypeAdapter;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.bean.LocationBean;
import com.shopping.app.jdmall.network.JDRetrofit;
import com.shopping.app.jdmall.utils.SPUtils;
import com.shopping.app.jdmall.widget.AddressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 龚浩 on 2017/4/8.
 */

public class IdentActivity extends BaseActivity {
    private static final String TAG = "IdentActivity";
    int mChickIdPay = 0;
    int mChickIdSend = 0;
    int mPayType = 1;
    int mSend = 1;
    int mUserCheckId = 1;



    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tv_display_name)
    TextView mTvDisplayName;
    @BindView(R.id.checkbox)
    CheckBox mCheckbox;
    @BindView(R.id.btn_commit)
    Button mBtnCommit;
    @BindView(R.id.info_bottom)
    LinearLayout mInfoBottom;
    @BindView(R.id.list_view)
    ListView mListView;
    @BindView(R.id.address)
    AddressView mAddress;
    @BindView(R.id.head_view)
    LinearLayout mHeadView;
    private SumbitCargoTypeAdapter mAdapter;
    private List<FindBean.ProductListBean> mListData = new ArrayList<>();

    Context mContext = this;
    private ArrayList<Parcelable> mDataList;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_ident;
    }

    @Override
    protected void init() {
        super.init();
        initListView();
        initToolBar();
        initData();

    }

    private void initData() {
        //获取对应userId的地址信息
        initLocation();
        //获取购物车里的信息
        initBuyCargo();

    }

    private void initMoney(ArrayList<Parcelable> dataList) {
        //初始化购物车里面的所有数据



    }

    private void initBuyCargo() {
        Intent intent = getIntent();
        mDataList = intent.getParcelableArrayListExtra("values");
        //获取总的金额数
        initMoney(mDataList);
        mAdapter.setData(mDataList);
    }

    private void initLocation() {
        String userId = SPUtils.getString(this, Constant.LOGIN_USERID, null);
        if (userId == null) {
            navigateTo(LoginPageActivity.class);
        } else {
            //通过网络请求获取网络地址信息
            startLoadLocationData("20428");
        }
    }

    private void startLoadLocationData(String userId) {
        Toast.makeText(this, "fsadfas", Toast.LENGTH_SHORT).show();
        JDRetrofit.getInstance().getApi().listLocation(userId).enqueue(new Callback<LocationBean>() {
            @Override
            public void onResponse(Call<LocationBean> call, Response<LocationBean> response) {
                Log.d(TAG, "onResponse: " + response.body().getResponse());
                List<LocationBean.AddressListBean> list = response.body().getAddressList();
                LocationBean.AddressListBean addressListBean = list.get(1);
                mAddress.setData(addressListBean);
            }

            @Override
            public void onFailure(Call<LocationBean> call, Throwable t) {
                Toast.makeText(mContext, "请求失败了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initListView() {
        mAdapter = new SumbitCargoTypeAdapter(this, mListData);
        mListView.setAdapter(mAdapter);
        mDataList = new ArrayList<>();
    }



    private void initToolBar() {
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        TextView textView = new TextView(this);
        textView.setTextColor(getResources().getColor(R.color.yellow));
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);
        textView.setText("确认订单");
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @OnClick(R.id.btn_commit)
    public void onClick(View v) {
        if(v == mBtnCommit) {
            //跳转订单结果界面
            navigateTo(OrderCommitSuccessActivity.class);
        }

    }

    @Override
    public void navigateTo(Class activity) {
        Intent intent = new Intent(this,activity);
        intent.putExtra("result","fail");
        startActivity(intent);
    }
}
