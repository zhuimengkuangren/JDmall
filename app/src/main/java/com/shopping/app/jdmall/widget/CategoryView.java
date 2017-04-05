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
    private Set<CategoryItemBean.CategoryBean> treeSet = new TreeSet<>(new Comparator<CategoryItemBean.CategoryBean>() {
        @Override
        public int compare(CategoryItemBean.CategoryBean o1, CategoryItemBean.CategoryBean o2) {
            return o1.getId() - o2.getId();
        }
    });
    private Set<Integer> idSet = new TreeSet<>();

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
        mRithtListAdapter = new CategoryRithtListAdapter(getContext(), mDataList);
        mListViewRight.setAdapter(mRithtListAdapter);



        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CategoryListItem childAt = (CategoryListItem) parent.getChildAt(position);
                mRithtListAdapter.notifyDataSetChanged();
            }
        });
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

        mListAdapter.notifyDataSetChanged();
    }

}
