package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.ui.activity.FindRecommandActivity;

/**
 * Created by user on 2017/4/5.
 */

public class FindHorizotalScrollView extends RelativeLayout {

    private static final String TAG = "FindHorizotalScrollView";
    private LinearLayout mLinearLayout;
    private ImageView mStar;

    public FindHorizotalScrollView(Context context) {
        this(context, null);
    }

    public FindHorizotalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_horizotal_scrollview_findfragment, this);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.ll_horizotal_find_fragment);


        mStar = (ImageView) view.findViewById(R.id.iv_star);

        mStar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FindRecommandActivity.class);
                intent.putExtra("hotproduct","热门商品");
                getContext().startActivity(intent);
            }
        });

        //mLinearLayout.setOnClickListener(mOnClickListener);



    }


    OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.iv_star:
                    Intent intent = new Intent(getContext(), FindRecommandActivity.class);
                    intent.putExtra("hotproduct","热门商品");
                    getContext().startActivity(intent);
                    break;
            }
        }
    };

}
