package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.adapter.CommentAdapter;
import com.shopping.app.jdmall.bean.CommentBeans;
import com.shopping.app.jdmall.network.JDRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 2017/4/6.
 */

public class CommentView extends RelativeLayout {
    private static final String TAG = "CommentViewyyy";
    private ListView mLv;
    public List<CommentBeans.CommentBean> mComment;
    public CommentView(Context context) {
        this(context,null);
    }

    public CommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        startLoadData();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_comment, this);
        mLv = (ListView) view.findViewById(R.id.lv_comment);

    }


    public void startLoadData() {
        Call<CommentBeans> beansCall= JDRetrofit.getInstance().getApi().listCommet(1, 1, 10);
        beansCall.enqueue(new Callback<CommentBeans>() {

            @Override
            public void onResponse(Call<CommentBeans> call, Response<CommentBeans> response) {
                mComment = response.body().getComment();
                mLv.setAdapter(new CommentAdapter(getContext(),mComment));
            }

            @Override
            public void onFailure(Call<CommentBeans> call, Throwable t) {
                Toast.makeText(getContext(), "fsadfas", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
