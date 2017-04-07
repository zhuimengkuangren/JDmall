package com.shopping.app.jdmall.ui.activity;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.LoginBean;
import com.shopping.app.jdmall.network.JDRetrofit;
import com.shopping.app.jdmall.utils.SPUtils;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author: admin
 * created on: 2017/4/6 8:52
 * description:
 */
public class LoginPageActivity extends BaseActivity {
    private static final String TAG = "LoginPageActivity";
    // private static final String LOGIN_USERID = "login_userId";


    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.fast_register)
    TextView fastRegister;
    @BindView(R.id.forget_passward)
    TextView forgetPassward;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_passward)
    TextView tvPassward;
    private String mUserId;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_loginpage;
    }

    @Override
    protected void init() {

    }


    @OnClick(R.id.login)
    public void onViewClicked() {
        String userName = etNumber.getText().toString().trim();
        String pwd = etPassword.getText().toString().trim();
        Log.d(TAG, "loginRequest: " + userName + pwd);
        if(TextUtils.isEmpty(userName)||TextUtils.isEmpty(pwd)){
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        //post请求
        loginRequest(userName,pwd);


    }



    private void loginRequest(final String username, final String pwd) {
        Call<ResponseBody> call = JDRetrofit.getInstance().getApi().login(username, pwd);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String str = null;
                try {
                    str = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(str, LoginBean.class);

                if("error".equals(loginBean.getResponse())){

                    Toast.makeText(LoginPageActivity.this, loginBean.getError(), Toast.LENGTH_SHORT).show();


                }
                if("login".equals(loginBean.getResponse())){
                    //用户名密码匹配成功
                    //保存用户名
                    SPUtils.setString(LoginPageActivity.this,Constant.USER_NAME,username);
                    //保存当前登录用户信息
                    SPUtils.setString(LoginPageActivity.this, Constant.LOGIN_USERID,loginBean.getUserInfo().getUserid());
                    Toast.makeText(LoginPageActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    finish();
                }






            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

//        Call<LoginBean> call = JDRetrofit.getInstance().getApi().login(username, pwd);
//        call.enqueue(new Callback<LoginBean>() {
//            @Override
//            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
//                // Log.d(TAG, "onResponse: " + userBean.getUserInfo().getUserid());
//                mUserId = response.body().getUserInfo().getUserid();
//
//
//
//            }
//
//            @Override
//            public void onFailure(Call<LoginBean> call, Throwable t) {
//                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
//            }
//        });

    }

}
