package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopping.app.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lzl on 2017/4/6.
 */

public class AddSubView extends FrameLayout {
    @BindView(R.id.iv_sub)
    ImageView mIvSub;
    @BindView(R.id.tv_num)
    TextView mTvNum;
    @BindView(R.id.iv_add)
    ImageView mIvAdd;

    private int value = 1;
    private int minValue = 1;
    private int maxValue = 10;
    private onNumberChangeListener mOnNumberChangeListener;

    public AddSubView(Context context) {
        this(context, null);
    }

    public AddSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.view_sub_add, this);
        ButterKnife.bind(this, this);

        int value = getValue();
        setValue(value);

    }

    @OnClick({R.id.iv_sub, R.id.iv_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_sub://减
                subNum();
                break;
            case R.id.iv_add://加
                addNum();
                break;
        }
    }

    private void addNum() {
        if(value < maxValue){
            value++;
        }
        setValue(value);
        if(mOnNumberChangeListener != null){
            mOnNumberChangeListener.onNumberChange(value);
        }
    }

    private void subNum() {
        if(value > minValue){
            value--;
        }
        setValue(value);
        if(mOnNumberChangeListener != null){
            mOnNumberChangeListener.onNumberChange(value);
        }
    }

    public int getValue() {
        String valueStr = mTvNum.getText().toString().trim();
        if(!TextUtils.isEmpty(valueStr)){
            value = Integer.parseInt(valueStr);
        }
        return value;

    }

    public void setValue(int value) {
        this.value = value;
        mTvNum.setText(value+"");
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * 接口监听数据变化
     */
    public interface onNumberChangeListener{
        void onNumberChange(int value);
    }

    public void setonNumberChangeListener(onNumberChangeListener onNumberChangeListener){
        mOnNumberChangeListener = onNumberChangeListener;
    }
}
