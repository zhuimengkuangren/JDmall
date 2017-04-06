package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shopping.app.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/4/6.
 */

public class FindDetailInfoView extends RelativeLayout {
    @BindView(R.id.tv_detail_name_find)
    TextView mTvDetailNameFind;
    @BindView(R.id.tv_detail_realprice_find)
    TextView mTvDetailRealpriceFind;
    @BindView(R.id.tv_detail_originprice_find)
    TextView mTvDetailOriginpriceFind;

    public FindDetailInfoView(Context context) {
        this(context, null);
    }

    public FindDetailInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_find_detail_info, this);
        ButterKnife.bind(this, this);
    }
    public void bindView(){

    }
}
