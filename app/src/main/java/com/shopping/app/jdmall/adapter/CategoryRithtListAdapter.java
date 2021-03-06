package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.shopping.app.jdmall.bean.CategoryItemBean;
import com.shopping.app.jdmall.widget.CategoryRightView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by 龚浩 on 2017/4/5.
 */

public class CategoryRithtListAdapter extends BaseAdapter {
    private static final String TAG = "CategoryRithtListAdapte";


    private Context mContext;
    private HashMap<String, List<CategoryItemBean.CategoryBean>> mMap;
    private List<String> mList = new ArrayList<>();

    public CategoryRithtListAdapter(Context context, HashMap<String, List<CategoryItemBean.CategoryBean>> map) {
        mContext = context;
        mMap = map;
        Set<String> keySet = map.keySet();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new CategoryRightView(mContext);
        }
        CategoryRightView rightView = (CategoryRightView) convertView;
        String category = mList.get(position);
        List<CategoryItemBean.CategoryBean> beanList = mMap.get(category);
        rightView.setData(category, beanList);
        return rightView;

    }


    public void setData(HashMap<String, List<CategoryItemBean.CategoryBean>> map) {
        mList.clear();
        mMap = map;
        Set<String> keySet = mMap.keySet();
        Log.d(TAG, "setData: ==="+keySet.size());
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            mList.add(0,iterator.next());
        }
    }
}
