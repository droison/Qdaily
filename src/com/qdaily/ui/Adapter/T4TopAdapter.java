package com.qdaily.ui.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.qdaily.ui.R;
import com.qdaily.entity.Tab4List;
import com.qdaily.util.ImageScale;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by song on 9/10/14.
 */
public class T4TopAdapter extends BaseAdapter{
    private Activity mActivity;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private List<Tab4List.Top> tops;

    public T4TopAdapter(Activity activity,List<Tab4List.Top> tops, DisplayImageOptions options) {
        this.mActivity = activity;
        this.options = options;
        this.tops = tops;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflator = LayoutInflater.from(mActivity);

        convertView = layoutInflator.inflate(R.layout.t4_top_item, null);
        convertView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ImageScale.getTab4TopHeight(mActivity)));
        ImageView img1 = (ImageView) convertView.findViewById(R.id.t4_top_img1);
        ImageView img2 = (ImageView) convertView.findViewById(R.id.t4_top_img2);
        ImageView img3 = (ImageView) convertView.findViewById(R.id.t4_top_img3);

        final Tab4List.Top top1 = tops.get(position*3);
        imageLoader.displayImage("http://"+top1.getIcon(), img1, options, animateFirstListener);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        if (tops.size()>3*position+1){
            final Tab4List.Top top2 = tops.get(position*3+1);
            imageLoader.displayImage("http://"+top2.getIcon(), img2, options, animateFirstListener);
            img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }else {
            img2.setVisibility(View.INVISIBLE);
        }

        if (tops.size()>3*position+2){
            final Tab4List.Top top3 = tops.get(position*3+2);
            imageLoader.displayImage("http://"+top3.getIcon(), img3, options, animateFirstListener);
            img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }else {
            img3.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return (tops.size()+2)/3;
    }

    @Override
    public Object getItem(int arg0) {
        return tops.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }


    class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(view, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }

}
