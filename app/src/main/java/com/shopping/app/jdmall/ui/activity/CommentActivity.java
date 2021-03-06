package com.shopping.app.jdmall.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.widget.CommentView;

/**
 * Created by user on 2017/4/6.
 */

public class CommentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        CommentView view= (CommentView) findViewById(R.id.comment_widget);
        view.setOnClickBackListerner(new CommentView.onClickBackListerner() {
            @Override
            public void onClose() {
                finish();
            }
        });
    }
}
