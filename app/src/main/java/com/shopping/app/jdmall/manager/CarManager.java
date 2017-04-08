package com.shopping.app.jdmall.manager;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shopping.app.jdmall.app.JDApplication;
import com.shopping.app.jdmall.bean.CarInfoBean;
import com.shopping.app.jdmall.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzl on 2017/4/6.
 */

public class CarManager {

    private static final String TAG = "CarManager";
    private static final String CAR_DATA = "car_data";
    private static CarManager mCarManager;
    private final Context mContext;
    private final SparseArray<CarInfoBean> mSparseArray;
    //内存缓存,优于hashmap,用(商品id*100 + 商品颜色属性*10 + 商品尺寸属性)作为key


    private CarManager(Context context) {
        mContext = context;
        mSparseArray = new SparseArray<CarInfoBean>(100);

        //本地缓存转内存缓存
        listToSparseArray();
    }


    private void listToSparseArray() {
        //获取本地数据的list
        List<CarInfoBean> Datalist = getAllData();

        for (int i = 0; i < Datalist.size(); i++) {
            CarInfoBean carInfoBean = Datalist.get(i);
            int beanKey = getBeanKey(carInfoBean);
            mSparseArray.put(beanKey, carInfoBean);
        }
    }

    //单例模式
    public static CarManager getInstance() {
        if (mCarManager == null) {
            synchronized (CarManager.class) {
                if (mCarManager == null) {
                    mCarManager = new CarManager(JDApplication.getContext());
                }
            }
        }
        return mCarManager;
    }


    /**
     * 获取本地数据集合
     *
     * @return
     */
    public List<CarInfoBean> getAllData() {
        ArrayList<CarInfoBean> carInfoBeanList = new ArrayList<>();

        String json = SPUtils.getString(mContext, CAR_DATA, "");
        //如果缓存存在,则转换为list
        if (!TextUtils.isEmpty(json)) {
            carInfoBeanList = new Gson().fromJson(json, new TypeToken<List<CarInfoBean>>() {
            }.getType());
        }
        return carInfoBeanList;
    }

    /**
     * 用于添加购物车
     */
    public boolean add(CarInfoBean carInfoBean) {
        int key = getBeanKey(carInfoBean);

        CarInfoBean tempData = mSparseArray.get(key);
        if (tempData != null) {
            int totalNum = tempData.getProdNum() + carInfoBean.getProdNum();
            if (totalNum > 10) {
                tempData.setProdNum(10);
            } else {
                tempData.setProdNum(totalNum);
            }

        } else {
            tempData = carInfoBean;
        }

        //添加到内存
        mSparseArray.put(key, tempData);

        Log.d(TAG, "add:size "+mSparseArray.size() +"key"+ key);

        //同步到本地
        saveLocal();

        return true;
    }


    /**
     * 用于购物车内部
     *
     * @param carInfoBean
     */
    public void delele(CarInfoBean carInfoBean) {
        int key = getBeanKey(carInfoBean);

        //从内存中删除
        mSparseArray.delete(key);
        Log.d(TAG, "delele:size "+mSparseArray.size());


        //同步到本地
        saveLocal();
    }

    private int getBeanKey(CarInfoBean carInfoBean) {
        CarInfoBean.ProductBean product = carInfoBean.getProduct();
        List<CarInfoBean.ProductBean.ProductPropertyBean> productProperty = product.getProductProperty();
        //第一个元素为颜色,第二个元素为尺码
        int attrValue = productProperty.get(0).getId() * 10 + productProperty.get(1).getId();
        return carInfoBean.getProduct().getId() * 100 + attrValue;
    }

    /**
     * 用于购物车内部
     *
     * @param carInfoBean
     */
    public void update(CarInfoBean carInfoBean) {

        int key = getBeanKey(carInfoBean);
        Log.d(TAG, "update:size "+mSparseArray.size() + "key" + key);

        //更新内存
        mSparseArray.put(key, carInfoBean);

        //同步到本地
        saveLocal();
    }

    private void saveLocal() {
        //Sparse转换为list
        List<CarInfoBean> carInfoBeanList = SparseToList();
        //list转换为json
        String json = new Gson().toJson(carInfoBeanList);
        //保存到本地
        SPUtils.setString(mContext, CAR_DATA, json);

    }

    private List<CarInfoBean> SparseToList() {
        ArrayList<CarInfoBean> carBeenList = new ArrayList<>();
        for (int i = 0; i < mSparseArray.size(); i++) {
            carBeenList.add(mSparseArray.valueAt(i));
        }
        return carBeenList;
    }

}
