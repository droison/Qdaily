package com.qdaily.ui.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dodowaterfall.widget.ScaleImageView;
import com.droison.util.ImageUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.qdaily.entity.Paper;
import com.qdaily.ui.R;
import com.qdaily.util.ImageFetcher;

import java.util.LinkedList;
import java.util.List;

import me.maxwin.view.XListView;

/**
 * Created by song on 9/4/14.
 */

public class ResearchAdapter extends BaseAdapter {
    private ImageFetcher mImageFetcher;
    private Activity mActivity;
    private LinkedList<Paper> mInfos;
    private XListView mListView;
    DisplayImageOptions options;

    public ResearchAdapter(Activity activity, XListView xListView, ImageFetcher mImageFetcher) {
        this.mActivity = activity;
        mInfos = new LinkedList<Paper>();
        mListView = xListView;
        this.mImageFetcher = mImageFetcher;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        Paper paper = mInfos.get(position);

        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(parent.getContext());
            convertView = layoutInflator.inflate(R.layout.research_item, null);
            holder = new ViewHolder();
            holder.imageView = (ScaleImageView) convertView.findViewById(R.id.research_img);
            holder.title = (TextView) convertView.findViewById(R.id.research_title);
            holder.des = (TextView) convertView.findViewById(R.id.research_des);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.title.setText(paper.getTitle());
        holder.des.setText(paper.getDescription());
        holder.imageView.setImageWidth(getImageWidth());
        holder.imageView.setImageHeight((int)(getImageWidth()*0.5));
        mImageFetcher.loadImage("http://"+paper.getPaperShow(), holder.imageView);
        return convertView;
    }

    class ViewHolder {
        ScaleImageView imageView;
        TextView title;
        TextView des;
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
