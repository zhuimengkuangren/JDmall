package com.shopping.app.jdmall.ui.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.adapter.FindRecomAdapter;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.network.JDRetrofit;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//Created by Blacole on 2017/4/6.
public class FindRecommandActivity extends BaseActivity {
    private static final String TAG = "FindRecommandActivity";
    @BindView(R.id.gv_recommand)
    GridView mGvRecommand;

    public int mPageCount = 1;
    @BindView(R.id.progress_bar)
    ImageView mProgressBar;
    @BindView(R.id.ib_back)
    ImageButton mIbBack;
    @BindView(R.id.head_image)
    ImageView mHeadImage;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    private List<FindBean.ProductListBean> mProductList;
    private FindRecomAdapter mFindRecomAdapter;

    private static final int ANIMATION_DURATION = 300;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_recommand;
    }

    private static Handler handler = new Handler();


    //复写初始化
    @Override
    protected void init() {
        //需要intent附加信息，然后设置标题栏
        String stringExtra = getIntent().getStringExtra("hotproduct");
        mTvTitle.setText(stringExtra);

        //进度条动画开启
        AnimationDrawable drawable = (AnimationDrawable) mProgressBar.getDrawable();
        drawable.start();

        //进行网络请求
        netWorkRequest();


        mGvRecommand.setOnScrollListener(mScrollListener);

        //界面消失
        mIbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    //启动头部动画
    private void startAnimation() {
        //动画集合
        AnimationSet animationSet = new AnimationSet(false);

        //1.创建平移动画对象
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0,//fromXDelta, toXDelta,
                0, -mHeadImage.getHeight());//fromYDelta, toYDelta);
        //移动起点        移动终点    相对于当前位置增量

        //2.设置动画时长
        translateAnimation.setDuration(ANIMATION_DURATION);//1秒完成动画
        animationSet.addAnimation(translateAnimation);


        //透明度动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(ANIMATION_DURATION);

        animationSet.addAnimation(alphaAnimation);

        //设置动画监听
        animationSet.setAnimationListener(mAnimationListener);
        //启动动画
        mHeadImage.startAnimation(animationSet);
    }

    //动画监听

    private Animation.AnimationListener mAnimationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {

            mHeadImage.setVisibility(View.GONE);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };


    //滑动监听
    private GridView.OnScrollListener mScrollListener = new GridView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

            if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                //如果视图可见，那么就让头部图gone掉
                if (mHeadImage.getVisibility()==View.VISIBLE){
                    startAnimation();
                }


            }

            if (scrollState == SCROLL_STATE_IDLE) {

                //首页在添加了头之后，发现不能滑动到最下边进行加载，仅为item的个数已经改变
                if (view.getLastVisiblePosition() == mFindRecomAdapter.getCount() - 1) {

                    //当获取数据的最后一个的时候，加载更多,再次进行网络访问
                    loadMoreData();


                }
            }

        }


        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    };

    //再次访问网络数据进行加载
    private void loadMoreData() {

        mPageCount++;

        Call<FindBean> listHotProduct = JDRetrofit.getInstance().getApi().listHotProduct(mPageCount, 10, "saleDown");

        listHotProduct.enqueue(new Callback<FindBean>() {
            @Override
            public void onResponse(Call<FindBean> call, Response<FindBean> response) {

                List<FindBean.ProductListBean> productList = response.body().getProductList();

                mProductList.addAll(productList);

                mFindRecomAdapter.notifyDataSetChanged();


                //Log.d(TAG, "onResponse: ++++++++++" + mProductList.size());

                //Log.d(TAG, "onResponse: +++++++++++++" + listCount + "-----" + productList.get(0).getName());
            }

            @Override
            public void onFailure(Call<FindBean> call, Throwable t) {
                Toast.makeText(FindRecommandActivity.this, "网络访问失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //首次访问网络
    private void netWorkRequest() {
        //http://localhost:8080/market/hotproduct?page=1&pageNum=10&orderby=saleDown
        //String hotProduct =  Constant.HOST+"hotproduct?page="+mPageCount+"&pageNum=10&orderby=saleDown";

        Call<FindBean> listHotProduct = JDRetrofit.getInstance().getApi().listHotProduct(mPageCount, 10, "saleDown");

        listHotProduct.enqueue(new Callback<FindBean>() {
            @Override
            public void onResponse(Call<FindBean> call, Response<FindBean> response) {

                mProductList = response.body().getProductList();


                mFindRecomAdapter = new FindRecomAdapter(FindRecommandActivity.this, mProductList);

                mGvRecommand.setAdapter(mFindRecomAdapter);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setVisibility(View.GONE);

                        mGvRecommand.setVisibility(View.VISIBLE);
                    }
                }, 800);



            }

            @Override
            public void onFailure(Call<FindBean> call, Throwable t) {
                Toast.makeText(FindRecommandActivity.this, "网络访问失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
