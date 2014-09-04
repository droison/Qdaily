package com.qdaily.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.alibaba.fastjson.JSON;
import com.droison.Thread.ThreadExecutor;
import com.droison.constants.Constant;
import com.droison.net.GetData;
import com.qdaily.BaiduMTJ.BaiduMTJActivity;
import com.qdaily.constants.AppConstants;
import com.qdaily.entity.WebArticle;
import com.qdaily.entity.WebArticleList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 9/4/14.
 */
public class WebInPagerActivity extends BaiduMTJActivity implements ViewPager.OnPageChangeListener{
    ViewPager pager;
    WebPagerAdapter adapter;
    private boolean isRunning;
    private ArrayList<WebArticle> articleArrayList;
    private boolean isFirst;
    private boolean isLast;
    private int curArticleId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webinpager);
        pager = (ViewPager)this.findViewById(R.id.webinpager_pager);
        pager.setOnPageChangeListener(this);
        adapter = new WebPagerAdapter();
        pager.setAdapter(adapter);
        initData();
    }

    private void initData(){
        curArticleId = 0;
        curArticleId = getIntent().getIntExtra("articleId",-1);
        isRunning = false;
        articleArrayList = new ArrayList<WebArticle>();
        loadData(curArticleId);
    }

    private void loadData(int articleId){
        if (articleId==-1)
            return;
        if (articleArrayList.size()!=0
                &&articleId!=articleArrayList.get(0).getArticle_id()
                &&articleId!=articleArrayList.get(articleArrayList.size()-1).getArticle_id())
        { //说明列表存在，通知更新
            updateViewPager(articleId);
            return;
        }
        if (isFirst&&articleId==(articleArrayList.get(0).getArticle_id())){
            return;
        }
        if (isLast&&articleId==(articleArrayList.get(articleArrayList.size()-1).getArticle_id())){
            return;
        }
        if (isLast&&isFirst)
            return;

        if (isRunning)
            return;
        isRunning = true;

        ThreadExecutor.execute(new GetData(this, mHandler, AppConstants.HTTPURL.WebArticle + articleId + "?device_id=1"));
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

                    WebArticleList webArticleList = JSON.parseObject(json, WebArticleList.class);
                    if (webArticleList.getArticle()==null||webArticleList.getUrl()==null)
                        return; //说明出错了

                    if (articleArrayList.size()==0)    //第一次
                        articleArrayList.add(webArticleList.getArticle());

                    if (webArticleList.getBefore_article()==null)
                        isFirst = true;
                    if (webArticleList.getNext_article()==null)
                        isLast = true;

                    if (!isFirst
                            &&webArticleList.getArticle().getArticle_id()==articleArrayList.get(0).getArticle_id()
                            &&webArticleList.getBefore_article()!=null)
                    {
                       articleArrayList.add(0,webArticleList.getBefore_article());
                    }
                    if (!isLast
                            &&webArticleList.getArticle().getArticle_id()==articleArrayList.get(articleArrayList.size()-1).getArticle_id()
                            &&webArticleList.getNext_article()!=null)
                    {
                        articleArrayList.add(webArticleList.getNext_article());
                    }
                    updateViewPager(webArticleList.getArticle().getArticle_id());
                    //完成，通知viewpager更新，有了上一个或者下一个了
                    break;
                default:
                    break;
            }

        }
    };

    //该article前后的文章都搞定了
    private void updateViewPager(int articleId){
        if (articleArrayList==null||articleArrayList.size()==0)
            return;

        if (articleId==curArticleId){
            int i=0;
            for(;i<articleArrayList.size();i++){
                if (articleArrayList.get(i).getArticle_id()==articleId)
                    break;
            }
            List<WebArticle> tempList = new ArrayList<WebArticle>();
            int curPage=0;
            tempList.add(articleArrayList.get(i));
            if (i==0)
            {
                if (!isLast)
                {
                    tempList.add(articleArrayList.get(i+1));
                }
            }
            else if(i==articleArrayList.size()-1)
            {
                if (!isFirst)
                {
                    tempList.add(0, articleArrayList.get(i-1));
                    curPage = 1;
                }
            }
            else {
                tempList.add(0, articleArrayList.get(i-1));
                curPage = 1;
                tempList.add(articleArrayList.get(i+1));
            }
            adapter.setArticles(tempList);
            adapter.notifyDataSetChanged();
            pager.setCurrentItem(curPage);
        }
        else
        {
            loadData(curArticleId);
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1){

    }
    @Override
    public void onPageSelected(int i){
        int temp = adapter.getCurrId(i);
        if (temp!=-1)
            curArticleId = temp;
        loadData(curArticleId);
    }
    @Override
    public void onPageScrollStateChanged(int i){

    }

    private class WebPagerAdapter extends PagerAdapter {

        private LayoutInflater inflater;
        private List<WebArticle> articles;

        public WebPagerAdapter() {
            articles = new ArrayList<WebArticle>();
            inflater = getLayoutInflater();
        }

        public void setArticles(List<WebArticle> articles){
            this.articles = articles;
        }

        public int getCurrId(int page){
            if(page>=0&&page<articles.size()){
                return articles.get(page).getArticle_id();
            }
            return -1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return articles.size();
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            WebView webview = new WebView(WebInPagerActivity.this);
            webview.loadUrl("http://"+articles.get(position).getUrl());
            view.addView(webview, 0);
            return webview;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }
}