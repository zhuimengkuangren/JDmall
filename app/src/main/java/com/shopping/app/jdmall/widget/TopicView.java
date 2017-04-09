package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.bean.TopicRenBean;
import com.shopping.app.jdmall.ui.activity.DetailListItemActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/6.
 */

public class TopicView extends LinearLayout {
    @BindView(R.id.image_view)
    ImageView mImageView;
    @BindView(R.id.text_view)
    TextView mTextView;
    @BindView(R.id.linear_layout_item)
    LinearLayout mLinearLayoutItem;
    private TopicRenBean.TopicBean mTopicBean;

    public TopicView(Context context) {
        this(context, null);
    }

    public TopicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_topic, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(TopicRenBean.TopicBean topicBean) {
        mTopicBean = topicBean;
        mTextView.setText(topicBean.getName());
        Glide.with(getContext()).load(Constant.HOST + topicBean.getPic()).into(mImageView);
    }

    @OnClick(R.id.linear_layout_item)
    public void onViewClicked() {
        FindBean.ProductListBean productListBean = new FindBean.ProductListBean();
        productListBean.setName(mTopicBean.getName());
        productListBean.setId(mTopicBean.getId());
        productListBean.setPic(mTopicBean.getPic());
        productListBean.setPrice(66);
        productListBean.setMarketPrice(168);
        Intent intent = new Intent(getContext(), DetailListItemActivity.class);
        intent.putExtra("values",productListBean);
        getContext().startActivity(intent);
    }
}
