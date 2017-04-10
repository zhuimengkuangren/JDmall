package com.shopping.app.jdmall.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.app.Constant;
import com.shopping.app.jdmall.bean.CarInfoBean;
import com.shopping.app.jdmall.bean.FindBean;
import com.shopping.app.jdmall.manager.CarManager;
import com.shopping.app.jdmall.ui.activity.DetailListItemActivity;
import com.shopping.app.jdmall.widget.AddSubView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lzl on 2017/4/6.
 */

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    private static final String TAG = "CarAdapter";

    private final Context mContext;
    private List<CarInfoBean> mCarInfoBeanList;
    private List<FindBean.ProductListBean> mProductList;
    private TextView mCarTotal;
    private CheckBox mCheckboxAll;
    private CheckBox mCbAll;
    private Button mBtnCollection;
    private OnItemClickListener mOnItemClickListener;

    public CarAdapter(Context context, List<CarInfoBean> carInfoBeanList, List<FindBean.ProductListBean> productList, TextView carTotal, CheckBox checkboxAll, CheckBox cbAll, Button btnCollection) {
        mContext = context;
        mCarInfoBeanList = carInfoBeanList;
        mProductList = productList;
        mCarTotal = carTotal;
        mCheckboxAll = checkboxAll;//完成状态的checkbox
        mCbAll = cbAll;//编辑状态的checkbox
        mBtnCollection = btnCollection;//收藏
        showTotalPrice();
        //设置点击事件
        initListener();
        //校验是否全选
        checkAll();

    }

    /**
     * 监听自身的Item的点击事件
     */
    private void initListener() {
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //根据位置找到对应的bean
                CarInfoBean carInfoBean = mCarInfoBeanList.get(position);
                //设置取反
                carInfoBean.setCheck(!carInfoBean.isCheck());
                //刷新状态
                notifyItemChanged(position);
                //校验是否全选
                checkAll();
                //重新计算
                showTotalPrice();
            }
        });

        mCheckboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到状态
                boolean isCheck = mCheckboxAll.isChecked();
                //全选和反选
                checkAllOrNone(isCheck);
                //重新计算
                showTotalPrice();
            }

        });


        mCbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到状态
                boolean isCheck = mCbAll.isChecked();
                //全选和反选
                checkAllOrNone(isCheck);
            }

        });

        mBtnCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCheck = hasSelect();
                if(isCheck){
                    Toast.makeText(mContext, "收藏成功!!O(∩_∩)O~~", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext, "亲,要请勾选您想收藏的商品哟!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //全选或反选子item
    public void checkAllOrNone(boolean isCheck) {

        if (mCarInfoBeanList != null && mCarInfoBeanList.size() > 0) {
            for (int i = 0; i < mCarInfoBeanList.size(); i++) {
                CarInfoBean carInfoBean = mCarInfoBeanList.get(i);
                carInfoBean.setCheck(isCheck);
                notifyItemChanged(i);
            }
            //重新计算
            showTotalPrice();
        }
    }


    public void checkAll() {
        if (mCarInfoBeanList != null && mCarInfoBeanList.size() > 0) {
            for (int i = 0; i < mCarInfoBeanList.size(); i++) {
                CarInfoBean carInfoBean = mCarInfoBeanList.get(i);
                if (!carInfoBean.isCheck()) {
                    //取消全选
                    mCheckboxAll.setChecked(false);
                    mCbAll.setChecked(false);
                    return;
                }
            }
            //勾选全选
            mCheckboxAll.setChecked(true);
            mCbAll.setChecked(true);
        } else {
            mCheckboxAll.setChecked(false);
            mCbAll.setChecked(false);
        }

    }

    public void showTotalPrice() {
        mCarTotal.setText("¥" + getTotalPrice());
    }

    private double getTotalPrice() {
        double totalPrice = 0.0;
        if (mCarInfoBeanList != null && mCarInfoBeanList.size() > 0) {
            for (int i = 0; i < mCarInfoBeanList.size(); i++) {
                CarInfoBean carInfoBean = mCarInfoBeanList.get(i);
                if (carInfoBean.isCheck()) {
                    totalPrice += carInfoBean.getProdNum() * carInfoBean.getProduct().getPrice();
                }
            }
            return totalPrice;
        }
        return totalPrice;
    }


    public void updateData(List<CarInfoBean> carInfoBeanList) {
        mCarInfoBeanList = carInfoBeanList;
        notifyDataSetChanged();
    }

    public void deleteData() {
        if (mCarInfoBeanList != null && mCarInfoBeanList.size() > 0) {
            for (int i = 0; i < mCarInfoBeanList.size(); i++) {
                //删除选中的
                CarInfoBean carInfoBean = mCarInfoBeanList.get(i);
                if (carInfoBean.isCheck()) {
                    //内存中移除
                    mCarInfoBeanList.remove(carInfoBean);

                    //本地移除
                    CarManager.getInstance().delele(carInfoBean);

                    //刷新
                    notifyItemRemoved(i);//移除

                    i--;

                }
            }
        }
    }

    public boolean hasSelect(){
        if (mCarInfoBeanList != null && mCarInfoBeanList.size() > 0) {
            for (int i = 0; i < mCarInfoBeanList.size(); i++) {
                //删除选中的
                CarInfoBean carInfoBean = mCarInfoBeanList.get(i);
                if(carInfoBean.isCheck()){
                    return true;
                }
            }
        }
        return false;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cb_gov)
        CheckBox mCbGov;
        @BindView(R.id.iv_gov)
        ImageView mIvGov;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_color)
        TextView mTvColor;
        @BindView(R.id.tv_size)
        TextView mTvSize;
        @BindView(R.id.tv_limit)
        TextView mTvLimit;
        @BindView(R.id.tv_soh)
        TextView mTvSoh;
        @BindView(R.id.addsubview)
        AddSubView mAddsubview;
        @BindView(R.id.tv_single)
        TextView mTvSingle;
        @BindView(R.id.tv_total)
        TextView mTvTotal;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //设置item的点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(getLayoutPosition());//如果item被点击,viewholder返回对应的条目的位置
                    }
                }
            });


        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_shop_car, null);
        return new ViewHolder(itemView);
    }
    boolean isMin;
    boolean isMax;
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CarInfoBean carInfoBean = mCarInfoBeanList.get(position);
        //勾选状态
        holder.mCbGov.setChecked(carInfoBean.isCheck());//这个地方是设置check不是select!!!


        //图片
        String url = Constant.HOST + carInfoBean.getProduct().getPic();
        Glide.with(mContext).load(url).into(holder.mIvGov);
        holder.mIvGov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转详情
                Intent intent = new Intent(mContext, DetailListItemActivity.class);
                intent.putExtra("values",mProductList.get(position));
                mContext.startActivity(intent);
            }
        });

        //标题
        holder.mTvTitle.setText(carInfoBean.getProduct().getName());

        //限购个数
        holder.mTvLimit.setText("每个ID限购: " + carInfoBean.getProduct().getBuyLimit());


        //颜色和尺寸
        List<CarInfoBean.ProductBean.ProductPropertyBean> productProperty = carInfoBean.getProduct().getProductProperty();
        for (int i = 0; i < productProperty.size(); i++) {
            CarInfoBean.ProductBean.ProductPropertyBean productPropertyBean = productProperty.get(i);
            if ("颜色".equals(productPropertyBean.getK())) {
                String ColorStr = productPropertyBean.getV();
                if("红色".equals(ColorStr)){
                    holder.mTvColor.setTextColor(Color.RED);
                }else{
                    holder.mTvColor.setTextColor(Color.GREEN);
                }
                holder.mTvColor.setText("颜色: " + ColorStr);
            } else if ("尺码".equals(productPropertyBean.getK())) {
                holder.mTvSize.setText("尺码: " + productPropertyBean.getV());
            }
        }

        //库存数量
        holder.mTvSoh.setText("库存数量: " + carInfoBean.getProduct().getNumber());

        //单价
        int singlePrice = carInfoBean.getProduct().getPrice();
        holder.mTvSingle.setText("单价: " + singlePrice);

        //购买数量
        int prodNum = carInfoBean.getProdNum();
        holder.mAddsubview.setValue(prodNum);

        //总价
        int totalPrice = singlePrice * carInfoBean.getProdNum();
        holder.mTvTotal.setText("总计: " + totalPrice + " ¥");

        //设置最值
        holder.mAddsubview.setMaxValue(carInfoBean.getProduct().getBuyLimit());
        holder.mAddsubview.setMinValue(1);

        //设置监听
        holder.mAddsubview.setonNumberChangeListener(new AddSubView.onNumberChangeListener() {
            @Override
            public void onNumberChange(int value) {
                //同步本地和缓存
                carInfoBean.setProdNum(value);

                CarManager.getInstance().update(carInfoBean);
                //适配器刷新
                notifyItemChanged(position);
                //显示总价格
                showTotalPrice();
            }

        });
    }


    @Override
    public int getItemCount() {
        return mCarInfoBeanList.size();
    }

    /**
     * 监听Item点击
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
