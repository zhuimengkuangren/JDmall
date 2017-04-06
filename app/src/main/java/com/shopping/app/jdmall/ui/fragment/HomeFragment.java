package com.shopping.app.jdmall.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

<<<<<<< HEAD
import com.shopping.app.jdmall.bean.BannerBean;
import com.shopping.app.jdmall.network.JDRetrofit;
import com.shopping.app.jdmall.widget.HomeView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

=======
import com.leon.loopviewpagerlib.FunBanner;
import com.shopping.app.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
>>>>>>> c245f824a87cff867fbc9b192572a078207e3881

/**
 * 主页fragment
 */
public class HomeFragment extends homeBaseFragment {


<<<<<<< HEAD

    private BannerBean mBannerBean;

    @Override
    protected void startLoadData() {
        Call<BannerBean> listHome = JDRetrofit.getInstance().getApi().listHome();
        listHome.enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                mBannerBean = response.body();
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {
                onDataLoadedFailed();
            }
        });
    }

    @Override
    protected View onCreateContentView() {
        HomeView homeView = new HomeView(getContext());
        homeView.bindView(mBannerBean);
        return homeView;
=======
    @BindView(R.id.scan)
    ImageButton scan;
    @BindView(R.id.searchBar)
    EditText searchBar;
    @BindView(R.id.search)
    Button search;
    @BindView(R.id.home_input)
    LinearLayout homeInput;
    @BindView(R.id.home_funBanner)
    FunBanner homeFunBanner;
    @BindView(R.id.shopping_spree)
    ImageView shoppingSpree;
    @BindView(R.id.sales_promotion)
    ImageView salesPromotion;
    @BindView(R.id.new_shop)
    ImageView newShop;
    @BindView(R.id.hot_shop)
    ImageView hotShop;
    @BindView(R.id.recommend)
    ImageView recommend;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.search, R.id.shopping_spree, R.id.sales_promotion, R.id.new_shop, R.id.hot_shop, R.id.recommend})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search:
                break;
            case R.id.shopping_spree:
                break;
            case R.id.sales_promotion:
                break;
            case R.id.new_shop:
                break;
            case R.id.hot_shop:
                break;
            case R.id.recommend:
                break;
        }
>>>>>>> c245f824a87cff867fbc9b192572a078207e3881
    }
}
