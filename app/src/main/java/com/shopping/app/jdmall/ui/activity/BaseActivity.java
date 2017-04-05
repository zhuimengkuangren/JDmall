package com.shopping.app.jdmall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by lzl on 2017/4/4.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);

        //子类初始化
        init(savedInstanceState);//重载
        init();
    }

    /**
     * 子类初始化
     */
    protected void init(){};

    /**
     * 重载
     * @param savedInstanceState
     */
    protected void init(Bundle savedInstanceState){};

    /**
     * 关联布局
     * @return
     */
    abstract protected int getLayoutResId();

    /**
     * activity跳转
     */
    public void navigateTo(Class activity){
        Intent intent = new Intent(this,activity);
        startActivity(intent);
        finish();
    }

}
