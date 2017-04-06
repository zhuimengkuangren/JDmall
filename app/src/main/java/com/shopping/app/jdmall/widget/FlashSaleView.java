package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.FlashSaleBean;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/6.
 * FlashSaleAdapter中使用的内容填充模块
 */

public class FlashSaleView extends RelativeLayout {
    @BindView(R.id.commodity_name)
    TextView mCommodityName;
    @BindView(R.id.end_time)
    TextView mEndTime;
    @BindView(R.id.buy_price)
    TextView mBuyPrice;
    @BindView(R.id.panic_buy_btn)
    Button mPanicBuyBtn;
    @BindView(R.id.image_view)
    ImageView mImageView;

    public FlashSaleView(Context context) {
        this(context, null);
    }

    public FlashSaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_flash_sale, this);
    }

    public void bindView(FlashSaleBean flashSaleBean) {
        List<FlashSaleBean.ProductListBean> productList =
                flashSaleBean.getProductList();
        for (int i = 0; i < productList.size(); i++) {
            FlashSaleBean.ProductListBean productListBean = productList.get(i);
            String name = productListBean.getName();
            mCommodityName.setText(name);
            mEndTime.setText(productListBean.getLeftTime());
            mBuyPrice.setText(productListBean.getPrice());
            Glide.with(getContext()).load(Constant.HOST+productListBean.getPic()).into(mImageView);
        }


    }

    @OnClick(R.id.panic_buy_btn)
    public void onClick() {
        Toast.makeText(getContext(),"需要升级付费版",Toast.LENGTH_SHORT).show();
    }
}
