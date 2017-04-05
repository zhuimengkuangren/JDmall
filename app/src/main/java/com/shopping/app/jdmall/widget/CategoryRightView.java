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
import com.shopping.app.jdmall.bean.CategoryItemBean;

import java.util.ArrayList;
import java.util.List;

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
    private List<String> listCategory = new ArrayList<>();

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
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

    }


    public void setData(String category, List<CategoryItemBean.CategoryBean> list) {
        mTvCategoty.setText(category);

        mTableLayout.removeAllViews();
        TableRow tableRow = new TableRow(getContext());
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.width = (mWidth - getPaddingLeft() - getPaddingRight()) / 3;
        params.gravity = Gravity.CENTER;
        for (int i = 0; i < list.size(); i++) {

            CategoryItemView itemView = new CategoryItemView(getContext());
            itemView.setData(list.get(i));
            itemView.setLayoutParams(params);
            tableRow.addView(itemView);
            if ((i + 1) % 3 == 0) {
                mTableLayout.addView(tableRow);
                tableRow = new TableRow(getContext());
            }
        }
        if(tableRow.getChildCount() != 0) {
            mTableLayout.addView(tableRow);
        }
    }
}
