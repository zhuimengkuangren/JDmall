package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;

import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.widget.CargoBuyInfoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 龚浩 on 2017/4/8.
 */

public class IdentCargoAdapter extends BaseListAdapter<FindBean.ProductListBean> {


    private Context mContext;
    private List<FindBean.ProductListBean> mList;

    public IdentCargoAdapter(Context context, List<FindBean.ProductListBean> list) {
        super(context, list);
        mContext = context;
        mList = list;
    }


    @Override
    protected View onCreateView(int position) {
        return new CargoBuyInfoView(getContext());
    }

    @Override
    protected void onBindView(int position, View convertView) {
        CargoBuyInfoView view = (CargoBuyInfoView) convertView;
        view.setData(mList.get(position));
    }

    public void setData(ArrayList<Parcelable> list) {

    }
}
