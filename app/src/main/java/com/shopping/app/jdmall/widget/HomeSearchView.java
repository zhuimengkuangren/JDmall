package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.event.CameraEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

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
    @BindView(R.id.ll_container)
    LinearLayout mLlContainer;
    private File file;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    public HomeSearchView(Context context) {
        this(context, null);
    }

    public HomeSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.view_serach, this);
        ButterKnife.bind(this, this);
        init();

    }

    private void init() {

    }

    @OnClick({R.id.camera, R.id.et_text, R.id.message, R.id.ll_container})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera:
                startPhoto();   //开始拍照
                break;
            case R.id.et_text:
                break;
            case R.id.message:
                break;
            case R.id.ll_container:
                break;
        }
    }

    private void startPhoto() {
        EventBus.getDefault().post(new CameraEvent());
    }
}
