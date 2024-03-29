package com.qdaily.ui.Views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.droison.util.DpSpDip2Px;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.qdaily.entity.PPT;
import com.qdaily.ui.R;
import com.qdaily.util.ImageScale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by song on 9/2/14.
 */
public class PPTView {
    private ViewPager viewPager;
    private List<ImageView> imageViews;
    private List<View> dots;
    private int currentItem = 0;
    private ScheduledExecutorService scheduledExecutorService;
    private LinearLayout dot_layout;
    private DisplayImageOptions pptOptions;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private ImageLoadingListener displayListener = new DisplayListener();
    private View convertView;
    private RelativeLayout ppt_layout;

    private TextView ppt_tag, ppt_title, ppt_icon_text;
    private ImageView ppt_icon_img;

    private List<PPT> pbs;

    public PPTView(List<PPT> pbs, Activity mContext, DisplayImageOptions pptOptions) {

        this.pbs = pbs;
        LayoutInflater layoutInflator = LayoutInflater.from(mContext);
        convertView = layoutInflator.inflate(R.layout.home_ppt, null);
        viewPager = (ViewPager)convertView.findViewById(R.id.ppt_viewpager);
        ppt_layout = (RelativeLayout) convertView.findViewById(R.id.ppt_layout);
        ppt_layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ImageScale.getTab1PPTHeigh(mContext)));
        dot_layout = (LinearLayout) convertView.findViewById(R.id.dot_layout);
        this.pptOptions = pptOptions;
        imageViews = new ArrayList<ImageView>();
        dots = new ArrayList<View>();

        ppt_tag = (TextView) convertView.findViewById(R.id.ppt_tag);
        ppt_title = (TextView) convertView.findViewById(R.id.ppt_title);
        ppt_icon_text = (TextView) convertView.findViewById(R.id.ppt_icon_text);
        ppt_icon_img = (ImageView) convertView.findViewById(R.id.ppt_icon_img);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(10, 10);
        lp.setMargins(5, 0, 5, 0);

        for (int i = 0; i < pbs.size(); i++) {
            final PPT pb = pbs.get(i);
            final ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(R.drawable.image_default);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageLoader.displayImage("http://" + pb.getBanner(), imageView, pptOptions, displayListener);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {

                }
            });
            imageViews.add(imageView);

            View view = new View(mContext);
            if (i == 0) {
                view.setBackgroundResource(R.drawable.dot_focused);
            } else {
                view.setBackgroundResource(R.drawable.dot_normal);
            }
            view.setLayoutParams(lp);
            dot_layout.addView(view);
            dots.add(view);
        }

        viewPager.setAdapter(new MyAdapter());
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }

    public void refreshPPTView(List<PPT> pbs){

    }

    public View getPPTView(){
        return convertView;
    }

    public void startScheduled(){
        if (scheduledExecutorService == null)
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2, TimeUnit.SECONDS);
    }

    public void stopScheduled(){
        if (scheduledExecutorService != null)
            scheduledExecutorService.shutdown();
    }

    private class ScrollTask implements Runnable {

        public void run() {
            synchronized (viewPager) {
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget();
            }
        }

    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            viewPager.setCurrentItem(currentItem);
        };
    };

    class DisplayListener extends SimpleImageLoadingListener {

        final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                imageView.setAdjustViewBounds(true);
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }

    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        private int oldPosition = 0;

        /**
         * This method will be invoked when a new page becomes selected.
         * position: Position index of the new selected page.
         */
        public void onPageSelected(int position) {
            currentItem = position;
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
            PPT ppt = pbs.get(position);
            if (ppt.getSuper_tag()!=null&&ppt.getSuper_tag().length()>0){
                ppt_tag.setVisibility(View.VISIBLE);
                ppt_tag.setText(ppt.getSuper_tag());
            }else {
                ppt_tag.setVisibility(View.GONE);
            }
            ppt_title.setText(ppt.getTitle());
            ppt_icon_text.setText(ppt.getCategory_title());
            imageLoader.displayImage("http://" + ppt.getCategory_icon(), ppt_icon_img, pptOptions);

        }

        public void onPageScrollStateChanged(int arg0) {

        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }
    }

    /**
     *
     * @author Administrator
     *
     */
    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(imageViews.get(arg1));
            return imageViews.get(arg1);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }
    }
}
