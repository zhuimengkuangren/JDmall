package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.shopping.app.jdmall.bean.BannerBean;
import com.shopping.app.jdmall.widget.HomeHeadBanner;
import com.shopping.app.jdmall.widget.PanBannerView;
import com.shopping.app.jdmall.widget.PanCategroy;
import com.shopping.app.jdmall.widget.PanImageView;
import com.shopping.app.jdmall.widget.PanMarqueeView;
import com.shopping.app.jdmall.widget.RecommendScrollView;
import com.shopping.app.jdmall.widget.SecondKill;

import java.util.List;

/**
 * Created by panpan on 2017/4/6.
 */

public class HomeAdapter extends BaseListLoadAdapter {

    private static final int ITEM_TYPE_BANNER01 = 0;
    private static final int ITEM_TYPE_CATEGROY = 1;
    private static final int ITEM_TYPE_MARQUEE = 2;
    private static final int ITEM_TYPE_SCOENDKILL = 3;
    private static final int ITEM_TYPE_IMAGE01 = 4;
    private static final int ITEM_TYPE_RecommendScrollView = 5;
    private static final int ITEM_TYPE_IMAGE02 = 6;
    private static final int ITEM_TYPE_BANNER02 = 7;
    private static final int ITEM_TYPE_NORMAL = 9;
    private BannerBean mBean;
    private List mList;

    public HomeAdapter(Context context, List list) {
        super(context, list);
    }

    public HomeAdapter(Context context, List list, BannerBean bean) {
        super(context, list);
        mList = list;
        mBean = bean;
    }

    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size() + 1;
        }
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 9;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return ITEM_TYPE_BANNER01; //第0个item为轮播图
        } else if (position == 1) {
            return ITEM_TYPE_CATEGROY;
        } else if (position == 2) {
            return ITEM_TYPE_MARQUEE;
        } else if (position == 3) {
            return ITEM_TYPE_SCOENDKILL;
        } else if (position == 4) {
            return ITEM_TYPE_IMAGE01;
        } else if (position == 5) {
            return ITEM_TYPE_RecommendScrollView;
        } else if (position == 6) {
            return ITEM_TYPE_IMAGE02;
        } else if (position == 7) {
            return ITEM_TYPE_BANNER02;
        }
        return ITEM_TYPE_NORMAL;
    }



    @Override
    protected View onCreateView(int position) {

        if (getItemViewType(position) == ITEM_TYPE_BANNER01) {
            return new HomeHeadBanner(getContext());    //创建轮播图
        } else if (getItemViewType(position) == ITEM_TYPE_CATEGROY) {
            return new PanCategroy(getContext());
        } else if (getItemViewType(position) == ITEM_TYPE_MARQUEE) {
            return new PanMarqueeView(getContext());
        } else if (getItemViewType(position) == ITEM_TYPE_SCOENDKILL) {
            return new SecondKill(getContext());
        } else if (getItemViewType(position) == ITEM_TYPE_IMAGE01) {
            return new PanImageView(getContext());
        } else if (getItemViewType(position) == ITEM_TYPE_RecommendScrollView) {
            return new RecommendScrollView(getContext());
        } else if (getItemViewType(position) == ITEM_TYPE_IMAGE02) {
            TextView textView = new TextView(getContext());
            //textView.setText("哈哈");
            return textView;
        } else if (getItemViewType(position) == ITEM_TYPE_BANNER02) {
            return new PanBannerView(getContext());
        }
        return oncreateNormalView();
    }

    @Override
    protected View oncreateNormalView() {
        TextView textView = new TextView(getContext());
        textView.setText("嘿嘿");
        return textView;
    }

    @Override
    protected void onBindView(int position, View convertView) {
        if (getItemViewType(position) == ITEM_TYPE_NORMAL) {
            onBindNormalView(position, convertView);
        }
        if (getItemViewType(position) == ITEM_TYPE_BANNER01) {
            HomeHeadBanner homeHeadBanner = (HomeHeadBanner) convertView;
            homeHeadBanner.bindView(mBean);
        }
    }

    @Override
    protected void onBindNormalView(int position, View convertView) {

    }
}
