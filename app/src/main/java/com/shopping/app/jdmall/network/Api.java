package com.shopping.app.jdmall.network;


import com.shopping.app.jdmall.bean.CategoryItemBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Retrofit的api接口,定义call方法
 *
 */

public interface Api {
    @GET("category")
    Call<CategoryItemBean> listCategory();
}
