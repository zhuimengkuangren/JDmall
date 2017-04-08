package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * listview adapter的基类
 *
 */

public abstract class BaseListAdapter<T> extends BaseAdapter {
    private static final String TAG = "BaseListAdapter";
    private Context mContext;
    private List<T> mList;

    public List<T> getList() {
        return mList;
    }

    public Context getContext() {
        return mContext;
    }

    public BaseListAdapter(Context context, List<T> list) {
        mContext = context;
        mList = list;
    }


    @Override
    public int getCount() {
        if(mList !=null){
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            Log.d(TAG, "getView: bug");
            convertView = onCreateView(position);//创建自定义Item的视图
        }
        onBindView(position,convertView);//绑定item的视图

        return convertView;
    }


    protected abstract View onCreateView(int position);

    protected abstract void onBindView(int position, View convertView);



}
