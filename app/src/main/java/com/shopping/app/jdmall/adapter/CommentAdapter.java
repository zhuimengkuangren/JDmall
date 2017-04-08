package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.shopping.app.jdmall.bean.CommentBeans;
import com.shopping.app.jdmall.widget.CommentItemView;

import java.util.List;

/**
 * Created by user on 2017/4/6.
 */

public class CommentAdapter extends BaseListAdapter {
    private static final String TAG = "CommentAdapter";
    private CommentItemView mCommentItemView;
    public CommentAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    protected View onCreateView(int position) {
        mCommentItemView = new CommentItemView(getContext());
        return mCommentItemView;
    }

    @Override
    protected void onBindView(int position, View convertView) {
        CommentBeans.CommentBean bean = (CommentBeans.CommentBean) getList().get(position);
        Log.d(TAG, "onBindNormalView: "+bean.getContent());
        CommentItemView convertView1 = (CommentItemView) convertView;
        convertView1.bindView(bean);
    }
}
