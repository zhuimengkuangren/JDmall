package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.ui.activity.DetailListItemActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2017/4/5.
 */

public class FindListItemView extends RelativeLayout {
    private static final String TAG = "FindListItemView";
    @BindView(R.id.tv_name_findfragment)
    TextView mName;
    @BindView(R.id.tv_market_price_findfragment)
    TextView mMarketPrice;
    @BindView(R.id.tv_actual_price_findfragment)
    TextView mActualPrice;
    @BindView(R.id.btn_item_list_find)
    Button mBtnItem;
    @BindView(R.id.iv_item_findfragmen)
    ImageView mIcon;
    private FindBean.ProductListBean bean;

    public FindListItemView(Context context) {
        this(context, null);
    }

    public FindListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_item_list_find, this);
        ButterKnife.bind(this, this);
    }

    @OnClick({R.id.btn_item_list_find, R.id.iv_item_findfragmen})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_item_list_find:
                Intent intent = new Intent(getContext(), DetailListItemActivity.class);
                /*CharSequence[] values={bean.getName(),bean.getPic(),bean.getMarketPrice()+"",bean.getPrice()+""};
                intent.putExtra("values",values);*/
                intent.putExtra("values",bean);
                getContext().startActivity(intent);
                break;
            case R.id.iv_item_findfragmen:
                break;
        }
    }

    public void bindView(FindBean.ProductListBean productListBean) {
        bean=productListBean;
        mName.setText(productListBean.getName());
        mMarketPrice.setText("市场价￥ "+productListBean.getMarketPrice()+"");
        mActualPrice.setText("现价 "+productListBean.getPrice()+"");
        String url= Constant.HOST+productListBean.getPic();
        mIcon.measure(0,0);
        Glide.with(getContext()).load(url).override(200,350).into(mIcon);
    }

}
