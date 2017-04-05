package com.shopping.app.jdmall.network;


import com.shopping.app.jdmall.bean.FindBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrofit的api接口,定义call方法
 *
 */

public interface Api {
    @GET("newproduct")
    Call<FindBean> listFind(@Query("page")int page,@Query("pageNum")int pageNum,@Query("orderby")String orderby);
}
