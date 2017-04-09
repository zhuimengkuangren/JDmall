package com.shopping.app.jdmall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopping.app.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 龚浩 on 2017/4/9.
 */

public class OrderCommitSuccessActivity extends BaseActivity {
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.result)
    TextView mResult;
    @BindView(R.id.iv_result)
    ImageView mIvResult;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_commit_success;
    }

    @Override
    protected void init() {
        super.init();
        initToolBar();
        Intent intent = getIntent();
        String result = intent.getStringExtra("result");
        initResult(result);

    }

    private void initResult(final String result) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 3; i >= 0; i--) {
                    final int finalI = i;
                    if (i == 0) {
                        finish();
                        navigateTo(MainActivity.class);
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (result.equals("success")) {
                                mResult.setText("订单提交成功,还有" + finalI + "秒跳转首页,点击直接跳转");
                                mIvResult.setImageResource(R.drawable.checked);
                            } else {
                                mResult.setText("订单提交失败,还有" + finalI + "秒跳转首页,点击直接跳转");
                                mIvResult.setImageResource(R.drawable.ysf_ic_failed);
                            }
                        }
                    });
                    SystemClock.sleep(1000);
                }
            }
        }).start();

    }

    private void initToolBar() {
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        TextView textView = new TextView(this);
        textView.setTextColor(getResources().getColor(R.color.yellow));
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);
        textView.setText("提交订单结果");
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


    @OnClick(R.id.result)
    public void onClick() {
        finish();
        navigateTo(MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
