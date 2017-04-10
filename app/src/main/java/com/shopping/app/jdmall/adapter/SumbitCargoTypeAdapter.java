package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.iflytek.cloud.thirdparty.T;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.widget.CargoInfoView;
import com.shopping.app.jdmall.widget.SubmitItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 龚浩 on 2017/3/29.
 */

public class SumbitCargoTypeAdapter extends BaseAdapter {

    private static final int TYPE_NOMAL = 0;
    private static final int TYPE_PROGRESS = 1;
    private Context mContext;
    private List mDataList;

    public SumbitCargoTypeAdapter(Context context, List<T> dataList) {
        mContext = context;

        mDataList = dataList;
    }


    @Override
    public int getCount() {
        if (mDataList != null) {
            return mDataList.size() + 5;

        }
        return 5;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position < getCount() - 5) {
            convertView = new CargoInfoView(mContext);
        } else {
            convertView = new SubmitItem(mContext);
        }
        if (position < getCount() - 5) {
            CargoInfoView infoView = (CargoInfoView) convertView;
            FindBean.ProductListBean bean = (FindBean.ProductListBean) mDataList.get(position);
            infoView.setData(bean);

        } else {
            SubmitItem sumbitIetm = (SubmitItem) convertView;
            sumbitIetm.setData(getCount() - position);
        }

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position > getCount() - 5) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_NOMAL;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }


    public void setData(ArrayList<Parcelable> list) {
        mDataList = list;
    }
}
