package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.adapter.CommentAdapter;
import com.shopping.app.jdmall.bean.CommentBeans;
import com.shopping.app.jdmall.network.JDRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 2017/4/6.
 */

public class CommentView extends RelativeLayout{
    private static final String TAG = "CommentViewyyy";
    @BindView(R.id.iv_click_viewcomment)
    ImageView mIvClickViewcomment;
    @BindView(R.id.lv_comment)
    ListView mLvComment;
    private ListView mLv;
    public List<CommentBeans.CommentBean> mComment;
    private onClickBackListerner mLIsterner;

    public CommentView(Context context) {
        this(context, null);
    }

    public CommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        startLoadData();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_comment, this);
        ButterKnife.bind(this, this);
        mLv = (ListView) view.findViewById(R.id.lv_comment);
    }


    public void startLoadData() {
        Call<CommentBeans> beansCall = JDRetrofit.getInstance().getApi().listCommet(1, 1, 10);
        beansCall.enqueue(new Callback<CommentBeans>() {

            @Override
            public void onResponse(Call<CommentBeans> call, Response<CommentBeans> response) {
                mComment = response.body().getComment();
                mLv.setAdapter(new CommentAdapter(getContext(), mComment));
            }

            @Override
            public void onFailure(Call<CommentBeans> call, Throwable t) {
            }
        });


    }

    @OnClick(R.id.iv_click_viewcomment)
    public void onClick() {
        //让activity finish掉
        mLIsterner.onClose();
    }
    public void setOnClickBackListerner(onClickBackListerner listerner){
        mLIsterner=listerner;
    }
    public interface onClickBackListerner{
        void onClose();
    }
}
