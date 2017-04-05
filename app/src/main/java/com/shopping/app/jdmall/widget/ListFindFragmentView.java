package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.shopping.app.jdmall.R;

/**
 * Created by user on 2017/4/5.
 */

public class ListFindFragmentView extends LinearLayout {
    public ListFindFragmentView(Context context) {
        this(context,null);
    }

    public ListFindFragmentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_list_fragment, this);
        ListView lvList = (ListView) view.findViewById(R.id.lv_list_find_fragment);

    }
    public void bindView(){

    }
}
