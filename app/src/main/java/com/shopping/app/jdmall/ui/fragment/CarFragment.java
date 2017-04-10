package com.shopping.app.jdmall.ui.fragment;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.adapter.CarAdapter;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.CarInfoBean;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.event.FragmentEvent;
import com.shopping.app.jdmall.manager.CarManager;
import com.shopping.app.jdmall.ui.activity.BaseActivity;
import com.shopping.app.jdmall.ui.activity.IdentActivity;
import com.shopping.app.jdmall.ui.activity.LoginPageActivity;
import com.shopping.app.jdmall.ui.activity.MainActivity;
import com.shopping.app.jdmall.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.shopping.app.jdmall.R.id.ll_check_all;

/**
 * 购物车fragment
 */
public class CarFragment extends BaseNotLoadDataFragment {

    private static final String TAG = "CarFragment";
    @BindView(R.id.tv_car_edit)
    TextView mTvCarEdit;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.checkbox_all)
    CheckBox mCheckboxAll;
    @BindView(R.id.car_total)
    TextView mCarTotal;
    @BindView(R.id.btn_check_out)
    Button mBtnCheckOut;
    @BindView(ll_check_all)
    LinearLayout mLlCheckAll;
    @BindView(R.id.cb_all)
    CheckBox mCbAll;
    @BindView(R.id.btn_delete)
    Button mBtnDelete;
    @BindView(R.id.btn_collection)
    Button mBtnCollection;
    @BindView(R.id.ll_delete)
    LinearLayout mLlDelete;
    @BindView(R.id.iv_empty)
    ImageView mIvEmpty;
    @BindView(R.id.tv_empty_car_tobuy)
    TextView mTvEmptyCarTobuy;
    @BindView(R.id.ll_enpty_car)
    LinearLayout mLlEnptyCar;
    private CarAdapter mCarAdapter;
    private List<CarInfoBean> mCarInfoBeanList;

    //定义两个状态
    private static final int ACTION_EDIT = 0;//编辑状态

    private static final int ACTION_COMPLETED = 1;//完成状态

    @Override
    public int getResId() {
        return R.layout.fragment_car;
    }

    @Override
    public void init() {
        initData();
        initListener();
        initAnimation();
    }

    private void initAnimation() {

        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);

        animation.setDuration(2000);

        animation.setRepeatCount(Animation.INFINITE);

        animation.setRepeatMode(Animation.REVERSE);

        mIvEmpty.startAnimation(animation);

    }

    private void initListener() {
        //设置默认的编辑状态
        mTvCarEdit.setTag(ACTION_EDIT);
        mTvCarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int action = (int) v.getTag();
                if (action == ACTION_EDIT) {
                    //切换完成状态
                    showEdit();
                } else {
                    //切换成编辑状态
                    hideEdit();
                }
            }


        });

    }

    private void showEdit() {
        //设置文本 - 完成
        mTvCarEdit.setTag(ACTION_COMPLETED);
        mTvCarEdit.setText("完成");
        //非勾选
        mCheckboxAll.setChecked(false);
        //mCbAll.setChecked(false);

        if (mCarAdapter != null) {
            mCarAdapter.checkAllOrNone(false);
            mCarAdapter.checkAll();
        }
        //删除视图显示
        mLlDelete.setVisibility(View.VISIBLE);
        //结算视图隐藏
        mLlCheckAll.setVisibility(View.GONE);
    }

    private void hideEdit() {
        mTvCarEdit.setTag(ACTION_EDIT);
        mTvCarEdit.setText("编辑");

        mCheckboxAll.setChecked(true);
        if (mCarAdapter != null) {
            mCarAdapter.checkAllOrNone(true);
            mCarAdapter.checkAll();
            mCarAdapter.showTotalPrice();
        }
        mLlDelete.setVisibility(View.GONE);

        mLlCheckAll.setVisibility(View.VISIBLE);


    }

    private void initData() {
        //模拟点击购物车
        //updateData();
        showData();

    }


    /**
     * 界面状态回调
     *
     * @param hidden
     */
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            showData();//show之后显示数据
        } else if (mCarInfoBeanList != null) {
            for (int i = 0; i < mCarInfoBeanList.size(); i++) {
                CarInfoBean carInfoBean = mCarInfoBeanList.get(i);
                CarManager.getInstance().update(carInfoBean);//hide之后保存数据
            }
        }

    }

    private void showData() {

        mCarInfoBeanList = CarManager.getInstance().getAllData();

        if (mCarInfoBeanList.size() > 0) {
            //转换集合
            List<FindBean.ProductListBean> productList = CarToProduct(mCarInfoBeanList);
            Log.d(TAG, "productList: " + productList.size());

            //有数据,把默认背景隐藏
            mLlEnptyCar.setVisibility(View.GONE);
            mTvCarEdit.setVisibility(View.VISIBLE);
            mLlCheckAll.setVisibility(View.VISIBLE);

            if (mCarAdapter == null) {
                //设置适配器
                mCarAdapter = new CarAdapter(getContext(), mCarInfoBeanList,productList,mCarTotal, mCheckboxAll, mCbAll,mBtnCollection);
                mRecyclerView.getItemAnimator().setChangeDuration(0);//去掉闪屏
                mRecyclerView.setAdapter(mCarAdapter);
            } else {
                mCarAdapter.updateData(mCarInfoBeanList);
            }
            //设置布局管理器
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        } else {
            //没数据,把默认背景显示
            mLlEnptyCar.setVisibility(View.VISIBLE);
            mTvCarEdit.setVisibility(View.GONE);
            mLlDelete.setVisibility(View.GONE);
        }

    }

    private ArrayList<FindBean.ProductListBean> CarToProduct(List<CarInfoBean> carInfoBeanList) {
        ArrayList<FindBean.ProductListBean> productListBeen =  new ArrayList<>();
        for (int i = 0; i <carInfoBeanList.size() ; i++) {
            CarInfoBean carInfoBean = carInfoBeanList.get(i);
            /*if(!carInfoBean.isCheck()){
                continue;
            }*/
            CarInfoBean.ProductBean product = carInfoBean.getProduct();

            int prodNum = carInfoBean.getProdNum();
            int id = product.getId();
            String name = product.getName();
            String number = product.getNumber();
            String pic = product.getPic();
            int price = product.getPrice();

            //填充数据
            FindBean.ProductListBean productListBean = new FindBean.ProductListBean();
            productListBean.setId(id);
            productListBean.setMarketPrice(price + 100);//市场价
            productListBean.setPrice(price);
            productListBean.setName(name);
            productListBean.setPic(pic);
            productListBean.setNumbers(Integer.parseInt(number));
            productListBean.setBuyCounts(prodNum);


            productListBeen.add(productListBean);

        }

        return productListBeen;

    }


    @OnClick({R.id.btn_check_out, R.id.btn_delete, R.id.btn_collection, R.id.tv_empty_car_tobuy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_check_out://去结算
                Toast.makeText(getContext(), "去结算!", Toast.LENGTH_SHORT).show();
                ArrayList<FindBean.ProductListBean> productListBeen = null;
                //转换集合
                if(mCarInfoBeanList != null && mCarInfoBeanList.size() >0){
                    productListBeen = CarToProduct(mCarInfoBeanList);
                }
                //验证登录状态
                String userId = SPUtils.getString(getContext(), Constant.LOGIN_USERID, null);
                if (userId == null) {
                    ((BaseActivity)getActivity()).navigateTo(LoginPageActivity.class);
                } else {
                    //只传递勾选的商品
                    productListBeen = CarToSelectedProduct(mCarInfoBeanList);

                    Intent intent = new Intent(getContext(),IdentActivity.class);
                    intent.putParcelableArrayListExtra("values",productListBeen);
                    getContext().startActivity(intent);
                }

                break;
            case R.id.btn_delete://删除
                //删除选中
                mCarAdapter.deleteData();
                //校验状态
                mCarAdapter.checkAll();
                //数据为空
                if (mCarAdapter.getItemCount() == 0) {
                    mCarTotal.setText("¥0.0");//还原合计
                    mLlEnptyCar.setVisibility(View.VISIBLE);
                    mTvCarEdit.setVisibility(View.GONE);
                    mLlDelete.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_empty_car_tobuy:
                Toast.makeText(getContext(), "亲,来逛逛呗~", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new FragmentEvent(MainActivity.findTagId, MainActivity.findFragmentTag));
                break;


        }
    }

    private ArrayList<FindBean.ProductListBean> CarToSelectedProduct(List<CarInfoBean> carInfoBeanList) {
        ArrayList<FindBean.ProductListBean> productListBeen =  new ArrayList<>();
        for (int i = 0; i <carInfoBeanList.size() ; i++) {
            CarInfoBean carInfoBean = carInfoBeanList.get(i);
            //忽略未勾选的商品
            if(!carInfoBean.isCheck()){
                continue;
            }
            CarInfoBean.ProductBean product = carInfoBean.getProduct();

            int prodNum = carInfoBean.getProdNum();
            int id = product.getId();
            String name = product.getName();
            String number = product.getNumber();
            String pic = product.getPic();
            int price = product.getPrice();

            //填充数据
            FindBean.ProductListBean productListBean = new FindBean.ProductListBean();
            productListBean.setId(id);
            productListBean.setMarketPrice(price + 100);//市场价
            productListBean.setPrice(price);
            productListBean.setName(name);
            productListBean.setPic(pic);
            productListBean.setNumbers(Integer.parseInt(number));
            productListBean.setBuyCounts(prodNum);


            productListBeen.add(productListBean);

        }
        return productListBeen;
    }
}
