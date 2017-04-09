package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.shopping.app.jdmall.R;

import java.util.List;

/**
 * Created by panpan on 2017/4/8.
 */

public class SearchRecommentAdapter extends BaseListAdapter {

    private List<String> mList;

    public SearchRecommentAdapter(Context context, List<String> list) {
        super(context, list);
        mList = list;
    }

    @Override
    protected View onCreateView(int position) {
        TextView textView = new TextView(getContext());
        int padding = getContext().getResources().getDimensionPixelSize(R.dimen.padding);
        textView.setPadding(padding,padding,padding,padding);
        String s = mList.get(position);
        textView.setText(s);
        return textView;
    }

    @Override
    protected void onBindView(int position, View convertView) {
    }
}
