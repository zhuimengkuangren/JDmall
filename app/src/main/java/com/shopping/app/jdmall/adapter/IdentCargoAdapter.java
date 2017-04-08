package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.widget.CargoInfoView;

import java.util.List;

/**
 * Created by 龚浩 on 2017/4/8.
 */

public class IdentCargoAdapter extends BaseAdapter {


    private Context mContext;
    private List<FindBean.ProductListBean> mList;

    public IdentCargoAdapter(Context context, List<FindBean.ProductListBean> list) {

        mContext = context;
        mList = list;
    }


    @Override
    public int getCount() {
        return 30;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return new CargoInfoView(mContext);
    }
}
