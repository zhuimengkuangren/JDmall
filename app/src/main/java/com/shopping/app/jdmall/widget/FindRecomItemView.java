package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.FindBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Blacole on 2017/4/6.
 */

public class FindRecomItemView extends LinearLayout {

    private static final String TAG = "FindRecomItemView";
    @BindView(R.id.productName)
    TextView mProductName;
    @BindView(R.id.productPrice)
    TextView mProductPrice;
    @BindView(R.id.productPicture)
    ImageView mProductPicture;

    public FindRecomItemView(Context context) {
        this(context, null);
    }

    public FindRecomItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //Log.d(TAG, "init:++++++++++++++++ ");
        View.inflate(getContext(), R.layout.view_item_find_recommand, this);
        ButterKnife.bind(this,this);
    }

    public void bindView(FindBean.ProductListBean productListBean) {

        //Log.d(TAG, "bindView: +++++++++++"+productListBean.getName()+"+++++++++"+productListBean.getPrice());
            mProductName.setText(productListBean.getName());

            mProductPrice.setText(String.valueOf(productListBean.getPrice()));
        String imageUrl = Constant.HOST + productListBean.getPic();

        //获取屏幕的宽度，设置每一张图的宽高
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels/2;
        int height = displayMetrics.heightPixels / 3;

        Glide.with(getContext()).load(imageUrl).override(width,height).into(mProductPicture);

    }
}
