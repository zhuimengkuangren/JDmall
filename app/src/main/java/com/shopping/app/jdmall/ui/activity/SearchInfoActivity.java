package com.shopping.app.jdmall.ui.activity;

import android.content.Context;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.adapter.SearchRecommentAdapter;
import com.shopping.app.jdmall.bean.ResultBean;
import com.shopping.app.jdmall.bean.SearchRecommentBean;
import com.shopping.app.jdmall.network.JDRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 龚浩 on 2017/4/6.
 */

public class SearchInfoActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_voice)
    ImageView mIvVoice;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.list_search)
    ListView mListSearch;
    private List<String> mStringList;
    private Gson mGson;

    @Override
    protected int getLayoutResId() {
        return R.layout.view_search_detail;
    }

   @Override
    protected void init() {
       mGson = new Gson();
       ButterKnife.bind(this, this);
        startLoadDatas();   //从网上请求数据
    }

    private static final String TAG = "SearchInfoActivity";

    private void startLoadDatas() {
        Call<SearchRecommentBean> searchRecommentBeanCall = JDRetrofit.getInstance().getApi().listSearchRecommend();
        searchRecommentBeanCall.enqueue(new Callback<SearchRecommentBean>() {
            @Override
            public void onResponse(Call<SearchRecommentBean> call, Response<SearchRecommentBean> response) {
                SearchRecommentBean recommentBean = response.body();
                mStringList = recommentBean.getSearchKeywords();
                initListView();
            }

            @Override
            public void onFailure(Call<SearchRecommentBean> call, Throwable t) {

            }
        });
    }

    private void initListView() {
        SearchRecommentAdapter searchRecommentAdapter = new SearchRecommentAdapter(this, mStringList);
        mListSearch.setAdapter(searchRecommentAdapter);
        mListSearch.setOnItemClickListener(mOnItemClickListener);
    }


    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(SearchInfoActivity.this, "该商品已下架", Toast.LENGTH_SHORT).show();
        }
    };


    @OnClick({R.id.iv_back, R.id.iv_voice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_voice:
                startVoice();   //开始语音识别
                break;
        }
    }

    private void startVoice() {
        //1.创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(this, null);
        //2.设置accent、 language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        //若要将UI控件用于语义理解，必须添加以下参数设置，设置之后onResult回调返回将是语义理解
        //结果
        // mDialog.setParameter("asr_sch", "1");
        // mDialog.setParameter("nlp_version", "2.0");
        //3.设置回调接口
        mDialog.setListener(mRecognizerDialogListener);
        //4.显示dialog，接收语音输入
        mDialog.show();
    }


    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        /**
         *
         * @param recognizerResult 语音识别结果
         * @param b true表示是标点符号
         */
        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            // Toast.makeText(MainActivity.this, recognizerResult.getResultString(), Toast.LENGTH_LONG).show();
            if (b) {
                return;
            }
            ResultBean resultBean = mGson.fromJson(recognizerResult.getResultString(), ResultBean.class);
            List<ResultBean.WsBean> ws = resultBean.getWs();
            String w = "";
            for (int i = 0; i < ws.size(); i++) {
                List<ResultBean.WsBean.CwBean> cw = ws.get(i).getCw();
                for (int j = 0; j < cw.size(); j++) {
                    w += cw.get(j).getW();
                }
            }

            mEtSearch.setText(w);
            Toast.makeText(SearchInfoActivity.this, w, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SpeechError speechError) {

        }
    };


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
