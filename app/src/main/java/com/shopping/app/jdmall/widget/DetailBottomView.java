package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.network.JDRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 2017/4/6.
 */

public class DetailBottomView extends RelativeLayout {



    @BindView(R.id.view_container_scroll)
    LinearLayout mContainerScroll;
    private List<FindBean.ProductListBean> mProductList;
    private static final String TAG = "DetailBottomView";
    public DetailBottomView(Context context) {
        this(context, null);
    }

    public DetailBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        loadData();

    }

    private void loadData() {
        Call<FindBean> beanCall = JDRetrofit.getInstance().getApi().listHotProduct(1, 10, "saleDown");
        beanCall.enqueue(new Callback<FindBean>() {
            @Override
            public void onResponse(Call<FindBean> call, Response<FindBean> response) {
                mProductList = response.body().getProductList();
                bindView(mProductList);
            }

            @Override
            public void onFailure(Call<FindBean> call, Throwable t) {

            }
        });
    }

    private void bindView(List<FindBean.ProductListBean> productList) {
        int count=0;
        if (productList.size()%2==0){
            count=productList.size()/2;
        }else{
            count=productList.size()/2+1;
        }
        int loop=0;
        for (int i = 0; i < count; i++) {
            Log.d(TAG, "bindView: "+productList.get(i).getPic());
            DetailBottomItemView bottomItemView = new DetailBottomItemView(getContext());
            if (loop==productList.size()-1){
                bottomItemView.bindLeftView(productList.get(loop));
                return;
            }else{
                bottomItemView.bindLeftView(productList.get(loop));
                loop+=1;
                bottomItemView.bindRightView(productList.get(loop));
            }
            mContainerScroll.addView(bottomItemView);

        }
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_detail_bottom_viewpager, this);
        final HorizontalScrollView horizotalView = (HorizontalScrollView) view.findViewById(R.id.scroll_view);
        horizotalView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /*horizotalView.setOnScrollChangeListener(new OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

            }
        });*/
       /* horizotalView.setOnDragListener(new OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                Log.d(TAG, "onDrag: "+event.toString());
                return true;
            }
        });*/
        ButterKnife.bind(this, this);
    }
}
