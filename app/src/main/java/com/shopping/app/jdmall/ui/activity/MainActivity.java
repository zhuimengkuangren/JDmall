package com.shopping.app.jdmall.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.event.FragmentEvent;
import com.shopping.app.jdmall.event.HomeEvent;
import com.shopping.app.jdmall.ui.fragment.CarFragment;
import com.shopping.app.jdmall.ui.fragment.CategoryFragment;
import com.shopping.app.jdmall.ui.fragment.FindFragment;
import com.shopping.app.jdmall.ui.fragment.HomeFragment;
import com.shopping.app.jdmall.ui.fragment.MineFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    //fragment的标记
    public static final String homeFragmentTag = "HomeFragment";
    public static final String categoryFragmentTag = "CategoryFragment";
    public static final String findFragmentTag = "FindFragment";
    public static final String carFragmentTag = "CarFragment";
    public static final String mineFragmentTag = "MineFragment";

    //tabID
    public static final int homeTagId = R.id.tab_home;
    public static final int categoryTagId = R.id.tab_category;
    public static final int findTagId = R.id.tab_find;
    public static final int carTagId = R.id.tab_car;
    public static final int mineTagId = R.id.tab_mine;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;


    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.tab_container)
    RadioGroup mTabContainer;

    private FragmentManager mFragmentManager;
    private long lastBackTime;//最后一次点击back的时间
    private int currentTabId = homeTagId;//当前显示的tab ID,默认初始化为home
    private File file;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        //获得FragmentManager
        mFragmentManager = getSupportFragmentManager();


        //activity初始化时如果不带状态,默认显示homefragment
        if (savedInstanceState == null) {
            Fragment fragment = new HomeFragment();
            mFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment, homeFragmentTag).commit();//replace会重新刷新view视图
        }

        //初始化监听器
        initListener();

        //注册eventbus
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销eventbus
        EventBus.getDefault().unregister(this);
    }

    /**
     * 监听Fragment的事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFragmentEvent(FragmentEvent fragmentEvent) {

    }

    private void initListener() {

        /**
         * 设置底部导航栏的check事件监听
         */
        mTabContainer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchTab(checkedId);
            }
        });
    }

    /**
     * 切换tab
     *
     * @param tabId
     */
    private void switchTab(int tabId) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();

        //找出已经保存的fragment
        Fragment homeFragment = mFragmentManager.findFragmentByTag(homeFragmentTag);
        Fragment categoryFragmen = mFragmentManager.findFragmentByTag(categoryFragmentTag);
        Fragment findFragment = mFragmentManager.findFragmentByTag(findFragmentTag);
        Fragment carFragment = mFragmentManager.findFragmentByTag(carFragmentTag);
        Fragment mineFragment = mFragmentManager.findFragmentByTag(mineFragmentTag);

        //隐藏所有fragment
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (categoryFragmen != null) {
            ft.hide(categoryFragmen);
        }
        if (findFragment != null) {
            ft.hide(findFragment);
        }
        if (carFragment != null) {
            ft.hide(carFragment);
        }
        if (mineFragment != null) {
            ft.hide(mineFragment);
        }

        //显示点击的fragment
        switch (tabId) {
            case homeTagId:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.fragment_container, homeFragment, homeFragmentTag);//添加fragment和标记
                } else {
                    ft.show(homeFragment);//不会重新刷新view视图,保留之前的页面状态
                }
                break;
            case categoryTagId:
                if (categoryFragmen == null) {
                    categoryFragmen = new CategoryFragment();
                    ft.add(R.id.fragment_container, categoryFragmen, categoryFragmentTag);
                } else {
                    ft.show(categoryFragmen);
                }
                break;
            case findTagId:
                if (findFragment == null) {
                    findFragment = new FindFragment();
                    ft.add(R.id.fragment_container, findFragment,
                            findFragmentTag);
                } else {
                    ft.show(findFragment);
                }
                break;
            case carTagId:
                if (carFragment == null) {
                    carFragment = new CarFragment();
                    ft.add(R.id.fragment_container, carFragment, carFragmentTag);
                } else {
                    ft.show(carFragment);
                }
                break;
            case mineTagId:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    ft.add(R.id.fragment_container, mineFragment, mineFragmentTag);
                } else {
                    ft.show(mineFragment);
                }
                break;
            default:
                break;
        }
        ft.commit();
        currentTabId = tabId;
    }

    /**
     * 获得fragment对象,没有则创建和添加
     */
    public Fragment getFragment(String fragmentTab, int tagId) {

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Fragment fragment = mFragmentManager.findFragmentByTag(fragmentTab);
        if (fragment != null) {
            return fragment;
        }

        //创建并添加fragment
        switch (tagId) {
            case homeTagId:
                fragment = new HomeFragment();
                break;
            case categoryTagId:
                fragment = new CategoryFragment();
                break;
            case findTagId:
                fragment = new FindFragment();
                break;
            case carTagId:
                fragment = new CarFragment();
                break;
            case mineTagId:
                fragment = new MineFragment();
                break;
        }
        ft.add(R.id.fragment_container, fragment, fragmentTab);
        ft.commit();
        ft.hide(fragment);

        return fragment;
    }

    /**
     * back处理
     */
    public void onBackPressed() {
        long currentBackTime = System.currentTimeMillis();

        //如果当前tab不是home,则切换到home
        if (currentTabId != R.id.tab_home) {
            mTabContainer.check(R.id.tab_home);
            return;
        }
        //当前tab是home,执行退出逻辑
        if (currentBackTime - lastBackTime > 2000) {
            Toast.makeText(this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
            lastBackTime = currentBackTime;
        } else {
            super.onBackPressed();
        }
    }


    /**
     * 处理fragment重叠问题
     *
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        for (int i = 0; i < mTabContainer.getChildCount(); i++) {
            RadioButton mTab = (RadioButton) mTabContainer.getChildAt(i);
            Fragment fragment = mFragmentManager.findFragmentByTag((String) mTab.getTag());
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            if (fragment != null) {
                if (!mTab.isChecked()) {
                    ft.hide(fragment);
                }
            }
            ft.commit();
        }
    }

    /**
     * 通过EvenBus接收RecommendScrollView传过来的信息,实现打开相机功能
     * MAIN线程模型：不管是哪个线程发布事件，都在主线程执行onMainEvent方法
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEvent(HomeEvent event) {

        switch (event.getEvent()) {
            case "Camera":
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
                //指定拍照完成保存文件到哪里
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                break;
            case "Alpha":

                HomeFragment homeFragment = (HomeFragment)getFragment("HomeFragment", homeTagId);
                homeFragment.stratAlphaAnimation();
                Toast.makeText(this, "上拉刷新了吗", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
