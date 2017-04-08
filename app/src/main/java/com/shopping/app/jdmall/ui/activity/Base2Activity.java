package com.shopping.app.jdmall.ui.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shopping.app.jdmall.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/6.
 * 自己使用的activity基类----rennb
 */

public abstract class Base2Activity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.progress_bar)
    ImageView mProgressBar;
    @BindView(R.id.retry)
    Button mRetry;
    @BindView(R.id.loading_error)
    LinearLayout mLoadingError;
    @BindView(R.id.activity_content)
    FrameLayout mActivityContent;
    private ActionBar mSupportActionBar;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_base_list;
    }

    @Override
    protected void init() {
        super.init();
        setSupportActionBar(mToolBar);
        mSupportActionBar = getSupportActionBar();
        mSupportActionBar.setDisplayHomeAsUpEnabled(true);
        startLoadData();
    }

    /*@Nullable
    @Override
    public ActionBar getSupportActionBar() {
        return mSupportActionBar;
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    protected abstract void startLoadData();

    /**
     * 子类调用,加载数据成功
     */
    protected void onDataLoadedSuccess() {
        //隐藏进度条
        mProgressBar.setVisibility(View.GONE);
        //将Activity布局加入到FrameLayout根容器中
        mActivityContent.addView(onCreateContentView());
    }

    /**
     * 子类重写,实现该方法来创建自己的视图
     */
    protected abstract View onCreateContentView();

    /**
     * 子类调用,加载数据失败
     */
    protected void onDataLoadedFailed() {
        //进度条消失
        mProgressBar.setVisibility(View.GONE);
        //将失败视图显示
        mLoadingError.setVisibility(View.VISIBLE);
    }

    /**
     * 加载失败点击处理
     */
    @OnClick(R.id.retry)
    public void onClick() {

        //将失败的视图隐藏
        mLoadingError.setVisibility(View.GONE);
        //显示进度条
        mProgressBar.setVisibility(View.VISIBLE);
        //重新加载数据
        startLoadData();
    }
}
