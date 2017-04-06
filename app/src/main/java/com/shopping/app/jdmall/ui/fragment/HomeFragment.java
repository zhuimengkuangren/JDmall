package com.shopping.app.jdmall.ui.fragment;


import android.view.View;

import android.util.Log;
import android.widget.BaseAdapter;

import com.leon.loopviewpagerlib.FunBanner;
import com.shopping.app.jdmall.app.Constant;



/**
 * 主页fragment
 */
public class HomeFragment extends BaseShopListFragment {

    private String mPicture;
    private static final String TAG = "HomeFragment";

    @Override
    protected View onCreateHeader() {
        Log.d(TAG, "onCreateHeader: =====zoule");
        return new FunBanner.Builder(getContext())
                .setHeightWidthRatio(0.377f)
                .setEnableAutoLoop(true)
                .setImageUrlHost(Constant.HOST+"home/")
                .setImageUrls(new String[]{mPicture})
                .build();
     

    }


    @Override
    protected void startLoadData() {

    }


    @Override
    protected void onStartLoadMore() {

    }
    @Override
    protected BaseAdapter oncreateAdapter() {
        return null;
    }
}
