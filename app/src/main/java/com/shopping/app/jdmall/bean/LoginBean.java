package com.shopping.app.jdmall.bean;

/**
 * Created by xuanxuan on 2017/4/6.
 */

public class LoginBean {

    /**
     * response : login
     * userInfo : {"userid":"20428"}
     */

    private String response;
    private UserInfoBean userInfo;
    private String error;
    private String error_code;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        /**
         * userid : 20428
         */

        private String userid;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
