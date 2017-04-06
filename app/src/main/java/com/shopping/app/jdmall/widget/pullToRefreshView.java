package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.shopping.app.jdmall.bean.CargoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 龚浩 on 2017/4/6.
 */

public class pullToRefreshView extends PullToRefreshListView {

    List<CargoBean> mList = new ArrayList<>();
    private PullToRreshAdapter mAdapter;

    public pullToRefreshView(Context context) {
        this(context,null);
    }

    public pullToRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mAdapter = new PullToRreshAdapter(getContext(),mList);
        setAdapter(mAdapter);
    }

    public void setData(List<CargoBean> list) {
        mList = list;
        mAdapter.notifyDataSetChanged();
    }


    class PullToRreshAdapter extends BaseAdapter {


        public PullToRreshAdapter(Context context, List<CargoBean> list) {

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
            return new QueryListItem(getContext());
        }
    }
}
