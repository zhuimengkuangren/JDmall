package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.FindBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/4/6.
 */

public class DetailInfoView extends RelativeLayout {
    private static final String TAG = "DetailInfoView";
    @BindView(R.id.tv_detail_name_find)
    TextView mName;
    @BindView(R.id.tv_detail_realprice_find)
    TextView mRealprice;
    @BindView(R.id.tv_detail_originprice_find)
    TextView mOriginprice;

    public DetailInfoView(Context context) {
        this(context, null);
    }

    public DetailInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_find_detail_info, this);
        ButterKnife.bind(this, this);
    }
    public void bindView(FindBean.ProductListBean mBean){
        mName.setText(mBean.getName());
        mOriginprice.setText("￥ "+ mBean.getMarketPrice());
        mRealprice.setText(mBean.getPrice()+"");
    }
}
