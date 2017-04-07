package com.shopping.app.jdmall.bean;

import java.util.List;

/**
 * Created by user on 2017/4/7.
 */

public class BuyCarBean {

    /**
     * available : true
     * bigPic : ["/images/product/detail/bigcar5.jpg","/images/product/detail/bigcar6.jpg","/images/product/detail/bigcar7.jpg","/images/product/detail/bigcar8.jpg"]
     * buyLimit : 10
     * commentCount : 0
     * id : 2
     * inventoryArea : 全国
     * leftTime : 17000
     * limitPrice : 1
     * marketPrice : 180
     * name : 粉色毛衣
     * pics : ["/images/product/detail/q1.jpg","/images/product/detail/q3.jpg","/images/product/detail/q4.jpg"]
     * price : 100
     * productProperty : [{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"},{"id":5,"k":"尺码","v":"XXXL"}]
     * score : 2
     */

    private ProductBean product;
    /**
     * product : {"available":true,"bigPic":["/images/product/detail/bigcar5.jpg","/images/product/detail/bigcar6.jpg","/images/product/detail/bigcar7.jpg","/images/product/detail/bigcar8.jpg"],"buyLimit":10,"commentCount":0,"id":2,"inventoryArea":"全国","leftTime":17000,"limitPrice":1,"marketPrice":180,"name":"粉色毛衣","pics":["/images/product/detail/q1.jpg","/images/product/detail/q3.jpg","/images/product/detail/q4.jpg"],"price":100,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"},{"id":5,"k":"尺码","v":"XXXL"}],"score":2}
     * response : product
     */

    private String response;

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public static class ProductBean {
        private boolean available;
        private int buyLimit;
        private int commentCount;
        private int id;
        private String inventoryArea;
        private int leftTime;
        private int limitPrice;
        private int marketPrice;
        private String name;
        private int price;
        private int score;
        private List<String> bigPic;
        private List<String> pics;
        /**
         * id : 1
         * k : 颜色
         * v : 红色
         */

        private List<ProductPropertyBean> productProperty;

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public int getBuyLimit() {
            return buyLimit;
        }

        public void setBuyLimit(int buyLimit) {
            this.buyLimit = buyLimit;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInventoryArea() {
            return inventoryArea;
        }

        public void setInventoryArea(String inventoryArea) {
            this.inventoryArea = inventoryArea;
        }

        public int getLeftTime() {
            return leftTime;
        }

        public void setLeftTime(int leftTime) {
            this.leftTime = leftTime;
        }

        public int getLimitPrice() {
            return limitPrice;
        }

        public void setLimitPrice(int limitPrice) {
            this.limitPrice = limitPrice;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public List<String> getBigPic() {
            return bigPic;
        }

        public void setBigPic(List<String> bigPic) {
            this.bigPic = bigPic;
        }

        public List<String> getPics() {
            return pics;
        }

        public void setPics(List<String> pics) {
            this.pics = pics;
        }

        public List<ProductPropertyBean> getProductProperty() {
            return productProperty;
        }

        public void setProductProperty(List<ProductPropertyBean> productProperty) {
            this.productProperty = productProperty;
        }

        public static class ProductPropertyBean {
            private int id;
            private String k;
            private String v;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getK() {
                return k;
            }

            public void setK(String k) {
                this.k = k;
            }

            public String getV() {
                return v;
            }

            public void setV(String v) {
                this.v = v;
            }
        }
    }
}
