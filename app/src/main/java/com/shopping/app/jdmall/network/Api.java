package com.shopping.app.jdmall.network;


import com.shopping.app.jdmall.bean.HomeTopicbean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Retrofit的api接口,定义call方法
 *
 */

public interface Api {
    @GET("home")
    Call<HomeTopicbean> listHome();

    Call<HomeTopicbean.HomeTopicBean> listHome(int size);
}
