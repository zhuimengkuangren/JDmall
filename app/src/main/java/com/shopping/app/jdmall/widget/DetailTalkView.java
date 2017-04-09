package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.CommentBeans;
import com.shopping.app.jdmall.ui.activity.CommentActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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


    private List<CommentBeans.CommentBean> mComment;

    public DetailTalkView(Context context) {
        this(context, null);
    }

    public DetailTalkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_find_detail_talk, this);
        ButterKnife.bind(this, this);
        mTvDetailGoodRate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startCommentActivity();
            }
        });
    }
    private void startCommentActivity() {
        Intent intent = new Intent(getContext(), CommentActivity.class);
        getContext().startActivity(intent);
    }


}
