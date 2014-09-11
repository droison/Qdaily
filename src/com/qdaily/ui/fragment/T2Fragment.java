package com.qdaily.ui.fragment;

import com.alibaba.fastjson.JSON;
import com.droison.PullRefreshAndMoreView.PullToRefreshView;
import com.droison.Thread.ThreadExecutor;
import com.droison.constants.Constant;
import com.droison.net.GetData;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.qdaily.constants.AppConstants;
import com.qdaily.entity.RecommendList;
import com.qdaily.ui.Adapter.T2Adapter;
import com.qdaily.ui.MainActivity;
import com.qdaily.ui.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.Date;

public class T2Fragment extends Fragment implements PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener{
    private int currentPage = 1;
    private MainActivity parentActivity;
    private PullToRefreshView root;
    private GridView recommendView;
    DisplayImageOptions options;
    private int loadDataType; //1为下拉刷新 2为加载更多
    private boolean isRunning;
    private T2Adapter mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        if (getActivity() != null && getActivity() instanceof MainActivity) {
            parentActivity = (MainActivity) getActivity();
        }
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        root = (PullToRefreshView)inflater.inflate(R.layout.maintab_recommend, null);
        setUpListener();
        setUpView();
        initData();
        return root;
	}

    private void setUpListener() {
        root.setOnHeaderRefreshListener(this);
        root.setFooterRefresh(false);
        root.setOnFooterRefreshListener(this);
        root.setHeadRefresh(true);

    }

    private void setUpView()
    {
        recommendView = (GridView) root.findViewById(R.id.recommendList);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.maintab3)
                .showImageForEmptyUri(R.drawable.maintab3)
                .showImageOnFail(R.drawable.maintab3)
                .considerExifParams(true)
                .cacheOnDisk(true)
                .cacheInMemory(false)
                .build();
        mAdapter = new T2Adapter(parentActivity, options);
        recommendView.setAdapter(mAdapter);
        loadDataType = 1;
        isRunning = false;
        currentPage = 1;
        initData();
    }

    private void initData(){
        if (!isRunning){
            ThreadExecutor.execute(new GetData(parentActivity, mHandler, AppConstants.HTTPURL.RecommendList + currentPage));
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

                    RecommendList recommendList = JSON.parseObject(json, RecommendList.class);
                    if (recommendList.getCategories()!=null&&recommendList.getCategories().size()>0)
                    {
                        if (loadDataType==1)
                        {
                            mAdapter.addItemTop(recommendList.getCategories());
                            mAdapter.notifyDataSetChanged();
                        }
                        else
                        {
                            mAdapter.addItemLast(recommendList.getCategories());
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
        initData();
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        currentPage = 1;
        loadDataType = 1;
        initData();
    }

    // 此处用户完成“刷新”和“更多”后的头部和尾部的UI变化
    void completeGridRefresh() {
        if (loadDataType == 1) {
            root.onHeaderRefreshComplete("上次刷新时间是:" + new Date().toLocaleString());
        }
        if (loadDataType == 2) {
            root.onFooterRefreshComplete();
        }

    }
}
