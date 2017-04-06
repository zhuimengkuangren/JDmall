package com.shopping.app.jdmall.network;



import com.shopping.app.jdmall.bean.BannerBean;

import com.shopping.app.jdmall.bean.CargoBean;

import com.shopping.app.jdmall.bean.CategoryItemBean;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.bean.FlashSaleBean;
import com.shopping.app.jdmall.bean.HomeTopicbean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**

 */


  public interface Api {
     @GET("home")
    Call<BannerBean> listHome();


    @GET("newproduct")
    Call<FindBean> listFind(@Query("page") int page, @Query("pageNum") int pageNum, @Query("orderby") String orderby);

    @GET("category")
    Call<CategoryItemBean> listCategory();

    @GET("productlist")
    Call<CargoBean> listProductlist(@Query("page") int page,
                                 @Query("pageNum") int pageNum,
                                 @Query("cId") int cId,
                                 @Query("orderby") String orderby);


    Call<HomeTopicbean.HomeTopicBean> listHome(int size);

    @GET("limitbuy")
    Call<List<FlashSaleBean>> listLimitBuy(@Query("page") int page, @Query("page") int pageNum );

}
