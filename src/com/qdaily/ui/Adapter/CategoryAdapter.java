package com.qdaily.ui.Adapter;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodowaterfall.widget.ScaleImageView;
import com.droison.staggeredGridView.StaggeredGridView;
import com.droison.util.ImageUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.qdaily.entity.Article;
import com.qdaily.ui.R;
import com.qdaily.util.ImageFetcher;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by storm on 14-3-26.
 */
public class CategoryAdapter extends BaseAdapter {

    private ImageFetcher mImageFetcher;

    private static final int IMAGE_MAX_HEIGHT = 240;

    private LayoutInflater mLayoutInflater;

    private StaggeredGridView mListView;

    private Drawable mDefaultImageDrawable;

    private Resources mResource;

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private LinkedList<Article> mInfos;
    private Activity mActivity;

    public CategoryAdapter(Activity activity, StaggeredGridView listView,   ImageFetcher mImageFetcher) {
        mResource = activity.getResources();
        mLayoutInflater = LayoutInflater.from(activity);
        mListView = listView;
        mInfos = new LinkedList<Article>();
        this.mActivity = activity;
        this.mImageFetcher = mImageFetcher;
    }

    @Override
    public Object getItem(int position) {
        return mInfos.get(position);
    }

    @Override
    public int getCount() {
        return mInfos.size();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        Article article = mInfos.get(position);

        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(parent.getContext());
            convertView = layoutInflator.inflate(R.layout.category_grid, null);
            holder = new ViewHolder();
            holder.imageView = (ScaleImageView) convertView.findViewById(R.id.iv_normal);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        if (holder.articleId != article.getArticle_id()){
            holder.imageView.setImageWidth(getImageWidth());
            holder.imageView.setImageHeight((int)(getImageWidth()*1.2));
//        imageLoader.displayImage("http://"+magazine.getArticleIndex(), holder.imageView, options, animateFirstListener);
            mImageFetcher.loadImage("http://"+article.getArticleIndex(), holder.imageView);
            holder.articleId = article.getArticle_id();
        }

        return convertView;
    }

    public void addItemLast(List<Article> datas) {
        mInfos.addAll(datas);
    }

    public void addItemTop(List<Article> datas) {
        mInfos.clear();
        addItemLast(datas);
    }

    class ViewHolder {
        ScaleImageView imageView;
Integer articleId;
        TextView caption;
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
                imageView.setAdjustViewBounds(true);
                view.setLayoutParams(new LinearLayout.LayoutParams(getImageWidth(),getImageWidth()*loadedImage.getHeight()/loadedImage.getWidth()));
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}
