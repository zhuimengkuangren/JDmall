package com.shopping.app.jdmall.network;


import com.shopping.app.jdmall.bean.BannerBean;
import com.shopping.app.jdmall.bean.CargoBean;
import com.shopping.app.jdmall.bean.CategoryItemBean;
import com.shopping.app.jdmall.bean.CommentBeans;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.bean.HomeTopicbean;
import com.shopping.app.jdmall.bean.LimitBuyBean;

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

    Call<CargoBean> listProductlist(@Query("page") int page, @Query("pageNum") int pageNum, @Query("cId") int cId, @Query("orderby") String orderby);

    Call<HomeTopicbean.HomeTopicBean> listHome(int size);

    //限时促销请求
    @GET("limitbuy")
    Call<LimitBuyBean> listLimitBuy(@Query("page") int page, @Query("page") int pageNum);


    @GET("product/comment")
    Call<CommentBeans> listCommet(@Query("pId") int pld, @Query("page") int page, @Query("pageNum") int pageNum);

    //热门商品请求
    @GET("hotproduct")
    Call<FindBean> listHotProduct(@Query("page") int page, @Query("pageNum") int pageNum, @Query("orderby") String orderby);


}
