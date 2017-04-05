package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.adapter.FindItemAdapter;
import com.shopping.app.jdmall.bean.FindBean;

import java.util.List;

/**
 * Created by user on 2017/4/5.
 */

public class FindListView extends LinearLayout {

    private ListView mLvList;

    public FindListView(Context context) {
        this(context,null);
    }

    public FindListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_list_find, this);
        mLvList = (ListView) view.findViewById(R.id.lv_list_find_fragment);

    }
    public void bindView(List<FindBean.ProductListBean> mProductList){
        FindItemAdapter itemAdapter = new FindItemAdapter(getContext(), mProductList);
        mLvList.setAdapter(itemAdapter);
    }
}
