package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.ui.activity.FindRecommandActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2017/4/5.
 */

public class FindHorizotalScrollView extends RelativeLayout {

    private static final String TAG = "FindHorizotalScrollView";
    @BindView(R.id.iv_collect)
    ImageView mIvCollect;
    @BindView(R.id.iv_lottery)
    ImageView mIvLottery;
    @BindView(R.id.iv_history)
    ImageView mIvHistory;
    @BindView(R.id.iv_groupbuy)
    ImageView mIvGroupbuy;
    @BindView(R.id.life_journey)
    ImageView mLifeJourney;
    @BindView(R.id.iv_order)
    ImageView mIvOrder;
    @BindView(R.id.iv_promotion)
    ImageView mIvPromotion;
    @BindView(R.id.iv_recharge)
    ImageView mIvRecharge;
    private LinearLayout mLinearLayout;
    private ImageView mStar;

    public static final String PRODUCT = "product";
    //public static final String NEW_PRODUCT = "newproduct";

    public FindHorizotalScrollView(Context context) {
        this(context, null);
    }

    public FindHorizotalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_horizotal_scrollview_findfragment, this);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.ll_horizotal_find_fragment);
        ButterKnife.bind(this, this);

    }


    @OnClick({R.id.iv_collect, R.id.iv_lottery,R.id.iv_groupbuy, R.id.iv_history, R.id.life_journey, R.id.iv_order, R.id.iv_promotion, R.id.iv_recharge})
    public void onClick(View view) {
        switch (view.getId()) {
            //星星按钮
            case R.id.iv_collect:
                startHotProductActivity(PRODUCT, "热门商品");
                break;
            case R.id.iv_groupbuy:
                //"新品上架"
                startHotProductActivity(PRODUCT, "新品上架");
                break;
            case R.id.iv_history:

                break;
            case R.id.life_journey:
                break;
            //彩票按钮
            case R.id.iv_lottery:
                startHotProductActivity(PRODUCT, "热门商品");
                break;
            case R.id.iv_order:
                startHotProductActivity(PRODUCT, "新品上架");
                break;
            case R.id.iv_promotion:
                break;
            case R.id.iv_recharge:
                break;
        }
    }

    //通过type(热门商品，新品上架)的判断，进行启动activity的复用
    private void startHotProductActivity(String product, String type) {
        Intent intent = new Intent(getContext(), FindRecommandActivity.class);
        intent.putExtra(product, type);
        getContext().startActivity(intent);
    }
}
