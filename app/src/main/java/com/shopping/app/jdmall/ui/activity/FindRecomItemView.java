package com.shopping.app.jdmall.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
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

    public void bindView(FindBean.ProductListBean productListBean, String type) {

        //Log.d(TAG, "bindView: +++++++++++"+productListBean.getName()+"+++++++++"+productListBean.getPrice());

            switch (type){

                case "热门商品":

                    mProductName.setText(productListBean.getName());
                    mProductPrice.setTextColor(getResources().getColor(R.color.colorRed));
                    mProductPrice.setText("失眠的价钱¥"+String.valueOf(productListBean.getPrice()));

                    break;

                case "新品上架":

                    //更换产品名字左边标签
                    Drawable drawable = getContext().getResources().getDrawable(R.mipmap.b87);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    mProductName.setCompoundDrawables(drawable, null, null, null);
                    mProductName.setText(productListBean.getName());

                    String str = "现价¥"+productListBean.getMarketPrice() + "市场价"+productListBean.getPrice();
                    //设置部分字体颜色
                    SpannableStringBuilder builder = new SpannableStringBuilder(str);

                    //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
                    ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);

                    builder.setSpan(redSpan, 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    builder.setSpan((new AbsoluteSizeSpan(18)), 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mProductPrice.setText(builder);
                    break;
            }


        String imageUrl = Constant.HOST + productListBean.getPic();

        //获取屏幕的宽度，设置每一张图的宽高
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels/2;
        int height = displayMetrics.heightPixels / 3;

        Glide.with(getContext()).load(imageUrl).override(width,height).into(mProductPicture);

    }
}
