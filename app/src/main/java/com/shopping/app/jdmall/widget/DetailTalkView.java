package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
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
import com.shopping.app.jdmall.bean.CommentBeans;
import com.shopping.app.jdmall.ui.activity.CommentActivity;
import com.shopping.app.jdmall.utils.AnimationUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by user on 2017/4/6.
 */

public class DetailTalkView extends RelativeLayout {
    private static final String TAG = "DetailTalkView";
    @BindView(R.id.tv_user_talk)
    TextView mTvUserTalk;
    @BindView(R.id.tv_user_talkcounts)
    TextView mTvUserTalkcounts;
    @BindView(R.id.tv_detail_good_rate)
    TextView mTvDetailGoodRate;
    int rowHeight = 0;
    int padding = (int) getResources().getDimension(R.dimen.size_middle);
    String[] categorys = {"质量不错", "物流很快", "服务态度很好", "材质不错", "撕不烂", "锤不坏", "拉不断", "非常便宜", "酷炫", "性价比高", "包装很好", "弹性很好", "手感不错", "摸起来很舒服"};
    @BindView(R.id.fl_category_find)
    FrameLayout mFlCategoryFind;
    @BindView(R.id.tv_look_comment)
    TextView mBtnLookComment;
    private List<CommentBeans.CommentBean> mComment;
    private FindCategoryView mFindCategoryView;
    private FrameLayout mFl;
    private ImageView mIv;
    private FlowLayout mFlowLayout;
    private TextView mTextView;
    private View mView;

    public DetailTalkView(Context context) {
        this(context, null);
    }

    public DetailTalkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getViewTreeObserver().addOnGlobalLayoutListener(listerner);
        init();
    }

    private int mHeight;
    private int mTop;
    ViewTreeObserver.OnGlobalLayoutListener listerner = new ViewTreeObserver.OnGlobalLayoutListener() {

        @Override
        public void onGlobalLayout() {
            getViewTreeObserver().removeOnGlobalLayoutListener(listerner);
            mHeight = mFlCategoryFind.getHeight();

            mTop = mTextView.getTop();

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mFlCategoryFind.getLayoutParams();//获取布局参数
            rowHeight = (int) (rowHeight * 2.6f);
            layoutParams.height = (int) (rowHeight);
            mFlCategoryFind.setLayoutParams(layoutParams);
        }
    };

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_find_detail_talk, this);
        ButterKnife.bind(this, this);
        initData();

    }

    private void initData() {
        mView = View.inflate(getContext(), R.layout.view_category_findfragment, null);
        mFl = (FrameLayout) mView.findViewById(R.id.ll_find_fragment);
        mIv = (ImageView) mView.findViewById(R.id.iv_arrow_find);

        mFlowLayout = new FlowLayout(getContext());
        for (int i = 0; i < categorys.length; i++) {
            mTextView = getTextView();
            mTextView.setText(categorys[i]);
            StateListDrawable stateListDrawable = getStateListDrawable();
            mTextView.setBackgroundDrawable(stateListDrawable);
            final int position = i;
            //条目点击
            mFlowLayout.addView(mTextView);
            mTextView.measure(0, 0);
            rowHeight = mTextView.getMeasuredHeight();
        }
        mFl.addView(mFlowLayout);
        mFlCategoryFind.removeAllViews();
        mFlCategoryFind.addView(mView);
        mIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoadAnimation();
            }

        });
    }

    boolean isOpen;

    private void startLoadAnimation() {
        if (isOpen) {
            AnimationUtils.animationViewHeight(mFlCategoryFind, mHeight, rowHeight);
        } else {
            AnimationUtils.animationViewHeight(mFlCategoryFind, rowHeight, mHeight);
        }
        isOpen = !isOpen;
    }

    private TextView getTextView() {
        TextView textView = new TextView(getContext());
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(padding, padding, padding, padding);
        return textView;
    }

    private StateListDrawable getStateListDrawable() {
        GradientDrawable normalDrawable = new GradientDrawable();
        normalDrawable.setCornerRadius(5.0f);

        normalDrawable.setColor(getArgb());
        //选择器
        StateListDrawable stateListDrawable = new StateListDrawable();
        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setCornerRadius(5.0f);//设置圆角
        pressedDrawable.setColor(Color.DKGRAY);

        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        stateListDrawable.addState(new int[]{}, normalDrawable);
        return stateListDrawable;
    }

    private int getArgb() {
        int alpha = 255;
        int red = 255;//30到230
        int green = 182;
        int blue = 193;
        return Color.argb(alpha, red, green, blue);
    }

    @OnClick({R.id.tv_detail_good_rate, R.id.tv_look_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_detail_good_rate:
            case R.id.tv_look_comment:
                startCommentActivity();
                break;
        }
    }

    private void startCommentActivity() {
        Intent intent = new Intent(getContext(), CommentActivity.class);
        getContext().startActivity(intent);
    }
}
