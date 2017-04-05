package com.shopping.app.jdmall.ui.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shopping.app.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment的基类
 */

public abstract class BaseFragment extends Fragment {

    @BindView(R.id.retry)
    Button mRetry;
    @BindView(R.id.loading_error)
    LinearLayout mLoadingError;
    @BindView(R.id.fragment_content)
    FrameLayout mFragmentContent;
    @BindView(R.id.progress_bar)
    ImageView mProgressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_base, null);
        ButterKnife.bind(this, root);

        //进度条动画开启
        AnimationDrawable drawable = (AnimationDrawable) mProgressBar.getDrawable();
        drawable.start();

        return root;
    }

    /**
     * 当fragment视图创建完成之后就开始加载数据
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startLoadData();
    }

    /**
     * 子类重写,加载数据
     */
    protected abstract void startLoadData();

    /**
     * 子类调用,加载数据成功
     */
    protected void onDataLoadedSuccess() {
        //隐藏进度条
        mProgressBar.setVisibility(View.GONE);
        //将Framgent布局加入到FrameLayout根容器中
        mFragmentContent.addView(onCreateContentView());
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
    public void onViewClicked() {
        //将失败的视图隐藏
        mLoadingError.setVisibility(View.GONE);
        //显示进度条
        mProgressBar.setVisibility(View.VISIBLE);
        //重新加载数据
        startLoadData();
    }
}
