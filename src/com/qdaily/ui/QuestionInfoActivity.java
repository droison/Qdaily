package com.qdaily.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.dodowaterfall.widget.ScaleImageView;
import com.droison.Thread.ThreadExecutor;
import com.droison.constants.Constant;
import com.droison.net.GetData;
import com.droison.util.DensityUtils;
import com.droison.util.ImageUtil;
import com.droison.util.ImageUtils;
import com.qdaily.constants.AppConstants;
import com.qdaily.entity.PaperList;
import com.qdaily.entity.Research.QuestionInfoBase;
import com.qdaily.ui.Adapter.QuestionCommentAdapter;
import com.qdaily.ui.Adapter.QuestionGridAdapter;
import com.qdaily.util.ImageFetcher;

/**
 * Created by song on 9/6/14.
 */
public class QuestionInfoActivity extends Activity {

    ScaleImageView question_img;
    TextView question_title;
    TextView question_des;
    GridView question_gridview;
    TextView question_commenttitle;
    LinearLayout question_commentlist;
    private QuestionCommentAdapter commentAdapter;
    private QuestionGridAdapter gridAdapter;
    private int questionId;
    private QuestionInfoBase qib;
    private ImageFetcher mImageFetcher;
    private ScrollView question_scroll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questioninfo);
        setUpView();
        initData();
    }

    private void setUpView() {
        question_img = (ScaleImageView) this.findViewById(R.id.question_img);
        question_title = (TextView) this.findViewById(R.id.question_title);
        question_des = (TextView) this.findViewById(R.id.question_des);
        question_gridview = (GridView) this.findViewById(R.id.question_gridview);
        question_commenttitle = (TextView) this.findViewById(R.id.question_commenttitle);
        question_commentlist = (LinearLayout) this.findViewById(R.id.question_commentlist);
        question_scroll = (ScrollView) this.findViewById(R.id.question_scroll);
    }

    private void initData() {
        questionId = getIntent().getIntExtra("questionId", -1);

        mImageFetcher = new ImageFetcher(this, 240);
        mImageFetcher.setLoadingImage(R.drawable.empty_photo);
        if (questionId != -1) {
            ThreadExecutor.execute(new GetData(this, mHandler, AppConstants.HTTPURL.QuestionInfo + questionId));
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.HANDLER_MESSAGE_NORMAL:
                    String json = (String) msg.obj;
                    if (json == null || json.length() == 0)
                        return;

                    qib = JSON.parseObject(json, QuestionInfoBase.class);

                    if (qib.isStatus()){
                        question_img.setImageWidth(ImageUtils.getScreenWidth(QuestionInfoActivity.this));
                        mImageFetcher.loadImage("http://"+qib.getPaper().getPaperShow(), question_img);
                        question_title.setText(qib.getPaper().getTitle());
                        question_des.setText(qib.getPaper().getDescription());
                        question_commenttitle.setText(qib.getComments().size()+"¸ö»Ø´ð");
                        if (qib.getPaper().getQuestions()!=null&&qib.getPaper().getQuestions().size()>0){
                            gridAdapter = new QuestionGridAdapter(QuestionInfoActivity.this, qib.getPaper().getQuestions().get(0).getOptions(), mImageFetcher);
                            question_gridview.setAdapter(gridAdapter);
                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, getGridViewHeight());
                            lp.setMargins(getResources().getDimensionPixelOffset(R.dimen.question_title_marginLeft),
                                    getResources().getDimensionPixelOffset(R.dimen.question_grid_maginTop),
                                    getResources().getDimensionPixelOffset(R.dimen.question_title_marginLeft),
                                    0
                                    );
                            question_gridview.setLayoutParams(lp);
                        }
                        commentAdapter = new QuestionCommentAdapter(QuestionInfoActivity.this, qib.getComments(), mImageFetcher);
                        int count = commentAdapter.getCount();
                        for (int i = 0; i < count; i++) {
                            View v = commentAdapter.getDropDownView(i, null, null);
                            question_commentlist.addView(v);
                        }
                        question_scroll.scrollTo(0,0);
                    }

                    break;
                default:
                    break;

            }
        }

        ;
    };

    private int getGridViewHeight(){
        if (gridAdapter==null)
            return 0;

        int itemHeight = gridAdapter.getItemHeight();
        int count = gridAdapter.getCount();
        return itemHeight*((count+1)/2) + (count/2) * this.getResources().getDimensionPixelOffset(R.dimen.question_grid_vspace);
    }


}