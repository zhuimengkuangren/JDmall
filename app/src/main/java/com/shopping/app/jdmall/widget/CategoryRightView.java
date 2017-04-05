package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.shopping.app.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/4/5.
 */

public class CategoryRightView extends LinearLayout {
    @BindView(R.id.tv_categoty)
    TextView mTvCategoty;
    @BindView(R.id.table_layout)
    TableLayout mTableLayout;
    private int mWidth;
    private static final String TAG = "CategoryRightView";

    public CategoryRightView(Context context) {
        this(context, null);
    }

    public CategoryRightView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_category_right, this);
        ButterKnife.bind(this);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mWidth = getMeasuredWidth();
                initData();
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

    }

    private void initData() {

        for (int i = 0; i < 4; i++) {
            TableRow tableRow = new TableRow(getContext());
            TableRow.LayoutParams params = new TableRow.LayoutParams();
            params.width = ( mWidth)/ 3 ;
            params.gravity = Gravity.CENTER;
            for (int j = 0; j < 3; j++) {
                CategoryItemView itemView = new CategoryItemView(getContext());
                itemView.setLayoutParams(params);
                tableRow.addView(itemView);
            }
            mTableLayout.addView(tableRow);
        }
    }
}
