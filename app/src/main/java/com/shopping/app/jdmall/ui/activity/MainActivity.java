package com.shopping.app.jdmall.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.ui.fragment.CarFragment;
import com.shopping.app.jdmall.ui.fragment.CategoryFragment;
import com.shopping.app.jdmall.ui.fragment.FindFragment;
import com.shopping.app.jdmall.ui.fragment.HomeFragment;
import com.shopping.app.jdmall.ui.fragment.MineFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    //fragment的标记
    public static final String homeFragmentTag = "HomeFragment";
    public static final String categoryFragmentTag = "CategoryFragment";
    public static final String findFragmentTag = "FindFragment";
    public static final String carFragmentTag = "CarFragment";
    public static final String mineFragmentTag = "MineFragment";

    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.tab_container)
    RadioGroup mTabContainer;

    private FragmentManager mFragmentManager;
    private long lastBackTime;//最后一次点击back的时间
    private int currentTabId = R.id.tab_home;//当前显示的tab ID,默认初始化为home

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
                    .replace(R.id.fragment_container, fragment, homeFragmentTag).commit();//replace会重新刷新
        }

        //初始化监听器
        initListener();


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
     * @param checkedId
     */
    private void switchTab(int checkedId) {
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
        switch (checkedId) {
            case R.id.tab_home:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.fragment_container, homeFragment, homeFragmentTag);//添加fragment和标记
                } else {
                    ft.show(homeFragment);//不会重新刷新,保留之前的页面状态
                }
                break;
            case R.id.tab_category:
                if (categoryFragmen == null) {
                    categoryFragmen = new CategoryFragment();
                    ft.add(R.id.fragment_container, categoryFragmen, categoryFragmentTag);
                } else {
                    ft.show(categoryFragmen);
                }
                break;
            case R.id.tab_find:
                if (findFragment == null) {
                    findFragment = new FindFragment();
                    ft.add(R.id.fragment_container, findFragment,
                            findFragmentTag);
                } else {
                    ft.show(findFragment);
                }
                break;
            case R.id.tab_car:
                if (carFragment == null) {
                    carFragment = new CarFragment();
                    ft.add(R.id.fragment_container, carFragment, carFragmentTag);
                } else {
                    ft.show(carFragment);
                }
                break;
            case R.id.tab_mine:
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
        currentTabId = checkedId;
    }


    /**
     * back处理
     */
    public void onBackPressed() {
        long currentBackTime = System.currentTimeMillis();

        //如果当前tab不是home,则切换到home
        if(currentTabId != R.id.tab_home){
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

}
