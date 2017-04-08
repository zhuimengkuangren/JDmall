package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.BannerBean;
import com.shopping.app.jdmall.bean.CategoryItemBean;
import com.shopping.app.jdmall.widget.HomeListItemView;
import com.shopping.app.jdmall.widget.PanBannerView;
import com.shopping.app.jdmall.widget.PanCategroyView;
import com.shopping.app.jdmall.widget.PanMarqueeView;
import com.shopping.app.jdmall.widget.RecommendScrollView;
import com.shopping.app.jdmall.widget.SecondKill;

import java.util.List;

/**
 * Created by panpan on 2017/4/6.
 */

public class HomeAdapter extends BaseListLoadAdapter {

    private static final int ITEM_TYPE_CATEGROY = 0;
    private static final int ITEM_TYPE_MARQUEE = 1;
    private static final int ITEM_TYPE_SCOENDKILL = 2;
    private static final int ITEM_TYPE_RecommendScrollView = 3;
    private static final int ITEM_TYPE_BANNER02 = 4;
    private static final int ITEM_TYPE_NORMAL = 5;
    private static final int ITEM_TYPE_IMAGE = 6;

    private BannerBean mBean;
    private Context mContext;
    private List<List<CategoryItemBean.CategoryBean>> mList;

    public HomeAdapter(Context context, List<List<CategoryItemBean.CategoryBean>> list, BannerBean bean) {
        super(context, list);
        mContext = context;
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
        return 7;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE_CATEGROY;
        } else if (position == 1) {
            return ITEM_TYPE_MARQUEE;
        } else if (position == 4) {
            return ITEM_TYPE_SCOENDKILL;
        } else if (position == 5) {
            return ITEM_TYPE_RecommendScrollView;
        } else if (position == 6) {
            return ITEM_TYPE_BANNER02;
        }else if (position == 7){
            return ITEM_TYPE_IMAGE;
        }
        return ITEM_TYPE_NORMAL;
    }

    @Override
    protected View onCreateView(int position) {
        if (getItemViewType(position) == ITEM_TYPE_CATEGROY) {
            return new PanCategroyView(getContext());
        } else if (getItemViewType(position) == ITEM_TYPE_MARQUEE) {
            return new PanMarqueeView(getContext());
        } else if (getItemViewType(position) == ITEM_TYPE_SCOENDKILL) {
            return new SecondKill(getContext());
        } else if (getItemViewType(position) == ITEM_TYPE_RecommendScrollView) {
            return new RecommendScrollView(getContext());
        } else if (getItemViewType(position) == ITEM_TYPE_BANNER02) {
            return new PanBannerView(getContext());
        }else if(getItemViewType(position) == ITEM_TYPE_IMAGE){
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.mipmap.avg);
            return imageView;
        }
        return oncreateNormalView();
    }

    @Override
    protected View oncreateNormalView() {
        return new HomeListItemView(getContext());
    }

    //需要减1 ,不然报越界异常
    @Override
    protected void onBindView(int position, View convertView) {
        if (getItemViewType(position) == ITEM_TYPE_NORMAL) {
            position = position - 1;
            onBindNormalView(position, convertView);
        }
    }

    @Override
    protected void onBindNormalView(int position, View convertView) {
        HomeListItemView homeListItemView = (HomeListItemView) convertView;
        List<CategoryItemBean.CategoryBean> categoryBeen = mList.get(position);
        homeListItemView.bindView(categoryBeen);


    }
}
