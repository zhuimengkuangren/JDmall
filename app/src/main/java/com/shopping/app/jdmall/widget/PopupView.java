package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.BuyCarBean;
import com.shopping.app.jdmall.bean.CarInfoBean;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.manager.CarManager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2017/4/7.
 */

public class PopupView extends RelativeLayout implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "PopupView";
    @BindView(R.id.icon_view_popup)
    ImageView mIcon;
    @BindView(R.id.price_buycar)
    TextView mPrice;
    @BindView(R.id.number_buycar)
    TextView mNumber;
    @BindView(R.id.iv_icon_buycar)
    ImageView mIvIcon;
    @BindView(R.id.tv_buy_numbers)
    TextView mBuyNumbers;
    @BindView(R.id.decrease_buy)
    TextView mDecreaseBuy;
    @BindView(R.id.actual_numbers)
    TextView mActualNumbers;
    @BindView(R.id.increase_buy)
    TextView mIncreaseBuy;
    @BindView(R.id.ensure_buy)
    TextView mEnsureBuy;
    @BindView(R.id.name_view_popup)
    TextView mName;
    @BindView(R.id.rg_size)
    RadioGroup mRgSize;
    @BindView(R.id.rg_color)
    RadioGroup mRgColor;
    @BindView(R.id.select_size_color)
    TextView mSelectSizeColor;
    private BuyCarBean.ProductBean mProduct;
    String size="";
    String color="";
    private String mUrl;
    private FindBean.ProductListBean mBean;
    private CarInfoBean.ProductBean mProductBean;
    private List<CarInfoBean.ProductBean.ProductPropertyBean> mProductProperty;
    private int mId;

    public PopupView(Context context) {
        this(context, null);
    }

    public PopupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        mRgSize.setOnCheckedChangeListener(this);
        mRgColor.setOnCheckedChangeListener(this);
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_popup, this);
        ButterKnife.bind(this, this);
    }



    @OnClick({R.id.decrease_buy, R.id.increase_buy, R.id.ensure_buy,R.id.iv_icon_buycar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.decrease_buy:
                String s = mActualNumbers.getText().toString();
                int count = Integer.parseInt(s);
                count--;
                if (count <= 1) {
                    count = 1;
                }
                mActualNumbers.setText(count + "");
                break;
            case R.id.increase_buy:
                String s1 = mActualNumbers.getText().toString();
                int count1 = Integer.parseInt(s1);
                count1++;
                mActualNumbers.setText(count1 + "");
                break;
            case R.id.ensure_buy:
                addBuyCart();
                break;
            case R.id.iv_icon_buycar:
                EventBus.getDefault().post("clickclose");
                break;
        }
    }

    private void addBuyCart() {
        String s = mActualNumbers.getText().toString();
        int i = Integer.parseInt(s);
        //TODO  拿到数量
        if (mRgSize.getCheckedRadioButtonId() != -1 && mRgColor.getCheckedRadioButtonId() != -1) {
            //已经选择
            mBean.setBuyCounts(i);
            if (mId==R.id.tv_buy_car){
                //点击的是购物车
                findBeanToCarInfoBean();
                Toast.makeText(getContext(), "您已成功添加到购物车", Toast.LENGTH_SHORT).show();
            }else if(mId==R.id.buy_now){
                //TODO 跳转到立即购买界面
                Toast.makeText(getContext(), "跳转到立即购买界面", Toast.LENGTH_SHORT).show();
                ArrayList<FindBean.ProductListBean> productBeenList = new ArrayList<>();
                for (int j = 0; j < i; j++) {
                    productBeenList.add(mBean);
                }
                //将集合传递过去即可
            }
            EventBus.getDefault().post("animationcompleted");
        } else {
            Toast.makeText(getContext(), "请选择颜色和尺寸", Toast.LENGTH_SHORT).show();
        }
    }
    //数据存放到购物车
    private void findBeanToCarInfoBean() {
        CarInfoBean carInfoBean = new CarInfoBean();
        mProductBean.setName(mBean.getName());
        mProductBean.setBuyLimit(10);
        mProductBean.setPic(mBean.getPic());
        mProductBean.setId(mBean.getId());
        mProductBean.setPrice(mBean.getPrice());
        mProductBean.setNumber(mBean.getBuyCounts()+"");
        mProductBean.setProductProperty(mProductProperty);
        Random random = new Random();
        int i = random.nextInt(90);
        mProductBean.setNumber(i+"");
        carInfoBean.setProduct(mProductBean);
        CarManager.getInstance().add(carInfoBean);
    }


    public void bindView(FindBean.ProductListBean bean,int id) {
        mBean=bean;
        mId=id;
        mPrice.setText("￥" + bean.getPrice());
        mUrl = Constant.HOST + bean.getPic();
        Log.d(TAG, "bindView: "+ mUrl);
        Glide.with(getContext()).load(mUrl).into(mIcon);
        mName.setText(bean.getName());
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        if (group == mRgSize) {
            switch (checkedId) {
                case R.id.m_size:
                    size="M";
                    break;
                case R.id.xxl_size:
                    size="XXL";
                    break;
                case R.id.xxxl_size:
                    size="XXXL";
                    break;
            }
        } else if (group == mRgColor) {
            switch (checkedId) {
                case R.id.red_color:
                    color="红色";
                    break;
                case R.id.green_color:
                    color="绿色";
                    break;

            }
        }
        if (mRgSize.getCheckedRadioButtonId() != -1 && mRgColor.getCheckedRadioButtonId() != -1) {
            //已经选择
            mName.setVisibility(GONE);
            mSelectSizeColor.setText("已选："+size+" "+color);
            mProductBean = new CarInfoBean.ProductBean();
            mProductProperty = mProductBean.getProductProperty();//为什么为null
            CarInfoBean.ProductBean.ProductPropertyBean productPropertyBean = new CarInfoBean.ProductBean.ProductPropertyBean();
            productPropertyBean.setK("颜色");
            productPropertyBean.setV(color);

            mProductProperty.add(productPropertyBean);

            CarInfoBean.ProductBean.ProductPropertyBean productPropertyBean2 = new CarInfoBean.ProductBean.ProductPropertyBean();
            productPropertyBean2.setK("尺码");
            productPropertyBean2.setV(size);
            mProductProperty.add(productPropertyBean2);
        }
    }
}
