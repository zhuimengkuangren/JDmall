package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by panpan on 2017/4/6.
 */

public class SecondBannerPagerAdapter extends BasePagerAdapter {


    public SecondBannerPagerAdapter(Context context, int[] list) {
        super(context, list);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    View CreatePagerView(ViewGroup container, int position) {

        position = position % mList.length;
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mList[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(imageView);
        return imageView;
    }


}
