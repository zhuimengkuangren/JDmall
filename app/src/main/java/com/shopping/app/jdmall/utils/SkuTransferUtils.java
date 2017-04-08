package com.shopping.app.jdmall.utils;

import com.shopping.app.jdmall.bean.SkuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzl on 2017/4/8.
 */

public class SkuTransferUtils {

    public static String ListToString(List<SkuBean> skuList) {
        String result = "";
        for (int i = 0; i < skuList.size(); i++) {
            SkuBean skuBean = skuList.get(i);
            int id = skuBean.getId();
            int num = skuBean.getNum();
            int[] attrs = skuBean.getAttrs();
            result += id + ":" + num + ":" + attrs[0] + "," + attrs[1] + "|";
        }
        return result.substring(0, result.length() - 1);
    }

    public static List<SkuBean> StringToList(String str) {
        ArrayList<SkuBean> skuList = new ArrayList<>();

        String replaceStr = str.replace("|", "#");//split无法识别"|",这是一个bug?
        String[] beanStrs = replaceStr.split("#");//bean字符串数组
        for (int i = 0; i < beanStrs.length; i++) {
            String beanStr = beanStrs[i];//bean字符串
            String[] beanUnits = beanStr.split(":");//bean元素数组
            int id = Integer.parseInt(beanUnits[0]);//id
            int num = Integer.parseInt(beanUnits[1]);//数量
            String[] beanattrUnits = beanStr.split(",");//属性元素数组
            int color = Integer.parseInt(beanUnits[0]);//颜色
            int size = Integer.parseInt(beanUnits[1]);//尺码
            int[] attrs = new int[]{color, size};

            //填充数据
            SkuBean skuBean = new SkuBean();
            skuBean.setNum(num);
            skuBean.setId(id);
            skuBean.setAttrs(attrs);

            skuList.add(skuBean);
        }

        return skuList;
    }


}
