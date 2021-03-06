package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.ui.activity.SearchInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 龚浩 on 2017/4/5.
 */

public class SearchView extends RelativeLayout {
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_search, this);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.iv_search, R.id.et_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                getContext().startActivity(new Intent(getContext(),SearchInfoActivity.class));
                break;
            case R.id.et_search:
                getContext().startActivity(new Intent(getContext(),SearchInfoActivity.class));
                break;
        }
    }
}
