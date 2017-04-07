package com.shopping.app.jdmall.manager;

import android.content.Context;
import android.text.TextUtils;
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

    private static final String CAR_DATA = "car_data";
    private static CarManager mCarManager;
    private final Context mContext;
    private final SparseArray<CarInfoBean> mSparseArray;//内存缓存,优于hashmap,用商品id作为key



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
            mSparseArray.put(carInfoBean.getProduct().getId(), carInfoBean);
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

        String json = SPUtils.getString(mContext, CAR_DATA);
        //如果缓存存在,则转换为list
        if (!TextUtils.isEmpty(json)) {
            carInfoBeanList = new Gson().fromJson(json, new TypeToken<List<CarInfoBean>>() {
            }.getType());
        }
        return carInfoBeanList;
    }

    /**
     * 增,删,改
     */
   /* public void add(CarInfoBean carInfoBean) {

        //内存中查找
        CarInfoBean tempData = mSparseArray.get(carInfoBean.getProduct().getId());
        if (tempData != null) {
            tempData.setProdNum(tempData.getProdNum() + 1);
        } else {
            tempData = carInfoBean;
        }

        //添加到内存
        mSparseArray.put(tempData.getProduct().getId(),tempData);

        //同步到本地
        saveLocal();
    }*/

    //删除数据
    public void delele(CarInfoBean carInfoBean) {

        //从内存中删除
        mSparseArray.delete(carInfoBean.getProduct().getId());

        //同步到本地
        saveLocal();
    }

    public void update(CarInfoBean carInfoBean) {
        //更新内存
        mSparseArray.put(carInfoBean.getProduct().getId(), carInfoBean);

        //同步到本地
        saveLocal();
    }

    private void saveLocal() {
        //Sparse转换为list
        List<CarInfoBean> carInfoBeanList = SparseToList();
        //list转换为json
        String json = new Gson().toJson(carInfoBeanList);
        //保存到本地
        SPUtils.setString(mContext,CAR_DATA,json);

    }

    private List<CarInfoBean> SparseToList() {
        ArrayList<CarInfoBean> carBeenList = new ArrayList<>();
        for (int i = 0; i < mSparseArray.size(); i++) {
            carBeenList.add(mSparseArray.valueAt(i));
        }
        return carBeenList;
    }


}

