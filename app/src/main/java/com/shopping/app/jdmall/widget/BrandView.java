package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.BrandRenBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/7.
 */
public class BrandView extends LinearLayout {

    @BindView(R.id.title_text_view)
    TextView mTitleTextView;
    @BindView(R.id.table_layout)
    TableLayout mTableLayout;
    private static final String TAG = "BrandView";

    public BrandView(Context context) {
        this(context, null);
    }

    public BrandView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_brand, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(BrandRenBean.BrandBean brandBean) {
        List<BrandRenBean.BrandBean.ValueBean> value = brandBean.getValue();
        mTitleTextView.setText(brandBean.getKey());
        mTableLayout.removeAllViews();
        TableRow mTableRow = null;
        for (int i = 0; i < value.size(); i++) {
            if (i % 3 == 0) {
                mTableRow = new TableRow(getContext());
                mTableLayout.addView(mTableRow);
            }
            int widthPixels = getResources().getDisplayMetrics().widthPixels - mTableLayout.getPaddingLeft() - mTableLayout.getPaddingRight();
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(getContext(), null);
            layoutParams.width = (int) (widthPixels / 3.0f);
            BrandRenBean.BrandBean.ValueBean valueBean = value.get(i);
            BrandViewInfo brandViewInfo = new BrandViewInfo(getContext());
            brandViewInfo.setLayoutParams(layoutParams);
            brandViewInfo.bindView(valueBean.getName(), valueBean.getPic());
            mTableRow.addView(brandViewInfo);

        }
    }
}