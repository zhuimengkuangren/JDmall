package com.shopping.app.jdmall.ui.activity;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.shopping.app.jdmall.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xuanxuan on 2017/4/8.
 */

public class AddressManagerActivity extends BaseActivity {
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.add_address)
    Button addAddress;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_address_manager;
    }

    @Override
    protected void init() {
        super.init();
        startLoadData();
    }

    private void startLoadData() {

    }

    @OnClick(R.id.add_address)
    public void onViewClicked() {
        finish();
    }
}
