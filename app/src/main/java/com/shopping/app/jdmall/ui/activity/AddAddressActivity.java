package com.shopping.app.jdmall.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shopping.app.jdmall.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xuanxuan on 2017/4/9.
 */

public class AddAddressActivity extends BaseActivity {
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.input_name)
    EditText inputName;
    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.address_select)
    LinearLayout addressSelect;
    @BindView(R.id.address_detail)
    EditText addressDetail;
    @BindView(R.id.save)
    Button save;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_add_address;
    }

    @OnClick({R.id.icon_back, R.id.address_select, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.address_select:
                break;
            case R.id.save:
                break;
        }
    }
}
