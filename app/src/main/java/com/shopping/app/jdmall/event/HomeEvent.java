package com.shopping.app.jdmall.event;

/**
 * Created by panpan on 2017/4/7.
 */

public class HomeEvent {

    private String mEvent;

    public HomeEvent(String event){

        mEvent = event;
    }

    public String getEvent(){
        return mEvent;
    }
}
