package com.shopping.app.jdmall.ui.activity;

import android.widget.EditText;
import android.widget.ImageView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.CargoBean;
import com.shopping.app.jdmall.widget.pullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 龚浩 on 2017/4/6.
 */

public class SearchActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_voice)
    ImageView mIvVoice;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.fresh_view)
    pullToRefreshView mFreshView;
    List<CargoBean> mList = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.view_search_detail;
    }

    @Override
    protected void init() {
        super.init();
        mFreshView.setData(mList);
        startLoadData();
    }

    private void startLoadData() {

    }


}
