package com.qdaily.ui.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.droison.util.DensityUtils;
import com.droison.util.ImageUtils;
import com.qdaily.entity.Research.QuestionGridBase;
import com.qdaily.ui.R;
import com.qdaily.util.ImageFetcher;

import java.util.List;

import me.maxwin.view.XListView;

/**
 * Created by song on 9/6/14.
 */
public class QuestionGridAdapter extends BaseAdapter {
    private ImageFetcher mImageFetcher;
    private Activity mActivity;
    private List<QuestionGridBase> mInfos;
    private XListView mListView;
    private LayoutInflater layoutInflator;
    private int imgWidth;

    public QuestionGridAdapter(Activity activity, List<QuestionGridBase> mInfos, ImageFetcher mImageFetcher) {
        this.mActivity = activity;
        this.mInfos = mInfos;
        this.mImageFetcher = mImageFetcher;
        layoutInflator = LayoutInflater.from(activity);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final QuestionGridBase qgb = mInfos.get(position);

        convertView = layoutInflator.inflate(R.layout.question_grid_item, null);
        ImageView question_griditem_img = (ImageView) convertView.findViewById(R.id.question_griditem_img);
        TextView  question_griditem_content = (TextView) convertView.findViewById(R.id.question_griditem_content);

        question_griditem_img.setLayoutParams(new RelativeLayout.LayoutParams(getImageWidth(),getImageWidth()));
        mImageFetcher.loadImage("http://" + qgb.getPicture(), question_griditem_img);
        question_griditem_content.setText(qgb.getContent());

        return convertView;
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

    public int getImageWidth(){
        if (imgWidth==0)
        {
            imgWidth = (ImageUtils.getScreenWidth(mActivity)
                    -2 * mActivity.getResources().getDimensionPixelOffset(R.dimen.question_title_marginLeft)
                    -mActivity.getResources().getDimensionPixelOffset(R.dimen.question_grid_hspace)
                    -4 * mActivity.getResources().getDimensionPixelOffset(R.dimen.question_grid_img_margin)
                    )/2;
        }
        return imgWidth;
    }

    public int getItemHeight(){
        return DensityUtils.dip2px(mActivity, 65) + getImageWidth();
    }
}
