package com.qdaily.ui.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qdaily.entity.Research.QuestionCommentBase;
import com.qdaily.ui.R;
import com.qdaily.util.ImageFetcher;

import java.util.List;

/**
 * Created by song on 9/6/14.
 */
public class QuestionCommentAdapter extends BaseAdapter {
    private ImageFetcher mImageFetcher;
    private Activity mActivity;
    private List<QuestionCommentBase> mInfos;
    private LayoutInflater layoutInflator;

    public QuestionCommentAdapter(Activity activity,List<QuestionCommentBase> mInfos, ImageFetcher mImageFetcher) {
        this.mActivity = activity;
        this.mInfos = mInfos;
        this.mImageFetcher = mImageFetcher;
        layoutInflator = LayoutInflater.from(activity);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final QuestionCommentBase qcb = mInfos.get(position);

        convertView = layoutInflator.inflate(R.layout.question_comment_item, null);
        ImageView question_comment_headimg = (ImageView) convertView.findViewById(R.id.question_comment_headimg);
        TextView  question_comment_username = (TextView) convertView.findViewById(R.id.question_comment_username);
        TextView  question_comment_time = (TextView) convertView.findViewById(R.id.question_comment_time);
        TextView  question_comment_content = (TextView) convertView.findViewById(R.id.question_comment_content);

        mImageFetcher.loadImage("http://"+qcb.getAuthor_face(), question_comment_headimg);
        question_comment_username.setText(qcb.getAuthor());
        question_comment_time.setText(qcb.getPublish_time());
        question_comment_content.setText(qcb.getContent());

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

}
