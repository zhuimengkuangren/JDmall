package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.CommentBeans;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/4/6.
 */

public class CommentItemView extends RelativeLayout {
    @BindView(R.id.iv_comment_icon)
    ImageView mIcon;
    @BindView(R.id.tv_name_comment)
    TextView mName;
    @BindView(R.id.ratingbar_comment)
    RatingBar mRatingbar;
    @BindView(R.id.tv_comment)
    TextView mComment;
    @BindView(R.id.time_comment)
    TextView mTime;

    public CommentItemView(Context context) {
        this(context, null);
    }

    public CommentItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_item_comment, this);
        ButterKnife.bind(this, this);

    }
    public void bindView(CommentBeans.CommentBean bean){
        mName.setText(bean.getUsername());
        mComment.setText(bean.getContent());
        mTime.setText(bean.getTime()+"");
    }
}
