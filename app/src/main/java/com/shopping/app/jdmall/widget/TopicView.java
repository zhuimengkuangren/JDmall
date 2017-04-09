package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.TopicRenBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/6.
 */

public class TopicView extends LinearLayout {
    @BindView(R.id.image_view)
    ImageView mImageView;
    @BindView(R.id.text_view)
    TextView mTextView;

    public TopicView(Context context) {
        this(context, null);
    }

    public TopicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_topic, this);
        ButterKnife.bind(this,this);
    }

    public void bindView(TopicRenBean.TopicBean topicBean) {
        mTextView.setText(topicBean.getName());
        Glide.with(getContext()).load(Constant.HOST + topicBean.getPic()).into(mImageView);
    }
}
