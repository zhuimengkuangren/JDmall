package com.shopping.app.jdmall.adapter;

import android.content.Context;

import com.shopping.app.jdmall.bean.BrandRenBean;
import com.shopping.app.jdmall.widget.BrandView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 */
public class BrandAdapter extends BaseListMoreAdapter<BrandRenBean.BrandBean>{
    public BrandAdapter(Context context, List<BrandRenBean.BrandBean> list) {
        super(context, list);
    }

    @Override
    protected void onBindViewHolder(ViewHolder viewHolder, int position) {
        ((BrandView)viewHolder.mView).bindView(getList().get(position));
    }

    @Override
    protected ViewHolder onCreateViewHolder(int position) {
        return new ViewHolder(new BrandView(getContext()));
    }

}
