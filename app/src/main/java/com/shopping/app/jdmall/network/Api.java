package com.shopping.app.jdmall.network;


import com.shopping.app.jdmall.bean.CategoryItemBean;
import com.shopping.app.jdmall.bean.FindBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**

 */

 interface Api {

    @GET("newproduct")
    Call<FindBean> listFind(@Query("page")int page, @Query("pageNum")int pageNum, @Query("orderby")String orderby);

    @GET("category")
    Call<CategoryItemBean> listCategory();

}
