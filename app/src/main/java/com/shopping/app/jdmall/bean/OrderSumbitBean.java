package com.shopping.app.jdmall.bean;

/**
 * Created by 龚浩 on 2017/4/10.
 */

public class OrderSumbitBean {

    /**
     * response : orderSumbit
     * orderInfo : {"orderId":" 032096 ","price":"450","paymentType":"1"}
     */

    private String response;
    /**
     * orderId :  032096
     * price : 450
     * paymentType : 1
     */

    private OrderInfoBean orderInfo;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public OrderInfoBean getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public static class OrderInfoBean {
        private String orderId;
        private String price;
        private String paymentType;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }
    }
}
