package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.FindBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/4/8.
 */

public class CargoInfoView extends LinearLayout {
    @BindView(R.id.cargo_buy)
    CargoBuyInfoView mCargoBuy;

    public CargoInfoView(Context context) {
        this(context, null);
    }

    public CargoInfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.view_cargo_info, this);
        ButterKnife.bind(this, this);
    }


    public void setData(FindBean.ProductListBean bean) {
        mCargoBuy.setData(bean);
    }
}
