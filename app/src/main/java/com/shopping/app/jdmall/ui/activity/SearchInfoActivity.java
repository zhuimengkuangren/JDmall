package com.shopping.app.jdmall.ui.activity;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.shopping.app.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/4/6.
 */

public class SearchInfoActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_voice)
    ImageView mIvVoice;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.list_search)
    ListView mListSearch;

    @Override
    protected int getLayoutResId() {
        return R.layout.view_search_detail;
    }

    @Override
    protected void init() {
        ButterKnife.bind(this, this);
        startLoadDatas();   //从网上请求数据
    }

    private void startLoadDatas() {

    }
}
