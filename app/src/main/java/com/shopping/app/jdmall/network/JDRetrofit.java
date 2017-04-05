package com.shopping.app.jdmall.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shopping.app.jdmall.app.Constant;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit单例
 */

public class JDRetrofit {

    private static JDRetrofit sJDRetrofit;

    private final Api mApi;

    //设置宽大处理畸形的json
    private Gson mGson = new GsonBuilder().setLenient().create();

    //单例模式
    public static JDRetrofit getInstance() {
        if (sJDRetrofit == null) {
            synchronized (JDRetrofit.class) {
                if (sJDRetrofit == null) {
                    sJDRetrofit = new JDRetrofit();
                }
            }
        }
        return sJDRetrofit;
    }

    private JDRetrofit() {
        //使用Retrofit来实现Api接口 需要配置gson转换器
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.HOST)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build();
        mApi = retrofit.create(Api.class);
    }

    public Api getApi() {
        return mApi;
    }
}
