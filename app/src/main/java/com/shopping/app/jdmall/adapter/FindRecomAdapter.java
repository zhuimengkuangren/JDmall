package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.view.View;

import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.widget.FindRecomItemView;

import java.util.List;

/**
 * Created by Blacole on 2017/4/6.
 */

public class FindRecomAdapter extends BaseListLoadAdapter {

    private List mList;

    //创建该适配器
    public FindRecomAdapter(Context context, List list) {
        super(context, list);
        mList = list;
        //getList().addAll(mList);


        //Log.d(TAG, "FindRecomAdapter: ++++++++++"+getList().size());


    }

    @Override
    protected View oncreateNormalView() {

        //创建条目视图
        return new FindRecomItemView(getContext());
    }

    @Override
    protected void onBindNormalView(int position, View convertView) {

        ((FindRecomItemView)convertView).bindView((FindBean.ProductListBean) mList.get(position));

    }
}
