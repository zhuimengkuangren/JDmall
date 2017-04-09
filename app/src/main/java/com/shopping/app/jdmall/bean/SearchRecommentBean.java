package com.shopping.app.jdmall.bean;

import java.util.List;

/**
 * Created by panpan on 2017/4/8.
 */

public class SearchRecommentBean {

    /**
     * response : searchrecommend
     * searchKeywords : ["帽子","时尚女裙","时尚秋装","韩版外套","情女装","女鞋","韩版棉袄","韩版秋装","女士外套"]
     */

    private String response;
    private List<String> searchKeywords;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<String> getSearchKeywords() {
        return searchKeywords;
    }

    public void setSearchKeywords(List<String> searchKeywords) {
        this.searchKeywords = searchKeywords;
    }
}
