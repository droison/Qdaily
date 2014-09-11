package com.qdaily.ui.fragment;

import com.alibaba.fastjson.JSON;
import com.droison.Thread.ThreadExecutor;
import com.droison.constants.Constant;
import com.droison.net.GetData;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.qdaily.constants.AppConstants;
import com.qdaily.entity.Category;
import com.qdaily.entity.Tab4List;
import com.qdaily.ui.Adapter.T4CategoryAdapter;
import com.qdaily.ui.Adapter.T4TopAdapter;
import com.qdaily.ui.MainActivity;
import com.qdaily.ui.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

public class T4Fragment extends Fragment implements View.OnClickListener{

    private MainActivity parentActivity;
    private View root;
    DisplayImageOptions options;
    private boolean isRunning;
    private Button tag1,tag2,tag3;
    private RelativeLayout t4_category_title_layout,t4_top_title_layout;
    private LinearLayout t4_category_layout,t4_top_layout;
    private T4CategoryAdapter t4CategoryAdapter;
    private T4TopAdapter t4TopAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null && getActivity() instanceof MainActivity) {
            parentActivity = (MainActivity) getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.maintab_find, null);
        setUpView();
        initData();
        return root;
    }

    private void setUpView()
    {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.maintab3)
                .showImageForEmptyUri(R.drawable.maintab3)
                .showImageOnFail(R.drawable.maintab3)
                .considerExifParams(true)
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        tag1 = (Button)root.findViewById(R.id.t4_tag1);
        tag2 = (Button)root.findViewById(R.id.t4_tag2);
        tag3 = (Button)root.findViewById(R.id.t4_tag3);

        t4_category_title_layout = (RelativeLayout) root.findViewById(R.id.t4_category_title_layout);
        t4_top_title_layout = (RelativeLayout) root.findViewById(R.id.t4_top_title_layout);
        t4_category_layout = (LinearLayout) root.findViewById(R.id.t4_category_layout);
        t4_top_layout = (LinearLayout) root.findViewById(R.id.t4_top_layout);

        isRunning = false;
        initData();
    }

    private void initData(){
        if (!isRunning){
            ThreadExecutor.execute(new GetData(parentActivity, mHandler, AppConstants.HTTPURL.Tab4));
            isRunning = true;
        }
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            isRunning = false;
            switch (msg.what){
                case Constant.HANDLER_MESSAGE_NORMAL:
                    String json = (String)msg.obj;
                    if (json == null || json.length()==0)
                        return;

                    Tab4List tab4List = JSON.parseObject(json, Tab4List.class);
                    List<Tab4List.Tag> tags = tab4List.getTags();
                    if (tags.size()>=3){
                        tag1.setText(tags.get(0).getTitle());
                        tag1.setTag(tags.get(0));
                        tag1.setOnClickListener(T4Fragment.this);
                        tag2.setText(tags.get(1).getTitle());
                        tag2.setTag(tags.get(1));
                        tag2.setOnClickListener(T4Fragment.this);
                        tag3.setText(tags.get(2).getTitle());
                        tag3.setTag(tags.get(2));
                        tag3.setOnClickListener(T4Fragment.this);
                    }
                    List<Category> categories = tab4List.getCategories();
                    if (categories!=null&&categories.size()>0)
                    {
                        t4_category_title_layout.setVisibility(View.VISIBLE);
                        t4CategoryAdapter = new T4CategoryAdapter(parentActivity, categories, options);
                        int count = t4CategoryAdapter.getCount();
                        for (int i = 0; i < count; i++) {
                            View v = t4CategoryAdapter.getDropDownView(i, null, null);
                            t4_category_layout.addView(v);
                        }
                    }
                    List<Tab4List.Top> tops = tab4List.getTops();
                    if (tops!=null&&tops.size()>0){
                        t4_top_title_layout.setVisibility(View.VISIBLE);
                        t4TopAdapter = new T4TopAdapter(parentActivity, tops, options);
                        int count = t4TopAdapter.getCount();
                        for (int i = 0; i < count; i++) {
                            View v = t4TopAdapter.getDropDownView(i, null, null);
                            t4_top_layout.addView(v);
                        }
                    }
                    break;
                default:
                    break;

            }
        }
    };

    @Override
    public void onClick(View view) {
        if (view instanceof Button){
            Tab4List.Tag tag = (Tab4List.Tag)view.getTag();

        }
    }
}
