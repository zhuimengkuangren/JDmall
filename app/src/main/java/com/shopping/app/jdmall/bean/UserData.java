package com.shopping.app.jdmall.bean;

/**
 * Created by xuanxuan on 2017/4/7.
 * 保存公用数据
 */

public class UserData {
    private static UserData sUserData = null;
    private UserData(){}

    public static UserData getInstance(){
        if(sUserData == null){
            synchronized (UserData.class){
                if(sUserData == null){
                    sUserData = new UserData();
                }
            }
        }
        return sUserData;
    }

    /**
     * 保存的数据
     */
    private String userid;//用户id

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
