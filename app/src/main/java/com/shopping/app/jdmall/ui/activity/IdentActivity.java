package com.shopping.app.jdmall.ui.activity;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.adapter.IdentCargoAdapter;
import com.shopping.app.jdmall.bean.FindBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 龚浩 on 2017/4/8.
 */

public class IdentActivity extends BaseActivity {


    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tv_display_name)
    TextView mTvDisplayName;
    @BindView(R.id.checkbox)
    CheckBox mCheckbox;
    @BindView(R.id.btn_commit)
    Button mBtnCommit;
    @BindView(R.id.info_bottom)
    LinearLayout mInfoBottom;
    @BindView(R.id.list_view)
    ListView mListView;
    private IdentCargoAdapter mAdapter;
    private List<FindBean.ProductListBean> mListData = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_ident;
    }

    @Override
    protected void init() {
        super.init();
        initListView();
        initToolBar();
    }

    private void initListView() {
        mAdapter = new IdentCargoAdapter(this, mListData);
        mListView.setAdapter(mAdapter);
    }


    private void initToolBar() {
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        TextView textView = new TextView(this);
        textView.setTextColor(Color.RED);
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);
        textView.setText("确认订单");
        //设置显示返回按钮
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        //定义actionbar的位置
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER);
        //设置显示customview
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(textView, lp);
    }

}
