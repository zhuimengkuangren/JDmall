package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.utils.AnimationUtils;

/**
 * Created by user on 2017/4/5.
 */

public class FindCategoryView extends RelativeLayout {
    String[] categorys={"京东号","型男号","潮女号","爱搞基","生活家","女神范","亲子园","数码控",
            "文艺咖","理财师","吃货党","品牌家","家居馆","视频购"};
    private int mHeight;//这是总高度
    int padding= (int) getResources().getDimension(R.dimen.size_middle);
    private FlowLayout mFlowLayout;
    private FrameLayout mFl;
    private ImageView mIv;
    private TextView mTextView;
    private int mLeft;
    int rowHeight=0;
    public FindCategoryView(Context context) {
        this(context,null);
    }

    public FindCategoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        mFl.getViewTreeObserver().addOnGlobalLayoutListener(listerner);
    }
    ViewTreeObserver.OnGlobalLayoutListener listerner=new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            getViewTreeObserver().removeOnGlobalLayoutListener(listerner);
            mHeight = mFl.getHeight();
            mLeft = mTextView.getLeft();

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mFl.getLayoutParams();//获取布局参数
            layoutParams.height = rowHeight;
            mFl.setLayoutParams(layoutParams);
        }
    };


    private void init() {
        View view =  View.inflate(getContext(), R.layout.view_category_findfragment, this);
        mFl = (FrameLayout) view.findViewById(R.id.ll_find_fragment);
        mIv = (ImageView) view.findViewById(R.id.iv_arrow_find);

        mFlowLayout = new FlowLayout(getContext());
        for (int i = 0; i < categorys.length; i++) {
                mTextView = getTextView();
                mTextView.setText(categorys[i]);
                StateListDrawable stateListDrawable = getStateListDrawable();
                mTextView.setBackgroundDrawable(stateListDrawable);
                final int position=i;
                //条目点击
                OnClickListener listerner = getOnClickListener(position);
                mTextView.setOnClickListener(listerner);
                mFlowLayout.addView(mTextView);
            mTextView.measure(0,0);
            rowHeight= mTextView.getMeasuredHeight()+mLeft;
        }
        mFl.addView(mFlowLayout);
        mIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoadAnimation();
            }

        });

    }
    boolean isOpen;
    private void startLoadAnimation() {
        if (isOpen){
            AnimationUtils.animationViewHeight(mFl,mHeight,rowHeight);
        }else{
            AnimationUtils.animationViewHeight(mFl,rowHeight,mHeight);
        }
        isOpen=!isOpen;
    }

    @NonNull
    private OnClickListener getOnClickListener(final int position) {
        return new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v==mIv){

                        }
                        switch (position){
                            case 0:
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            case 5:
                                break;
                            case 6:
                                break;
                            case 7:
                                break;
                            case 8:
                                break;
                            case 9:
                                break;
                            case 10:
                                break;
                            case 11:
                                break;
                            case 12:
                                break;
                            case 13:
                                break;
                        }
                    }
                };
    }

    @NonNull
    private TextView getTextView() {
        TextView textView = new TextView(getContext());
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(padding,padding,padding,padding);
        return textView;
    }

    private StateListDrawable getStateListDrawable() {
        GradientDrawable normalDrawable = new GradientDrawable();
        normalDrawable.setCornerRadius(5.0f);
        normalDrawable.setColor(Color.WHITE);
        //选择器
        StateListDrawable stateListDrawable = new StateListDrawable();
        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setCornerRadius(5.0f);//设置圆角
        pressedDrawable.setColor(Color.DKGRAY);

        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        stateListDrawable.addState(new int[]{}, normalDrawable);
        return stateListDrawable;
    }

}
