package com.shopping.app.jdmall.ui.activity;

import android.util.Log;
import android.widget.Button;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.utils.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xuanxuan on 2017/4/7.
 */

public class SettingActivity extends BaseActivity {
    private static final String TAG = "SettingActivity";
    @BindView(R.id.logout)
    Button logout;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void init() {

    }


    @OnClick(R.id.logout)
    public void onViewClicked() {
       logout();
    }

    private void logout() {
        //拿到本地文件里的userid，置为空，返回登录界面
        String userid = SPUtils.getString(this, Constant.LOGIN_USERID,null);
        Log.d(TAG, "logout: " + userid);
        userid = null;
        SPUtils.setString(this,Constant.LOGIN_USERID,userid);
        finish();
    }
}
