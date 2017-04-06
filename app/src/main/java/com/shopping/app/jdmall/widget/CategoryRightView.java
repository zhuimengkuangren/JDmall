package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.CategoryItemBean;
import com.shopping.app.jdmall.ui.activity.QueryCargoActivity;

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
    private int mWidthPixels;

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
        mTableLayout.removeAllViews();
        mTvCategoty.setText(category);
        int end = list.size() / 3;
        if(end != 0) {
            end = end + 1;
        }
        for (int i = 0; i < end; i++) {
            TableRow.LayoutParams params = new TableRow.LayoutParams();
            TableRow tableRow = new TableRow(getContext());
            measure(0,0);
            //此处有个bug,无法获取到布局后的宽度,权益之际,写的,要改
            params.width = 173;
            int last = 0 ;
            if(i == end - 1) {
                last = list.size();
            }else {
                last = 3 * (i+1);
            }
            for (int j = 3 * i; j < last; j++) {
                final CategoryItemBean.CategoryBean bean = list.get(j);
                CategoryItemView itemView = new CategoryItemView(getContext());
                itemView.setData(bean);
                itemView.setLayoutParams(params);
                itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       navigateTo(getContext(), QueryCargoActivity.class,bean);
                    }
                });
                tableRow.addView(itemView);
            }
            mTableLayout.addView(tableRow);
        }

    }

    private void navigateTo(Context context, Class aClass, CategoryItemBean.CategoryBean bean) {
        Intent intent = new Intent(context,aClass);
        intent.putExtra("tag",bean.getName());
        context.startActivity(intent);
    }
}
