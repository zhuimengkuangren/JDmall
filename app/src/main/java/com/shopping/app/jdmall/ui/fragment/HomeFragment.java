package com.shopping.app.jdmall.ui.fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.shopping.app.jdmall.bean.CategoryItemBean;
import com.shopping.app.jdmall.network.JDRetrofit;
import com.shopping.app.jdmall.widget.HomePullToRefreshList;
import com.shopping.app.jdmall.widget.HomeSearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 主页fragment
 */
public class HomeFragment extends BaseFragment {

    private CategoryItemBean mCategoryItemBean;
    private HomeSearchView mHomeSearchView;

    @Override
    protected void init() {
        super.init();
        //申请权限
        initPermission();
    }

    private void initPermission() {

        int result = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO);
        if(result == PackageManager.PERMISSION_DENIED) {
          /*  Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivity(intent);
*/
            Toast.makeText(getContext(),"向用户请求权限",Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.RECORD_AUDIO},0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode == 0) {
            if(grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(getContext(),"用户拒绝了权限请求",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void startLoadData() {
        Call<CategoryItemBean> categoryItemBeanCall = JDRetrofit.getInstance().getApi().listCategory();
        categoryItemBeanCall.enqueue(new Callback<CategoryItemBean>() {
            @Override
            public void onResponse(Call<CategoryItemBean> call, Response<CategoryItemBean> response) {
                mCategoryItemBean = response.body();
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<CategoryItemBean> call, Throwable t) {
                onDataLoadedFailed();
            }
        });

    }


    @Override
    protected View onCreateContentView() {
        List<List<CategoryItemBean.CategoryBean>> parent = new ArrayList<>();
        List<CategoryItemBean.CategoryBean> child = null;

        List<CategoryItemBean.CategoryBean> beanList = mCategoryItemBean.getCategory();


        for (int i = 0; i < beanList.size(); i++) {
            if (i % 5 == 0) {
                child = new ArrayList<>();
                parent.add(child);
            }
            child.add(beanList.get(i));
        }

        HomePullToRefreshList homePullToRefreshList = new HomePullToRefreshList(getContext(), parent, 0);

        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.addView(homePullToRefreshList);
        mHomeSearchView = new HomeSearchView(getContext());
        frameLayout.addView(mHomeSearchView);

        return frameLayout;
    }


    //搜索栏实现透明动画功能
    public void stratAlphaAnimation(){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
        mHomeSearchView.startAnimation(alphaAnimation);
    }

}
