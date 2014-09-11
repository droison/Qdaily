package com.qdaily.ui.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.qdaily.entity.Category;
import com.qdaily.ui.CategoryActivity;
import com.qdaily.ui.R;
import com.qdaily.util.ImageScale;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by song on 9/10/14.
 */
public class T4CategoryAdapter extends BaseAdapter {
    private Activity mActivity;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private List<Category> categories;

    public T4CategoryAdapter(Activity activity,List<Category> categories, DisplayImageOptions options) {
        this.mActivity = activity;
        this.options = options;
        this.categories = categories;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

         LayoutInflater layoutInflator = LayoutInflater.from(mActivity);
         convertView = layoutInflator.inflate(R.layout.t4_category_item, null);

        convertView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,ImageScale.getTab4CategoryHeight(mActivity)));

       final Category category1 = categories.get(position*2);
        RelativeLayout layout1 = (RelativeLayout) convertView.findViewById(R.id.t4_category_layout1);
         ImageView img1 = (ImageView) convertView.findViewById(R.id.t4_category_img1);
         ImageView icon1 = (ImageView) convertView.findViewById(R.id.t4_category_icon1);
         TextView text1 = (TextView) convertView.findViewById(R.id.t4_category_text1);
         imageLoader.displayImage("http://"+category1.getArticleIndex(), img1, options, animateFirstListener);
         imageLoader.displayImage("http://"+category1.getIcon(), icon1, options, animateFirstListener);
         text1.setText(category1.getTitle());
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toCategoryGridActivity = new Intent(mActivity, CategoryActivity.class);
                toCategoryGridActivity.putExtra("categoryId",category1.getCategory_id());
                toCategoryGridActivity.putExtra("title",category1.getArticle_title());
                mActivity.startActivity(toCategoryGridActivity);
            }
        });
        RelativeLayout layout2 = (RelativeLayout) convertView.findViewById(R.id.t4_category_layout2);
        if (categories.size()>2*position+1){
            final Category category2 = categories.get(position*2+1);
            ImageView img2 = (ImageView) convertView.findViewById(R.id.t4_category_img2);
            ImageView icon2 = (ImageView) convertView.findViewById(R.id.t4_category_icon2);
            TextView text2 = (TextView) convertView.findViewById(R.id.t4_category_text2);
            imageLoader.displayImage("http://"+category2.getArticleIndex(), img2, options, animateFirstListener);
            imageLoader.displayImage("http://"+category2.getIcon(), icon2, options, animateFirstListener);
            text2.setText(category2.getTitle());
            layout2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent toCategoryGridActivity = new Intent(mActivity, CategoryActivity.class);
                    toCategoryGridActivity.putExtra("categoryId",category2.getCategory_id());
                    toCategoryGridActivity.putExtra("title",category2.getArticle_title());
                    mActivity.startActivity(toCategoryGridActivity);
                }
            });
        }else {
            layout2.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return (categories.size()+1)/2;
    }

    @Override
    public Object getItem(int arg0) {
        return categories.get(arg0);
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
