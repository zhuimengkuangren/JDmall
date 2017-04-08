package com.shopping.app.jdmall.utils;

import com.shopping.app.jdmall.bean.SkuBean;

import java.util.List;

/**
 * Created by lzl on 2017/4/8.
 */

public class SkuTransferUtils {

    public static String BeanToString(List<SkuBean> skuList){
        String result = "";
        for (int i = 0; i < skuList.size(); i++) {
            SkuBean skuBean = skuList.get(i);
            int id = skuBean.getId();
            int num = skuBean.getNum();
            int[] attrs = skuBean.getAttrs();
            result += id + ":" + num + ":" + attrs[0] + "," + attrs[1] + "|";
        }
        return result;
    }

}
