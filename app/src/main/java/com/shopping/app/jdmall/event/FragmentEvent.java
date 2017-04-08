package com.shopping.app.jdmall.event;

/**
 * Created by lzl on 2017/4/5.
 */

public class FragmentEvent {

    private final String mTab;
    private int mId;

    public FragmentEvent(int id,String tab) {

        mId = id;
        mTab = tab;
    }

    public int getFragmentId() {
        return mId;
    }

    public String getFragmentTab() {
        return mTab;
    }
}
