package com.shopping.app.jdmall.adapter;

import android.content.Context;

import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.widget.NewProductView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */

public class NewProductAdapter extends BaseLoadMoreAdapter<FindBean> {
    public NewProductAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    protected void onBindNormalViewHolder(ViewHolder viewHolder, int position) {

    }

    @Override
    protected ViewHolder onCreateNormalViewHolder() {
        return new ViewHolder(new NewProductView(getContext()));
    }

}
