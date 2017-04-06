package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.shopping.app.jdmall.bean.CargoBean;
import com.shopping.app.jdmall.ui.activity.DetailListItemActivity;

import java.util.List;

/**
 * Created by 龚浩 on 2017/4/6.
 */

public class pullToRefreshView extends PullToRefreshListView {

    List<CargoBean.ProductListBean> mList;
    private PullToRreshAdapter mAdapter;
    private CargoBean mCargoBean;

    public pullToRefreshView(Context context) {
        this(context, null);
    }

    public pullToRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //设置既能上啦也能下拉
        setMode(Mode.BOTH);
        //设置适配器
        mAdapter = new PullToRreshAdapter();
        setAdapter(mAdapter);
        setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                navigateTo(getContext(),DetailListItemActivity.class,mList.get(position));
            }
        });
    }

    private void navigateTo(Context context, Class aClass, CargoBean.ProductListBean bean) {
        String url = bean.getPic();
        Intent intent = new Intent(context,aClass);
        context.startActivity(intent);
    }

    public void setData(CargoBean cargoBean) {
        mCargoBean = cargoBean;
        mList = mCargoBean.getProductList();
        mAdapter.notifyDataSetChanged();
    }


    class PullToRreshAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            if (mList != null) {
                return mList.size();
            }
            return 0;

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
            if(convertView == null) {
                convertView = new QueryListItem(getContext());
            }
            QueryListItem itemView = (QueryListItem) convertView;
            CargoBean.ProductListBean bean = mList.get(position);
            itemView.setData(bean);
            return itemView;
//            return new QueryListItem(getContext());
        }
    }
}
