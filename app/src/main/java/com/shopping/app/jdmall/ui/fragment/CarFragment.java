package com.shopping.app.jdmall.ui.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.adapter.CarAdapter;
import com.shopping.app.jdmall.bean.CarInfoBean;
import com.shopping.app.jdmall.manager.CarManager;

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
       updateData();
        showData();

    }

    private void updateData() {

        for (int i = 1; i < 3; i++) {

            CarInfoBean carInfoBean1 = new CarInfoBean();
            carInfoBean1.setProdNum(9);
            CarInfoBean.ProductBean productBean = new CarInfoBean.ProductBean();
            productBean.setId(1);
            productBean.setBuyLimit(10);
            productBean.setName("黑丝");
            productBean.setPrice(100);
            productBean.setNumber(String.valueOf(13));

            List<CarInfoBean.ProductBean.ProductPropertyBean> list = new ArrayList<>();
            CarInfoBean.ProductBean.ProductPropertyBean productPropertyBean1 = new CarInfoBean.ProductBean.ProductPropertyBean();
            productPropertyBean1.setId(1);
            productPropertyBean1.setK("颜色");
            productPropertyBean1.setV("红色");

            CarInfoBean.ProductBean.ProductPropertyBean productPropertyBean2 = new CarInfoBean.ProductBean.ProductPropertyBean();
            productPropertyBean2.setId(3);
            productPropertyBean2.setK("尺码");
            productPropertyBean2.setV("M");

            list.add(productPropertyBean1);
            list.add(productPropertyBean2);

            productBean.setProductProperty(list);

            productBean.setPic("/images/product/detail/q1.jpg");
            carInfoBean1.setProduct(productBean);

            CarManager.getInstance().add(carInfoBean1);
        }


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
          /* //重置底部条
            mCheckboxAll.setChecked(false);
            mCarTotal.setText("¥0.0");
            //重置状态
            mTvCarEdit.setTag(ACTION_EDIT);
            mLlDelete.setVisibility(View.GONE);
            mLlCheckAll.setVisibility(View.VISIBLE);*/
        }

    }

    private void showData() {

        mCarInfoBeanList = CarManager.getInstance().getAllData();

        if (mCarInfoBeanList.size() > 0) {
            //有数据,把默认背景隐藏
            mLlEnptyCar.setVisibility(View.GONE);
            mTvCarEdit.setVisibility(View.VISIBLE);
            mLlCheckAll.setVisibility(View.VISIBLE);

            if (mCarAdapter == null) {
                //设置适配器
                mCarAdapter = new CarAdapter(getContext(), mCarInfoBeanList, mCarTotal, mCheckboxAll, mCbAll);
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

    @OnClick({R.id.btn_check_out, R.id.btn_delete, R.id.btn_collection, R.id.tv_empty_car_tobuy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_check_out://去结算
                Toast.makeText(getContext(), "去结算!", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_delete://删除
                //删除选中
                mCarAdapter.deleteData();
                //校验状态
                mCarAdapter.checkAll();
                //数据为空
                if (mCarAdapter.getItemCount() == 0) {
                    mLlEnptyCar.setVisibility(View.VISIBLE);
                    mTvCarEdit.setVisibility(View.GONE);
                    mLlDelete.setVisibility(View.GONE);
                }
                break;
            case R.id.btn_collection://收藏
                break;
            case R.id.tv_empty_car_tobuy:
                Toast.makeText(getContext(), "去逛逛", Toast.LENGTH_SHORT).show();
                break;


        }
    }
}
