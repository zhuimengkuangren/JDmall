package com.shopping.app.jdmall.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/4/5.
 */

public class SPUtils {
    private static final String NAME = "JDMall";

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
}
