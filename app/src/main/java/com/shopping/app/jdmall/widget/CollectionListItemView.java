package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.CollectionBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuanxuan on 2017/4/9.
 */

public class CollectionListItemView extends RelativeLayout {
    @BindView(R.id.product_image)
    ImageView productImage;
    @BindView(R.id.product_name)
    TextView productName;
    @BindView(R.id.product_price)
    TextView productPrice;

    public CollectionListItemView(Context context) {
        this(context, null);
    }

    public CollectionListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_collection_list_item, this);
        ButterKnife.bind(this,this);
    }

    public void bindView(CollectionBean.ProductListBean productListBean) {
        productName.setText("¥" + productListBean.getPrice());
        productName.setText(productListBean.getName());
        Glide.with(getContext()).load(productListBean.getPic()).centerCrop().into(productImage);

    }
}
