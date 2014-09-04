package com.qdaily.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.droison.PullRefreshAndMoreView.PullToRefreshView;
import com.droison.Thread.ThreadExecutor;
import com.droison.constants.Constant;
import com.droison.net.GetData;
import com.droison.staggeredGridView.StaggeredGridView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.qdaily.constants.AppConstants;
import com.qdaily.entity.Category;
import com.qdaily.entity.CategoryList;
import com.qdaily.entity.RecommendList;
import com.qdaily.ui.Adapter.CategoryAdapter;
import com.qdaily.util.ImageFetcher;

import java.util.Date;

/**
 * Created by song on 9/3/14.
 */
public class CategoryActivity extends Activity implements PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener{
    private RelativeLayout category_title;
    private PullToRefreshView category_pullview;
    private StaggeredGridView category_gridview;
    private CategoryAdapter mAdapter;
    private Integer categoryId;
    private String title;
    private int currentPage = 1;
    private Boolean isRunning;
    private int loadDataType;
    DisplayImageOptions options;
    private ImageFetcher mImageFetcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setUpView();
        initData();
    }

    private void setUpView(){
        category_title = (RelativeLayout)this.findViewById(R.id.category_title);
        category_pullview = (PullToRefreshView)this.findViewById(R.id.category_pullview);
        category_gridview = (StaggeredGridView)this.findViewById(R.id.category_gridview);
        category_pullview.setOnHeaderRefreshListener(this);
        category_pullview.setOnFooterRefreshListener(this);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.maintab3)
                .showImageForEmptyUri(R.drawable.maintab3)
                .showImageOnFail(R.drawable.maintab3)
                .considerExifParams(true)
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        mImageFetcher = new ImageFetcher(this, 240);
        mImageFetcher.setLoadingImage(R.drawable.empty_photo);
        mAdapter = new CategoryAdapter(this, category_gridview, mImageFetcher);
        category_gridview.setAdapter(mAdapter);
    }

    private void initData(){
        categoryId = getIntent().getIntExtra("categoryId", 25);//默认是全图片
        title = getIntent().getStringExtra("title");
        loadDataType = 1;
        currentPage = 1;
        isRunning = false;
        loadData();

    }
    private void loadData(){
        if (!isRunning){
            ThreadExecutor.execute(new GetData(this, mHandler, AppConstants.HTTPURL.CategoryList + categoryId +"&page=" + currentPage));
            isRunning = true;
        }
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            isRunning = false;
            switch (msg.what){
                case Constant.HANDLER_MESSAGE_NORMAL:
                    String json = (String)msg.obj;
                    if (json == null || json.length()==0)
                        return;

                    CategoryList categoryList = JSON.parseObject(json, CategoryList.class);
                    if (categoryList.getArticles()!=null&&categoryList.getArticles().size()>0)
                    {
                        if (loadDataType==1)
                        {
                            mAdapter.addItemTop(categoryList.getArticles());
                            mAdapter.notifyDataSetChanged();
                        }
                        else
                        {
                            mAdapter.addItemLast(categoryList.getArticles());
                            mAdapter.notifyDataSetChanged();
                        }
                    }else
                    {
                        if (currentPage>1) //表示当前页面已经没有值了
                            currentPage--;
                    }
                    completeGridRefresh();
                    break;
                default:
                    break;

            }
        }
    };

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        currentPage++;
        loadDataType = 2;
        loadData();
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        currentPage = 1;
        loadDataType = 1;
        loadData();
    }

    // 此处用户完成“刷新”和“更多”后的头部和尾部的UI变化
    void completeGridRefresh() {
        if (loadDataType == 1) {
            category_pullview.onHeaderRefreshComplete("上次刷新时间是:" + new Date().toLocaleString());
        }
        if (loadDataType == 2) {
            category_pullview.onFooterRefreshComplete();
        }

    }
}