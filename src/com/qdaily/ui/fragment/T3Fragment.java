package com.qdaily.ui.fragment;

import com.alibaba.fastjson.JSON;
import com.droison.Thread.ThreadExecutor;
import com.droison.constants.Constant;
import com.droison.net.GetData;
import com.qdaily.constants.AppConstants;
import com.qdaily.entity.PaperList;
import com.qdaily.ui.Adapter.T3Adapter;
import com.qdaily.ui.MainActivity;
import com.qdaily.ui.R;
import com.qdaily.util.ImageFetcher;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.maxwin.view.XListView;

public class T3Fragment extends Fragment implements XListView.IXListViewListener{
    private MainActivity parentActivity;
    private View view;
    private XListView mAdapterView = null;
    private T3Adapter mAdapter = null;
    private int currentPage;
    private int loadDataType; //1为下拉刷新 2为加载更多
    private boolean isRunning;
    private ImageFetcher mImageFetcher;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        if (getActivity() != null && getActivity() instanceof MainActivity) {
            parentActivity = (MainActivity) getActivity();
        }
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.maintab_research, container, false);
        setUpView();
        initData();
        return view;
	}

    private void setUpView()
    {
        mAdapterView = (XListView) view.findViewById(R.id.researchlist);
        mAdapterView.setPullLoadEnable(true);
        mAdapterView.setXListViewListener(this);

        mImageFetcher = new ImageFetcher(parentActivity, 240);
        mImageFetcher.setLoadingImage(R.drawable.empty_photo);
        mAdapter = new T3Adapter(parentActivity, mAdapterView, mImageFetcher);

        mAdapterView.setAdapter(mAdapter);
        loadDataType = 1;
        isRunning = false;
        currentPage = 1;
        initData();
    }

    private void initData(){
        if (!isRunning){
            ThreadExecutor.execute(new GetData(parentActivity, mHandler, AppConstants.HTTPURL.PaperList + currentPage));
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

                    PaperList paperList = JSON.parseObject(json, PaperList.class);

                    if (paperList.getPapers()!=null&&paperList.getPapers().size()>0)
                    {
                        if (loadDataType==1)
                        {
                            mAdapter.addItemTop(paperList.getPapers());
                            mAdapter.notifyDataSetChanged();
                            mAdapterView.stopRefresh();
                        }
                        else
                        {
                            mAdapterView.stopLoadMore();
                            mAdapter.addItemLast(paperList.getPapers());
                            mAdapter.notifyDataSetChanged();
                        }
                    }else
                    {
                        if (currentPage>1) //表示当前页面已经没有值了
                            currentPage--;
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

    }
}
