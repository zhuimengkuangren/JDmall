package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by panpan on 2017/4/6.
 */

public abstract class BasePagerAdapter extends PagerAdapter {

    public Context mContext;
    public int[] mList;


    public BasePagerAdapter(Context context,int[] list){
        mContext = context;
        mList = list;
    }


    @Override
    public int getCount() {
        if (mList != null){
            return mList.length;
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return CreatePagerView(container,position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    abstract View CreatePagerView(ViewGroup container, int position);

}
