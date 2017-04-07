package com.shopping.app.jdmall.ui.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.ui.activity.LoginPageActivity;
import com.shopping.app.jdmall.ui.activity.UserSettingActivity;
import com.shopping.app.jdmall.utils.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的fragment
 */
public class MineFragment extends BaseNotLoadDataFragment {

    private static final int REQUEST_CODE_LOGIN = 100;
    private static final String TAG = "MineFragment";
    @BindView(R.id.user_icon)
    ImageView userIcon;
    @BindView(R.id.setting_icon)
    ImageView settingIcon;
    Unbinder unbinder;
    @BindView(R.id.login_user)
    TextView loginUser;
    private String mUsername;



    @Override
    public int getResId() {
        return R.layout.mine_fragment;
    }

    @Override
    public void init() {
        //判断上次是否登陆过
        String s = SPUtils.getString(getContext(), Constant.LOGIN_USERID, null);
        if (s != null) {
            String username = SPUtils.getString(getContext(),Constant.USER_NAME,null);
            loginUser.setText("欢迎" + username);
        }

    }

    @OnClick({R.id.user_icon, R.id.setting_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_icon:
                //判断上一次登录状态
                String s = SPUtils.getString(getContext(), Constant.LOGIN_USERID, null);
                if(s!=null){//如果是已登录状态，跳转到设置个人信息界面
                    Intent intent = new Intent(getContext(),UserSettingActivity.class);
                    startActivity(intent);
                }else {//否则跳转到登录界面
                    Intent intent = new Intent(getContext(), LoginPageActivity.class);
                    //  startActivityForResult(intent, REQUEST_CODE_LOGIN);
                    startActivity(intent);
                }
                break;
            case R.id.setting_icon:

                break;
        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case REQUEST_CODE_LOGIN:
//                //
//                mUsername = data.getStringExtra("username");
//                //mPwd = data.getStringExtra("pwd");
//                Log.d(TAG, "onActivityResult: " + mUsername + "===============");
//
//                break;
//        }
//    }


}
