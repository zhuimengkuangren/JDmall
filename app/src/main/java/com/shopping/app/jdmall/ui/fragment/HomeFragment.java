package com.shopping.app.jdmall.ui.fragment;


import android.os.SystemClock;
import android.view.View;


/**
 * 主页fragment
 */
public class HomeFragment extends BaseFragment {


    @Override
    protected void startLoadData() {
        //测试
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        onDataLoadedFailed();
                    }
                });

            }
        }).start();
    }

    @Override
    protected View onCreateContentView() {
        return null;
    }
}
