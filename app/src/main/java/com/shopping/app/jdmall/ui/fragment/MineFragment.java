package com.shopping.app.jdmall.ui.fragment;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.UserBean;
import com.shopping.app.jdmall.network.JDRetrofit;
import com.shopping.app.jdmall.ui.activity.AddressManagerActivity;
import com.shopping.app.jdmall.ui.activity.CollectionActivity;
import com.shopping.app.jdmall.ui.activity.LoginPageActivity;
import com.shopping.app.jdmall.ui.activity.OrderListActivity;
import com.shopping.app.jdmall.ui.activity.SettingActivity;
import com.shopping.app.jdmall.ui.activity.UserSettingActivity;
import com.shopping.app.jdmall.utils.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 我的fragment
 */
public class MineFragment extends BaseNotLoadDataFragment {

    private static final int REQUEST_CODE_LOGIN = 100;//登录界面返回
    private static final String TAG = "MineFragment";
    private static final int REQUEST_CODE_ORDERLIST = 200;//订单列表界面返回
    @BindView(R.id.user_icon)
    ImageView userIcon;
    @BindView(R.id.setting_icon)
    ImageView settingIcon;
    Unbinder unbinder;
    @BindView(R.id.login_user)
    TextView loginUser;
    @BindView(R.id.address_manager)
    LinearLayout addressManager;
    @BindView(R.id.order)
    LinearLayout order;
    @BindView(R.id.collection)
    LinearLayout collection;
    @BindView(R.id.shopping_notes)
    LinearLayout shoppingNotes;
    @BindView(R.id.look_step)
    LinearLayout lookStep;
    @BindView(R.id.favor)
    LinearLayout favor;
    @BindView(R.id.order_size)
    TextView orderSize;
    @BindView(R.id.favor_count)
    TextView favorCount;



    @Override
    public int getResId() {
        return R.layout.mine_fragment;
    }

    @Override
    public void init() {
        //请求账户中心的数据
    startLoadData("20428");

    }

    private void startLoadData(String userid) {
        Call<UserBean> call = JDRetrofit.getInstance().getApi().getUserInfo(userid);
        call.enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                UserBean.UserInfoBean userInfoBean = response.body().getUserInfo();
                //更新订单个数
                orderSize.setText(userInfoBean.getOrderCount()+"");
                //更新喜欢个数
                favorCount.setText(userInfoBean.getFavoritesCount()+"");

            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {

            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        String s = SPUtils.getString(getContext(), Constant.LOGIN_USERID, null);
        if (s != null) {
            String username = SPUtils.getString(getContext(), Constant.USER_NAME, null);
            // Log.d(TAG, "init: " + mUsername);
            loginUser.setText("欢迎,用户" + username);
        } else {
            loginUser.setText("登录，注册");
        }
    }

    @OnClick({R.id.user_icon, R.id.setting_icon, R.id.order, R.id.address_manager, R.id.collection,
            R.id.favor })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_icon:
                //判断上一次登录状态
                String s = SPUtils.getString(getContext(), Constant.LOGIN_USERID, null);
                if (s != null) {//如果是已登录状态，跳转到设置个人信息界面
                    Intent intent = new Intent(getContext(), UserSettingActivity.class);
                    startActivity(intent);
                } else {//否则跳转到登录界面
                    Intent intent = new Intent(getContext(), LoginPageActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_LOGIN);

                }
                break;
            case R.id.setting_icon:

                //跳转到设置界面
                toStartActivity(SettingActivity.class);
                break;
            case R.id.order:
                if(SPUtils.getString(getContext(),Constant.LOGIN_USERID,null)!=null){
                    //跳转到订单列表界面
                   toStartActivity(OrderListActivity.class);

                }else{
                    toStartActivity(LoginPageActivity.class);
                }

                break;
            case R.id.address_manager:
                if(SPUtils.getString(getContext(),Constant.LOGIN_USERID,null)!=null){
                    //跳转到地址管理界面
                    toStartActivity(AddressManagerActivity.class);
                }else{
                    toStartActivity(LoginPageActivity.class);
                }

                break;
            case R.id.favor:
                //跳转到喜欢界面
                toStartActivity(CollectionActivity.class);
                break;
            case R.id.collection:
                toStartActivity(CollectionActivity.class);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_LOGIN:
                String username = data.getStringExtra("user_name");
                if (username != null) {
                    //刷新ui
                    loginUser.setText("欢迎，用户" + username);
                }
                Log.d(TAG, "onActivityResult: " + username + "===============");

                break;

        }
    }


}
