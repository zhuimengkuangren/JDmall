package com.shopping.app.jdmall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.shopping.app.jdmall.R;
import com.shopping.app.jdmall.adapter.CategoryListAdapter;
import com.shopping.app.jdmall.adapter.CategoryRithtListAdapter;
import com.shopping.app.jdmall.bean.CategoryItemBean;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/4/5.
 */
public class CategoryView extends LinearLayout {
    private static final String TAG = "CategoryView";
    @BindView(R.id.search_view)
    SearchView mSearchView;
    @BindView(R.id.list_view)
    ListView mListView;
    @BindView(R.id.list_view_right)
    ListView mListViewRight;
    private List<String> mList = new ArrayList<String>();
    private CategoryItemBean mData;
    private CategoryListAdapter mListAdapter;
    private List<CategoryItemBean.CategoryBean> mDataList = new ArrayList<>();
    private CategoryRithtListAdapter mRithtListAdapter;
    private HashMap<String,List<CategoryItemBean.CategoryBean>> mHashMap = new HashMap<>();
    private Set<CategoryItemBean.CategoryBean> secondTitle = new TreeSet<>(new Comparator<CategoryItemBean.CategoryBean>() {
        @Override
        public int compare(CategoryItemBean.CategoryBean o1, CategoryItemBean.CategoryBean o2) {
            return o1.getId() - o2.getId();
        }
    });

    public CategoryView(Context context) {
        this(context, null);
    }

    public CategoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        View.inflate(getContext(), R.layout.view_category, this);
        ButterKnife.bind(this);
        mListView.setDivider(null);
        mListAdapter = new CategoryListAdapter(getContext(), mList);
        mListView.setAdapter(mListAdapter);
        mRithtListAdapter = new CategoryRithtListAdapter(getContext(), mHashMap);
        mListViewRight.setAdapter(mRithtListAdapter);



        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mHashMap.clear();
                secondTitle.clear();
                initSecondData(position);
            }
        });
    }

    private void initSecondData(final int position) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<CategoryItemBean.CategoryBean> category = mData.getCategory();
                for (int i = 0; i < category.size(); i++) {
                    CategoryItemBean.CategoryBean bean = category.get(i);
                    if(bean.getParentId() == (position + 1)) {
                        secondTitle.add(bean);
                    }
                }
                Iterator<CategoryItemBean.CategoryBean> iterator = secondTitle.iterator();
                while (iterator.hasNext()) {
                    CategoryItemBean.CategoryBean bean = iterator.next();
                    int parentId = bean.getId();
                    mDataList = new ArrayList<>();
                    for (int j = 0; j < category.size(); j++) {
                        CategoryItemBean.CategoryBean categoryBean = category.get(j);
                        if(categoryBean.getParentId() == parentId) {

                            mDataList.add(categoryBean);
                        }
                    }
                    mHashMap.put(bean.getName(),mDataList);
                }
                post(new Runnable() {
                    @Override
                    public void run() {
                        mRithtListAdapter.setData(mHashMap);
                        mRithtListAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();


    }


    public void setData(CategoryItemBean data) {
        mData = data;
        List<CategoryItemBean.CategoryBean> beanList = data.getCategory();
        for (int i = 0; i < beanList.size(); i++) {
            CategoryItemBean.CategoryBean bean = beanList.get(i);
            if(bean.getParentId() == 0) {
                mList.add(bean.getName());
            }
        }
        initSecondData(0);
        mListAdapter.notifyDataSetChanged();
    }

}
