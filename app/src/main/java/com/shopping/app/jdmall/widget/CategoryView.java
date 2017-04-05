package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.adapter.CategoryListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/4/5.
 */
public class CategoryView extends LinearLayout {
    @BindView(R.id.search_view)
    SearchView mSearchView;
    @BindView(R.id.list_view)
    ListView mListView;
    @BindView(R.id.right_view)
    CategoryRightView mRightView;
    private List<String> mList = new ArrayList<String>();

    public CategoryView(Context context) {
        this(context, null);
    }

    public CategoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
        init();
    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            mList.add("条目" + i);
        }
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_category, this);
        ButterKnife.bind(this);
        CategoryListAdapter listAdapter = new CategoryListAdapter(getContext(), mList);
        mListView.setAdapter(listAdapter);
    }
}
