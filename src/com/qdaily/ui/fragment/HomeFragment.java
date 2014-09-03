package com.qdaily.ui.fragment;

import com.alibaba.fastjson.JSON;
import com.dodola.model.DuitangInfo;
import com.dodowaterfall.Helper;
import com.droison.Thread.ThreadExecutor;
import com.droison.constants.Constant;
import com.droison.net.GetData;
import com.huewu.pla.lib.internal.PLA_AbsListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.qdaily.constants.AppConstants;
import com.qdaily.entity.HomeList;
import com.qdaily.ui.Adapter.PicGridAdapter;
import com.qdaily.ui.MainActivity;
import com.qdaily.ui.R;
import com.qdaily.ui.Views.PPTView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;

public class HomeFragment extends Fragment implements XListView.IXListViewListener {

    private MainActivity parentActivity;
    private View view;
    private XListView mAdapterView = null;
    private PicGridAdapter mAdapter = null;
    private int currentPage = 0;
    DisplayImageOptions options;
    private PPTView pptView;
    ContentTask task;
    private boolean isShow;

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
        task = new ContentTask(parentActivity, 2);
        initPPTView();
    }




    private class ContentTask extends AsyncTask<String, Integer, List<DuitangInfo>> {

        private Context mContext;
        private int mType = 1;

        public ContentTask(Context context, int type) {
            super();
            mContext = context;
            mType = type;
        }

        @Override
        protected List<DuitangInfo> doInBackground(String... params) {
            try {
                return parseNewsJSON(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<DuitangInfo> result) {
            if (mType == 1) {

                mAdapter.addItemTop(result);
                mAdapter.notifyDataSetChanged();
                mAdapterView.stopRefresh();

            } else if (mType == 2) {
                mAdapterView.stopLoadMore();
                mAdapter.addItemLast(result);
                mAdapter.notifyDataSetChanged();
            }

        }

        @Override
        protected void onPreExecute() {
        }

        public List<DuitangInfo> parseNewsJSON(String url) throws IOException {
            List<DuitangInfo> duitangs = new ArrayList<DuitangInfo>();
            String json = "";
            if (Helper.checkConnection(mContext)) {
                try {
                    json = Helper.getStringFromUrl(url);

                } catch (IOException e) {
                    Log.e("IOException is : ", e.toString());
                    e.printStackTrace();
                    return duitangs;
                }
            }
            Log.d("MainActiivty", "json:" + json);

            try {
                if (null != json) {
                    JSONObject newsObject = new JSONObject(json);
                    JSONObject jsonObject = newsObject.getJSONObject("data");
                    JSONArray blogsJson = jsonObject.getJSONArray("blogs");

                    for (int i = 0; i < blogsJson.length(); i++) {
                        JSONObject newsInfoLeftObject = blogsJson.getJSONObject(i);
                        DuitangInfo newsInfo1 = new DuitangInfo();
                        newsInfo1.setAlbid(newsInfoLeftObject.isNull("albid") ? "" : newsInfoLeftObject.getString("albid"));
                        newsInfo1.setIsrc(newsInfoLeftObject.isNull("isrc") ? "" : newsInfoLeftObject.getString("isrc"));
                        newsInfo1.setMsg(newsInfoLeftObject.isNull("msg") ? "" : newsInfoLeftObject.getString("msg"));
                        newsInfo1.setHeight(newsInfoLeftObject.getInt("iht"));
                        duitangs.add(newsInfo1);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return duitangs;
        }
    }

    /**
     * 添加内容
     *
     * @param pageindex
     * @param type
     *            1为下拉刷新 2为加载更多
     */
    private void AddItemToContainer(int pageindex, int type) {
        if (task.getStatus() != AsyncTask.Status.RUNNING) {
            String url = "http://www.duitang.com/album/1733789/masn/p/" + pageindex + "/24/";
            Log.d("MainActivity", "current url:" + url);
            ContentTask task = new ContentTask(parentActivity, type);
            task.execute(url);

        }
    }

    private void initPPTView(){
        ThreadExecutor.execute(new GetData(parentActivity, mHandler, AppConstants.HTTPURL.HomeList + 1));
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constant.HANDLER_MESSAGE_NORMAL:
                    String json = (String)msg.obj;
                    if (json!=null&&json.length()>0){
                        HomeList homeList = (HomeList) JSON.parseObject(json, HomeList.class);
                        if (homeList.getPpts()!=null&&homeList.getPpts().size()>0){
                            pptView = new PPTView(homeList.getPpts(), parentActivity, options);
                            mAdapterView.addHeaderView(pptView.getPPTView());
                            pptView.startScheduled();
                        }
                    }
                    mAdapterView.setAdapter(mAdapter);
                    AddItemToContainer(currentPage, 1);
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
        AddItemToContainer(++currentPage, 1);

    }

    @Override
    public void onLoadMore() {
        AddItemToContainer(++currentPage, 2);

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
