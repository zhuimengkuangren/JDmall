package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.CargoBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 龚浩 on 2017/4/6.
 */

public class QueryListItem extends RelativeLayout {
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_commend)
    TextView mTvCommend;
    @BindView(R.id.iv_fav)
    ImageView mIvFav;
    @BindView(R.id.buy_car)
    ImageView mBuyCar;

    private boolean isFav;

    public QueryListItem(Context context) {
        this(context, null);
    }

    public QueryListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.view_query_item, this);
        ButterKnife.bind(this, this);
        init();
    }

    private void init() {

    }

    public void setData(CargoBean.ProductListBean bean) {
        String url = Constant.HOST + bean.getPic();
        Glide.with(getContext()).load(url).into(mIvIcon);


        mTvTitle.setText(bean.getName());
        mTvPrice.setText("¥" + bean.getPrice());
        mTvCommend.setText("精彩评论:" + bean.getCommentCount() + "条");
    }

    @OnClick({R.id.buy_car, R.id.iv_fav})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buy_car:
                TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 100);
                animation.setDuration(2000);
                mBuyCar.startAnimation(animation);
                break;
            case R.id.iv_fav:
                isFav = !isFav;
                if (isFav) {
                    mIvFav.setImageResource(R.drawable.aid);
                } else {
                    mIvFav.setImageResource(R.drawable.aic);
                }
                break;
        }
    }
}
