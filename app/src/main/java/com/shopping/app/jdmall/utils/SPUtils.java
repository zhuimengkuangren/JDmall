package com.shopping.app.jdmall.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/4/5.
 */

public class SPUtils {
    private static final String NAME = "JDMall";
    private static final String COLLECT = "collect";
    private static final String GUANZHU = "guanzhu";


    private static SharedPreferences getSharePreferences(Context context){
        return context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
    }
    public static boolean getBoolean(Context context,String key,boolean values){
        SharedPreferences sharePreferences = getSharePreferences(context);
        boolean aBoolean = sharePreferences.getBoolean(key, values);
        return aBoolean;
    }
    public static void setBoolean (Context context,String key, boolean values) {
        SharedPreferences sharePreferences = getSharePreferences(context);
        SharedPreferences.Editor edit = sharePreferences.edit();
        edit.putBoolean(key, values);
        edit.commit();
    }

    public static String getString(Context context,String key,String value){
        SharedPreferences sharePreferences = getSharePreferences(context);
        return  sharePreferences.getString(key,value);
    }

    public static void setString(Context context,String key,String value){
        SharedPreferences sharePreferences = getSharePreferences(context);
        sharePreferences.edit().putString(key,value).apply();

    }
    public static boolean getCollect(Context context,boolean values){
        boolean aBoolean = getBoolean(context, COLLECT, values);
        return aBoolean;
    }
    public static void setCollect (Context context, boolean values) {
        setBoolean(context,COLLECT,values);
    }
    public static boolean getGuanZhu(Context context,boolean values){
        boolean aBoolean = getBoolean(context, GUANZHU, values);
        return aBoolean;
    }
    public static void setGuanZhu (Context context, boolean values) {
        setBoolean(context,GUANZHU,values);
    }

}
