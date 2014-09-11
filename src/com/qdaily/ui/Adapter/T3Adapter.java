package com.qdaily.ui.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodowaterfall.widget.ScaleImageView;
import com.droison.util.ImageUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.qdaily.entity.Paper;
import com.qdaily.ui.QuestionInfoActivity;
import com.qdaily.ui.R;
import com.qdaily.util.ImageFetcher;

import java.util.LinkedList;
import java.util.List;

import me.maxwin.view.XListView;

/**
 * Created by song on 9/4/14.
 */

public class T3Adapter extends BaseAdapter {
    private ImageFetcher mImageFetcher;
    private Activity mActivity;
    private LinkedList<Paper> mInfos;
    private XListView mListView;
    DisplayImageOptions options;

    public T3Adapter(Activity activity, XListView xListView, ImageFetcher mImageFetcher) {
        this.mActivity = activity;
        mInfos = new LinkedList<Paper>();
        mListView = xListView;
        this.mImageFetcher = mImageFetcher;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        final Paper paper = mInfos.get(position);

        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(parent.getContext());
            convertView = layoutInflator.inflate(R.layout.research_item, null);
            holder = new ViewHolder();
            holder.imageView = (ScaleImageView) convertView.findViewById(R.id.research_img);
            holder.title = (TextView) convertView.findViewById(R.id.research_title);
            holder.des = (TextView) convertView.findViewById(R.id.research_des);
            holder.countview = (LinearLayout) convertView.findViewById(R.id.research_item_countview);
            holder.count = (TextView) convertView.findViewById(R.id.research_item_count);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        if (paper.getJoin_count()>0){
            holder.countview.setVisibility(View.VISIBLE);
            holder.count.setText(paper.getJoin_count()+"");
        }else {
            holder.countview.setVisibility(View.GONE);
        }
        holder.title.setText(paper.getTitle());
        holder.des.setText(paper.getDescription());
        holder.imageView.setImageWidth(getImageWidth());
        holder.imageView.setImageHeight((int)(getImageWidth()*0.5));
        mImageFetcher.loadImage("http://"+paper.getPaperShow(), holder.imageView);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toQuestionInfoActivity = new Intent(mActivity, QuestionInfoActivity.class);
                toQuestionInfoActivity.putExtra("questionId", paper.getPaper_id());
                mActivity.startActivity(toQuestionInfoActivity);
            }
        });
        return convertView;
    }

    class ViewHolder {
        ScaleImageView imageView;
        TextView title;
        TextView des;
        LinearLayout countview;
        TextView count;
    }

    @Override
    public int getCount() {
        return mInfos.size();
    }

    @Override
    public Object getItem(int arg0) {
        return mInfos.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    public void addItemLast(List<Paper> datas) {
        mInfos.addAll(datas);
    }

    public void addItemTop(List<Paper> datas) {
        mInfos.clear();
        addItemLast(datas);
    }

    public int getImageWidth(){
        return ImageUtils.getScreenWidth(mActivity);
    }

}
