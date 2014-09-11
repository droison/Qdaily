package com.qdaily.ui.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.droison.util.ImageUtil;
import com.droison.util.ImageUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.qdaily.entity.Category;
import com.qdaily.ui.CategoryActivity;
import com.qdaily.ui.R;
import com.qdaily.ui.WebInPagerActivity;
import com.qdaily.util.BlurImgCache;
import com.qdaily.util.ImageScale;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import me.maxwin.view.XListView;

public class T2Adapter extends BaseAdapter {
    private Activity mActivity;
    private LinkedList<Category> mInfos;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public T2Adapter(Activity activity, DisplayImageOptions options) {
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
            holder.imageView = (ImageView) convertView.findViewById(R.id.recommend_grid_img);
            holder.logo = (ImageView) convertView.findViewById(R.id.recommend_grid_logo);
            holder.dot = (ImageView) convertView.findViewById(R.id.recommend_grid_dot);
            holder.title = (TextView) convertView.findViewById(R.id.recommend_grid_title);
            holder.text = (TextView) convertView.findViewById(R.id.recommend_grid_text);
            holder.layout = (RelativeLayout) convertView.findViewById(R.id.recommend_grid_layout);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        holder.layout.setLayoutParams(new LinearLayout.LayoutParams(ImageScale.getTab2ImgWidth(mActivity),ImageScale.getTab2ImgHeight(mActivity)));
        Drawable drawable = BlurImgCache.getInstance().getCache("http://"+category.getArticleIndex());
        if (drawable!=null){
            holder.imageView.setImageDrawable(drawable);
        }else {
            imageLoader.displayImage("http://"+category.getArticleIndex(), holder.imageView, options, animateFirstListener);
        }
        Bitmap bm = BlurImgCache.getInstance().getT2IconCache("http://"+category.getIcon());
        if (bm != null){
            holder.logo.setImageBitmap(bm);
        }else {
            holder.logo.setTag("icon");
            imageLoader.displayImage("http://"+category.getIcon(), holder.logo, options,animateFirstListener);
        }
        holder.text.setText(category.getTitle());
        holder.title.setText(category.getArticle_title());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (category.getAuthor()==null||category.getAuthor().length()==0)
                {
                    Intent toCategoryGridActivity = new Intent(mActivity, CategoryActivity.class);
                    toCategoryGridActivity.putExtra("categoryId",category.getCategory_id());
                    toCategoryGridActivity.putExtra("title",category.getArticle_title());
                    mActivity.startActivity(toCategoryGridActivity);
                }
                else
                {
                    Intent toWebInPage = new Intent(mActivity, WebInPagerActivity.class);
                    toWebInPage.putExtra("articleId",category.getArticle_id());
                    mActivity.startActivity(toWebInPage);
                }
            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView title;
        ImageView logo;
        ImageView dot;
        TextView text;
        RelativeLayout layout;
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

    class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (view.getTag()==null){
                if (loadedImage != null) {
                    ImageView imageView = (ImageView) view;
                    Drawable drawable = BlurImgCache.BoxBlurFilter(loadedImage);
                    imageView.setImageDrawable(drawable);
                    BlurImgCache.getInstance().addCache(imageUri, drawable);
                }
            }else {
                ImageView imageView = (ImageView) view;
                imageView.setImageBitmap(loadedImage);
                BlurImgCache.getInstance().addT2IconCache(imageUri, loadedImage);
            }
        }
    }

}
