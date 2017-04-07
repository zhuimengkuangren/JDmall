package com.shopping.app.jdmall.network;


import com.shopping.app.jdmall.bean.BannerBean;
import com.shopping.app.jdmall.bean.CargoBean;
import com.shopping.app.jdmall.bean.CategoryItemBean;
import com.shopping.app.jdmall.bean.CommentBeans;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.bean.HomeTopicbean;

import com.shopping.app.jdmall.bean.LimitBuyBean;

import com.shopping.app.jdmall.bean.LogoutBean;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> login(@Field("username") String username, @Field("password") String password);

    @GET("logout")
    Call<LogoutBean> logout(@Query("userid") String userid);


    //http://localhost:8080/market/newproduct?page=1&pageNum=10&orderby=saleDown

    //新品上架请求
    @GET("newproduct")
    Call<FindBean> listNewProduct(@Query("page") int page, @Query("pageNum") int pageNum, @Query("orderby") String orderby);



}
