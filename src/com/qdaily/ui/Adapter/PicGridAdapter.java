package com.qdaily.ui.Adapter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.dodowaterfall.widget.ScaleImageView;
import com.droison.util.ImageUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.qdaily.entity.Magazine;
import com.qdaily.ui.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import me.maxwin.view.XListView;

public class PicGridAdapter extends BaseAdapter {
    private Activity mActivity;
    private LinkedList<Magazine> mInfos;
    private XListView mListView;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public PicGridAdapter(Activity activity, XListView xListView, DisplayImageOptions options) {
        this.mActivity = activity;
        mInfos = new LinkedList<Magazine>();
        mListView = xListView;
        this.options = options;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        Magazine magazine = mInfos.get(position);

        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(parent.getContext());
            convertView = layoutInflator.inflate(R.layout.infos_list, null);
            holder = new ViewHolder();
            holder.imageView = (ScaleImageView) convertView.findViewById(R.id.news_pic);
            holder.contentView = (TextView) convertView.findViewById(R.id.news_title);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.imageView.setImageWidth(getImageWidth());
        imageLoader.displayImage("http://"+magazine.getArticleIndex(), holder.imageView, options, animateFirstListener);
        return convertView;
    }

    class ViewHolder {
        ScaleImageView imageView;
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

    public void addItemLast(List<Magazine> datas) {
        mInfos.addAll(datas);
    }

    public void addItemTop(List<Magazine> datas) {
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
                ScaleImageView imageView = (ScaleImageView) view;
                loadedImage.getWidth();
                imageView.setImageHeight(getImageWidth()*loadedImage.getHeight()/loadedImage.getWidth());
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }

}
