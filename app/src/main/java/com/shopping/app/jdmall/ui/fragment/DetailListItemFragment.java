package com.shopping.app.jdmall.ui.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.widget.FindDetailInfoView;
import com.shopping.app.jdmall.widget.FindDetailTalkView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by user on 2017/4/6.
 */

public class DetailListItemFragment extends BaseListLoadMoreFragment {
    @BindView(R.id.view_pager_find)
    ViewPager mViewPagerFind;
    @BindView(R.id.ll_find_listitem)
    LinearLayout mLlFindListitem;
    @BindView(R.id.info_view)
    FindDetailInfoView mInfoView;
    @BindView(R.id.talk_view)
    FindDetailTalkView mTalkView;
    @BindView(R.id.view_pager_bottom)
    ViewPager mViewPagerBottom;

    @Override
    protected void startLoadMoreData() {

    }

    @Override
    protected BaseAdapter oncreateAdapter() {
        return null;
    }

    @Override
    protected void startLoadData() {

    }

    @Override//这个加载成功后创建这个view，基类已经做了加载
    protected View onCreateContentView() {
        //子类重写
        View view = View.inflate(getContext(), R.layout.view_listitem_detail_fragment, null);

        return view;
    }

    @OnClick({R.id.view_pager_find, R.id.view_pager_bottom})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_pager_find:
                break;
            case R.id.view_pager_bottom:
                break;
        }
    }
}
