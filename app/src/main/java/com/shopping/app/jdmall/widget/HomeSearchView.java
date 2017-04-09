package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.event.HomeEvent;
import com.shopping.app.jdmall.ui.activity.HomeMsgActivity;
import com.shopping.app.jdmall.ui.activity.SearchInfoActivity;
import com.shopping.app.jdmall.ui.activity.TestScanActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by panpan on 2017/4/5.
 */

public class HomeSearchView extends RelativeLayout {
    @BindView(R.id.camera)
    ImageView mCamera;
    @BindView(R.id.et_text)
    EditText mEtText;
    @BindView(R.id.message)
    ImageView mMessage;
    @BindView(R.id.search)
    ImageView mSearch;
    @BindView(R.id.ll_container)
    LinearLayout mLlContainer;

    public HomeSearchView(Context context) {
        this(context, null);
    }

    public HomeSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.view_home_serach, this);
        ButterKnife.bind(this, this);
        init();

    }

    private void init() {

    }

    private static final String TAG = "HomeSearchView";
    @OnClick({R.id.camera, R.id.et_text, R.id.search, R.id.message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera:
                getContext().startActivity(new Intent(getContext(), TestScanActivity.class));
                break;
            case R.id.et_text:
                getContext().startActivity(new Intent(getContext(),SearchInfoActivity.class));
                break;
            case R.id.search:
                getContext().startActivity(new Intent(getContext(),SearchInfoActivity.class));
                break;
            case R.id.message:
                openMsg();
                break;
        }
    }


    //通过EvenBus发送一个消息给mainActivity,让mainActivity去实现打开相机功能
    private void startPhoto() {
        EventBus.getDefault().post(new HomeEvent("Camera"));
    }

    private void openMsg() {
        getContext().startActivity(new Intent(getContext(),HomeMsgActivity.class));
    }
}
