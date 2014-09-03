package com.qdaily.ui.fragment;

import com.alibaba.fastjson.JSON;
import com.dodowaterfall.Helper;
import com.droison.Thread.ThreadExecutor;
import com.droison.constants.Constant;
import com.droison.net.GetData;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.qdaily.constants.AppConstants;
import com.qdaily.entity.HomeList;
import com.qdaily.ui.Adapter.PicGridAdapter;
import com.qdaily.ui.MainActivity;
import com.qdaily.ui.R;
import com.qdaily.ui.Views.PPTView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.maxwin.view.XListView;

public class HomeFragment extends Fragment implements XListView.IXListViewListener {

    private MainActivity parentActivity;
    private View view;
    private XListView mAdapterView = null;
    private PicGridAdapter mAdapter = null;
    private int currentPage;
    DisplayImageOptions options;
    private PPTView pptView;
    private boolean isShow;
    private int loadDataType; //1为下拉刷新 2为加载更多
    private boolean isRunning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null && getActivity() instanceof MainActivity) {
            parentActivity = (MainActivity) getActivity();
        }
        isShow = true;
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.maintab_daily, null);
        setUpView();
        initData();
		return view;
	}

    private void setUpView()
    {
        mAdapterView = (XListView) view.findViewById(R.id.list);
        mAdapterView.setPullLoadEnable(true);
        mAdapterView.setXListViewListener(this);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.maintab3)
                .showImageForEmptyUri(R.drawable.maintab3)
                .showImageOnFail(R.drawable.maintab3)
                .considerExifParams(true)
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        mAdapter = new PicGridAdapter(parentActivity, mAdapterView, options);
        loadDataType = 1;
        isRunning = false;
        currentPage = 1;
        initData();
    }

    private void initData(){
        if (!isRunning){
            ThreadExecutor.execute(new GetData(parentActivity, mHandler, AppConstants.HTTPURL.HomeList + currentPage));
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

                    HomeList homeList = JSON.parseObject(json, HomeList.class);
                    if (currentPage == 1)
                    {
                        if (homeList.getPpts()!=null && homeList.getPpts().size()>0) {
                            if (pptView == null) {
                                pptView = new PPTView(homeList.getPpts(), parentActivity, options);
                                mAdapterView.addHeaderView(pptView.getPPTView());
                                pptView.startScheduled();
                            } else {
                                pptView.refreshPPTView(homeList.getPpts());
                            }
                        }
                        mAdapterView.setAdapter(mAdapter);
                    }
                    if (homeList.getMagazines()!=null&&homeList.getMagazines().size()>0)
                    {
                        if (loadDataType==1)
                        {
                            mAdapter.addItemTop(homeList.getMagazines());
                            mAdapter.notifyDataSetChanged();
                            mAdapterView.stopRefresh();
                        }
                        else
                        {
                            mAdapterView.stopLoadMore();
                            mAdapter.addItemLast(homeList.getMagazines());
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                    break;
                default:
                    break;

            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();

//        mAdapterView.setAdapter(mAdapter);

//        AddItemToContainer(currentPage, 2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        loadDataType = 1;
        initData();
    }

    @Override
    public void onLoadMore() {
        currentPage++;
        loadDataType = 2;
        initData();
    }

    @Override
    public void onScrolling(Boolean isUp){
        if (isUp&&!isShow){
            parentActivity.showBottomAndTitle();
            isShow = true;
        }else if (!isUp&&isShow){
            parentActivity.hideBottomAndTitle();
            isShow = false;
        }
    }
}
