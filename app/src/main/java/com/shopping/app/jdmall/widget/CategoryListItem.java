package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shopping.app.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/4/5.
 */

public class CategoryListItem extends LinearLayout {
    @BindView(R.id.tv_categoty)
    TextView mTvCategoty;

    public CategoryListItem(Context context) {
        this(context, null);
    }

    public CategoryListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.list_category_item, this);
        ButterKnife.bind(this);
    }

    public void setData(String data) {
        mTvCategoty.setText(data);
    }
}
