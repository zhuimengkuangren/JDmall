package com.shopping.app.jdmall.ui.activity;

import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.adapter.OrderListAdapter;
import com.shopping.app.jdmall.bean.OrderListsBean;
import com.shopping.app.jdmall.network.JDRetrofit;
import com.shopping.app.jdmall.utils.GsonUtils;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xuanxuan on 2017/4/8.
 */

public class OrderListActivity extends BaseActivity {
    private static final String TAG = "OrderListActivity";
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.all)
    RadioButton all;
    @BindView(R.id.tenmin)
    RadioButton tenmin;
    @BindView(R.id.beyondten)
    RadioButton beyondten;
    @BindView(R.id.cancel)
    RadioButton cancel;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;
    @BindView(R.id.listview)
    ListView listview;
   // private List<OrderListsBean.OrderListBean> mDataList;
    private String[] titles = {"全部订单", "10分钟内订单", "10分钟前订单", "取消的订单"};
    private List<OrderListsBean.OrderListBean> mOrderList ;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_order_list;
    }

    @Override
    protected void init() {
        super.init();

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.all:
                        Toast.makeText(OrderListActivity.this, "点击了1", Toast.LENGTH_SHORT).show();
                        startLoadData(1);
                        break;
                    case R.id.tenmin:
                        Toast.makeText(OrderListActivity.this, "点击了2", Toast.LENGTH_SHORT).show();
                        startLoadData(1);
                        break;
                    case R.id.beyondten:
                        Toast.makeText(OrderListActivity.this, "点击了3", Toast.LENGTH_SHORT).show();
                        startLoadData(2);
                        break;
                    case R.id.cancel:
                        Toast.makeText(OrderListActivity.this, "点击了4", Toast.LENGTH_SHORT).show();
                        startLoadData(3);
                        break;

                }
            }
        });
        OrderListAdapter orderListAdapter = new OrderListAdapter(this,mOrderList);
        orderListAdapter.setData(mOrderList);
        listview.setAdapter(orderListAdapter);

    }

    private void startLoadData(int type) {
        String userid = "20428";
        //请求订单列表数据
        Call<ResponseBody> orderList = JDRetrofit.getInstance().getApi().getOrderList(userid,type, 0, 10);
        orderList.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    Log.d(TAG, "onResponse: " + string);
                   OrderListsBean mOrderListBean = (OrderListsBean) GsonUtils.fromJson(string, OrderListsBean.class);

                    mOrderList = mOrderListBean.getOrderList();
                    Log.d(TAG, "onResponse: " + mOrderListBean.getOrderList().get(0).getStatus());
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

    }


    @OnClick(R.id.icon_back)
    public void onViewClicked() {
        finish();
    }



}
