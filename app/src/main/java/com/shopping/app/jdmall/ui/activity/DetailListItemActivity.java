package com.shopping.app.jdmall.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.ui.fragment.DetailListItemFragment;

/**
 * Created by user on 2017/4/6.
 */

public class DetailListItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item_list);
        init();
    }

    protected void init() {
        //子类初始化
        FragmentManager supportManager = getSupportFragmentManager();
        DetailListItemFragment itemFragment = new DetailListItemFragment();
        FragmentTransaction tr = supportManager.beginTransaction();
        tr.replace(R.id.fl_find_listitem,itemFragment);
        tr.commit();

    }


}
