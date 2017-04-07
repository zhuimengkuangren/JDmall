package com.shopping.app.jdmall.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lzl on 2017/4/6.
 */

/**
 * 购物车专用bean
 */
public class CarInfoBean implements Serializable{

    /**
     * prodNum : 3
     * product : {"buyLimit":10,"id":1,"name":"韩版时尚蕾丝裙","number":"100","pic":"/images/product/detail/c3.jpg","price":350,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"}]}
     */

    //至少一个
    private int prodNum = 1;
    /**
     * buyLimit : 10
     * id : 1
     * name : 韩版时尚蕾丝裙
     * number : 100
     * pic : /images/product/detail/c3.jpg
     * price : 350
     * productProperty : [{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"}]
     */

    private ProductBean product;

    public int getProdNum() {
        return prodNum;
    }

    public void setProdNum(int prodNum) {
        this.prodNum = prodNum;
    }

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public static class ProductBean {
        private int buyLimit;
        private int id;
        private String name;
        private String number;
        private String pic;
        private int price;
        /**
         * id : 1
         * k : 颜色
         * v : 红色
         */

        private List<ProductPropertyBean> productProperty;

        public int getBuyLimit() {
            return buyLimit;
        }

        public void setBuyLimit(int buyLimit) {
            this.buyLimit = buyLimit;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
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

        @Override
        public String toString() {
            return "ProductBean{" +
                    "buyLimit=" + buyLimit +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", number='" + number + '\'' +
                    ", pic='" + pic + '\'' +
                    ", price=" + price +
                    ", productProperty=" + productProperty +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CarInfoBean{" +
                "prodNum=" + prodNum +
                ", product=" + product +
                '}';
    }
}
