package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by panpan on 2017/4/6.
 */

public class PanMarqueeView extends RelativeLayout {
    public PanMarqueeView(Context context) {
        this(context,null);
    }

    public PanMarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.view_marquee,this);
        ButterKnife.bind(this,this);
        init();
    }

    private void init() {

        MarqueeView marqueeView = (MarqueeView) findViewById(R.id.marqueeView);

        List<String> info = new ArrayList<>();
        info.add("您不用讲价。也不用还价;每件都是惊爆价");
        info.add("服装城即将装修，全面升级，全场清仓大处理");
        info.add("回拢资金清仓大甩卖");
        info.add("不是1998,也不是998,只要98");
        info.add("服装城盛大假期欢乐购");
        info.add("运动侣情便装专卖店，提前换季清仓");
        marqueeView.startWithList(info);
    }
}
