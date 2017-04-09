package com.shopping.app.jdmall.ui.activity;

import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.adapter.OrderListAdapter;
import com.shopping.app.jdmall.bean.OrderBean;
import com.shopping.app.jdmall.network.JDRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
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
   private List<OrderBean.OrderListBean> mDataList;
    private String[] titles = {"全部订单", "10分钟内订单", "10分钟前订单", "取消的订单"};
   // private List<OrderBean.OrderListBean> mOrderList ;


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


    }

    private void startLoadData(int type) {
        String userid = "20428";
        //请求订单列表数据
        Call<OrderBean> orderList = JDRetrofit.getInstance().getApi().getOrderList(userid,type, 0, 10);
        orderList.enqueue(new Callback<OrderBean>() {
            @Override
            public void onResponse(Call<OrderBean> call, Response<OrderBean> response) {
                mDataList = response.body().getOrderList();
                OrderListAdapter orderListAdapter = new OrderListAdapter(OrderListActivity.this,mDataList);
                orderListAdapter.setData(mDataList);
                listview.setAdapter(orderListAdapter);
            }

            @Override
            public void onFailure(Call<OrderBean> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

    }


    @OnClick(R.id.icon_back)
    public void onViewClicked() {
        finish();
    }



}
