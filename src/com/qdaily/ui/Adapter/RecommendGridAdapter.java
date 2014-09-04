package com.qdaily.ui.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodowaterfall.widget.ScaleImageView;
import com.droison.util.ImageUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.qdaily.entity.Category;
import com.qdaily.entity.Magazine;
import com.qdaily.ui.CategoryActivity;
import com.qdaily.ui.R;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import me.maxwin.view.XListView;

public class RecommendGridAdapter extends BaseAdapter {
    private Activity mActivity;
    private LinkedList<Category> mInfos;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public RecommendGridAdapter(Activity activity, DisplayImageOptions options) {
        this.mActivity = activity;
        mInfos = new LinkedList<Category>();
        this.options = options;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        final Category category = mInfos.get(position);

        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(parent.getContext());
            convertView = layoutInflator.inflate(R.layout.recommend_grid, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.news_pic);
            holder.contentView = (TextView) convertView.findViewById(R.id.news_title);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        convertView.setLayoutParams(new GridView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,400));
        holder.imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,200));
        imageLoader.displayImage("http://"+category.getArticleIndex(), holder.imageView, options, animateFirstListener);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toCategoryGridActivity = new Intent(mActivity, CategoryActivity.class);
                toCategoryGridActivity.putExtra("categoryId",category.getCategory_id());
                toCategoryGridActivity.putExtra("title",category.getArticle_title());
                mActivity.startActivity(toCategoryGridActivity);
            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView contentView;
        TextView timeView;
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

    public void addItemLast(List<Category> datas) {
        mInfos.addAll(datas);
    }

    public void addItemTop(List<Category> datas) {
        mInfos.clear();
        addItemLast(datas);
    }

    public int getImageWidth(){
        return ImageUtils.getScreenWidth(mActivity)/2;
    }

    class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
//                imageView.setLayoutParams(new LinearLayout.LayoutParams(getImageWidth(),200));
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }

}
