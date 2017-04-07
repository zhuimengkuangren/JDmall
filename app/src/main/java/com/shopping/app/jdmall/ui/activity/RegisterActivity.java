package com.shopping.app.jdmall.ui.activity;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.bean.RegisterBean;
import com.shopping.app.jdmall.network.JDRetrofit;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xuanxuan on 2017/4/7.
 */

public class RegisterActivity extends BaseActivity {

    private static final String TAG = "RegisterActivity";
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.user_pwd)
    EditText userPwd;
    @BindView(R.id.pwd_again)
    EditText pwdAgain;
    @BindView(R.id.register)
    Button register;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {
        super.init();
    }

    @OnClick(R.id.register)
    public void onViewClicked() {
        String username =  etUsername.getText().toString().trim();
        String pwd = userPwd.getText().toString().trim();
        String ensurePwd = pwdAgain.getText().toString().trim();
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(pwd)||TextUtils.isEmpty(ensurePwd)){
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        }

        if(!pwd.equals(ensurePwd)){
            Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
        }

        register(username,pwd);
    }

    private void register(String username, String pwd) {
        Call<ResponseBody> call = JDRetrofit.getInstance().getApi().register(username, pwd);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Gson gson = new Gson();
                RegisterBean registerBean = null;
                try {
                    registerBean = gson.fromJson(response.body().string(),RegisterBean.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if("register".equals(registerBean.getResponse())){
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
                if("error".equals(registerBean.getResponse())){
                    Toast.makeText(RegisterActivity.this, registerBean.getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}
