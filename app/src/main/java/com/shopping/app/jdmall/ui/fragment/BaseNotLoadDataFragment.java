package com.shopping.app.jdmall.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/4/5.
 */

public abstract class BaseNotLoadDataFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(getResId(),null);
        ButterKnife.bind(this,root);
        init();
        return root;
    }




    protected void init() {
    }

    public abstract int getResId() ;

    public void navigate(Context context ,Class clazz) {
        Intent intent = new Intent(context,clazz);
        context.startActivity(intent);
    }
}
