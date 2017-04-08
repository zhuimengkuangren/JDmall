package com.shopping.app.jdmall.bean;

import java.util.List;

/**
 * Created by xuanxuan on 2017/4/8.
 */

public class OrderBean {

    /**
     * orderList : [{"flag":1,"orderId":"268358","price":402,"status":"未处理","time":"1491613268443"}]
     * response : orderList
     */

    private String response;
    private List<OrderListBean> orderList;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        /**
         * flag : 1
         * orderId : 268358
         * price : 402
         * status : 未处理
         * time : 1491613268443
         */

        private int flag;
        private String orderId;
        private int price;
        private String status;
        private String time;

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
