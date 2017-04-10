package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.FindBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;

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
    @BindView(R.id.share_sdk)
    ImageView mShareSdk;
    @BindView(R.id.arrow_right)
    ImageView mArrowRight;
    private onMyClickListerner mListerner;
    private PopupWindow mWindow;

    public DetailInfoView(Context context) {
        this(context, null);
    }

    public DetailInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ShareSDK.initSDK(getContext());
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_find_detail_info, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(FindBean.ProductListBean mBean) {
        mName.setText(mBean.getName());
        mOriginprice.setText("￥ " + mBean.getMarketPrice());
        mRealprice.setText(mBean.getPrice() + "");
    }


    public void setMyOnClickListerner(onMyClickListerner listerner) {
        mListerner = listerner;
    }

    @OnClick({R.id.share_sdk, R.id.arrow_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.share_sdk:
                mListerner.share();
                break;
            case R.id.arrow_right:
                startPopupWindow();
                break;
        }
    }

    private void startPopupWindow() {
        View convertView = View.inflate(getContext(), R.layout.view_safe_popup, null);
        mWindow = new PopupWindow(convertView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindow.dismiss();
            }
        });
        mWindow.setFocusable(true);
        mWindow.setOutsideTouchable(true);
        //设置动画样式
        mWindow.setAnimationStyle(R.style.pop_buycar);
        mWindow.showAtLocation(convertView, Gravity.BOTTOM, 0, 0);//显示在指定位置,在0,0的位置
    }

    public interface onMyClickListerner {
        void share();
    }
}
