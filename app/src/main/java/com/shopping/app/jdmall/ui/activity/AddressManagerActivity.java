package com.shopping.app.jdmall.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.adapter.BaseListAdapter;
import com.shopping.app.jdmall.bean.AddressBean;
import com.shopping.app.jdmall.network.JDRetrofit;
import com.shopping.app.jdmall.widget.AddressListItemView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xuanxuan on 2017/4/8.
 */

public class AddressManagerActivity extends BaseActivity {
    private static final String TAG = "AddressManagerActivity";

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.add_address)
    Button addAddress;

    private List<AddressBean.AddressListBean> bean;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_address_manager;
    }

    @Override
    protected void init() {
        super.init();
        startLoadData();


    }

    private BaseListAdapter<AddressBean.AddressListBean> mAddressAdapter;

    private void startLoadData() {
        String userid = "20428";
        Call<AddressBean> call = JDRetrofit.getInstance().getApi().getAddressList(userid);
            call.enqueue(new Callback<AddressBean>() {
                @Override
                public void onResponse(Call<AddressBean> call, Response<AddressBean> response) {

                    bean = response.body().getAddressList();

                  Log.d(TAG, "onResponse: " +  response.body().getResponse());
                    //由于异步请求的关系，adapter的初始化必须放在这里，
                    mAddressAdapter = new BaseListAdapter<AddressBean.AddressListBean>(AddressManagerActivity.this,bean) {
                        @Override
                        protected View onCreateView(int position) {
                            return new AddressListItemView(AddressManagerActivity.this);
                        }

                        @Override
                        protected void onBindView(int position, View convertView) {

                            ((AddressListItemView)convertView).bindView(bean.get(position));
                        }
                    };
                    listView.setAdapter(mAddressAdapter);
                }

                @Override
                public void onFailure(Call<AddressBean> call, Throwable t) {

                }
            });
    }

    @OnClick({R.id.add_address,R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.add_address:
                //跳转到新增收货地址界面
                Intent intent = new Intent(this,AddAddressActivity.class);
                break;
        }

    }
}
