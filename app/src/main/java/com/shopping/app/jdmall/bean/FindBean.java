package com.shopping.app.jdmall.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 2017/4/5.
 */

public class FindBean {

    /**
     * listCount : 10
     * productList : [{"id":12,"marketPrice":180,"name":"女士外套","pic":"/images/product/detail/q10.jpg","price":160},{"id":15,"marketPrice":300,"name":"韩版粉嫩外套","pic":"/images/product/detail/q13.jpg","price":160},{"id":20,"marketPrice":200,"name":"妈妈新款","pic":"/images/product/detail/q18.jpg","price":160},{"id":22,"marketPrice":200,"name":"新款毛衣","pic":"/images/product/detail/q20.jpg","price":160},{"id":25,"marketPrice":150,"name":"新款秋装","pic":"/images/product/detail/q23.jpg","price":160},{"id":26,"marketPrice":200,"name":"粉色系暖心套装","pic":"/images/product/detail/q24.jpg","price":200},{"id":27,"marketPrice":150,"name":"韩版粉嫩外套","pic":"/images/product/detail/q25.jpg","price":160},{"id":28,"marketPrice":300,"name":"春装新款","pic":"/images/product/detail/q26.jpg","price":200},{"id":29,"marketPrice":180,"name":"日本奶粉","pic":"/images/product/detail/q26.jpg","price":160},{"id":30,"marketPrice":200,"name":"超凡奶粉","pic":"/images/product/detail/q26.jpg","price":160}]
     * response : newProduct
     */

    private int listCount;
    private String response;
    /**
     * id : 12
     * marketPrice : 180
     * name : 女士外套
     * pic : /images/product/detail/q10.jpg
     * price : 160
     */

    private List<ProductListBean> productList;

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public static class ProductListBean implements Serializable {


        private int numbers;
        private int id;
        private int marketPrice;
        private String name;
        private String pic;
        private int price;

        public int getNumbers() {
            return numbers;
        }

        public void setNumbers(int numbers) {
            this.numbers = numbers;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

    }
}
