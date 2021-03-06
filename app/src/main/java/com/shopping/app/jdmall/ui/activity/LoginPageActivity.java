package com.shopping.app.jdmall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.LoginBean;
import com.shopping.app.jdmall.bean.UserData;
import com.shopping.app.jdmall.network.JDRetrofit;
import com.shopping.app.jdmall.utils.SPUtils;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.forget_passward)
    TextView forgetPassward;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_passward)
    TextView tvPassward;
    @BindView(R.id.fast_register)
    TextView fastRegister;
    @BindView(R.id.back)
    ImageView back;
    private String mUserId;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_loginpage;
    }

    @Override
    protected void init() {

    }


    @OnClick({R.id.login, R.id.fast_register,R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                String userName = etNumber.getText().toString().trim();
                String pwd = etPassword.getText().toString().trim();
                Log.d(TAG, "loginRequest: " + userName + pwd);
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //post请求
                loginRequest(userName, pwd);
                break;
            case R.id.fast_register:
                //快速注册
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.back:
                finish();
                break;
        }

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

                if ("error".equals(loginBean.getResponse())) {

                    Toast.makeText(LoginPageActivity.this, loginBean.getError(), Toast.LENGTH_SHORT).show();


                }
                if ("login".equals(loginBean.getResponse())) {


                    //用户名密码匹配成功
                    //保存用户名
                    SPUtils.setString(LoginPageActivity.this, Constant.USER_NAME, username);
                    Intent data = new Intent();
                    data.putExtra("user_name", username);
                    setResult(RESULT_OK, data);
                    //保存当前登录用户信息
                    //持久化存储
                    SPUtils.setString(LoginPageActivity.this, Constant.LOGIN_USERID, loginBean.getUserInfo().getUserid());
                    //存进公共数据类
                    UserData.getInstance().setUserid(loginBean.getUserInfo().getUserid());
                    Toast.makeText(LoginPageActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    finish();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
