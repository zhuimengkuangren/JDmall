package com.shopping.app.jdmall.bean;

import java.util.Arrays;

/**
 * Created by lzl on 2017/4/8.
 */

public class SkuBean {
    private int id;
    private int num;
    private int[] attrs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int[] getAttrs() {
        return attrs;
    }

    public void setAttrs(int[] attrs) {
        this.attrs = attrs;
    }

    @Override
    public String toString() {
        return "SkuBean{" +
                "id=" + id +
                ", num=" + num +
                ", attrs=" + Arrays.toString(attrs) +
                '}';
    }
}
