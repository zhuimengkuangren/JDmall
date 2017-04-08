package com.shopping.app.jdmall.utils;

import com.google.gson.Gson;

/**
 * Created by xuanxuan on 2017/4/8.
 */

public class GsonUtils {

    public static<T> T  fromJson(String json,Class<T> tClass){
        Gson gson = new Gson();
        T obj = gson.fromJson(json, tClass);
        return obj;
    }
}
