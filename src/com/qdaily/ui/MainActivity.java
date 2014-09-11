package com.qdaily.ui;

import com.qdaily.BaiduMTJ.BaiduMTJFragmentActivity;
import com.qdaily.ui.fragment.T1Fragment;
import com.qdaily.ui.fragment.T2Fragment;
import com.qdaily.ui.fragment.T3Fragment;
import com.qdaily.ui.fragment.T4Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * @version V1.0
 * @Description: 主Activity，底部TabHost选项卡
 * @Author bill
 * @date 2014-9-1
 */

public class MainActivity extends BaiduMTJFragmentActivity implements View.OnClickListener {

    LinearLayout tab_layout;
    int CURRENT_TAB = 0; // 设置常量
    T1Fragment t1Fragment;
    T2Fragment t2Fragment;
    T3Fragment t3Fragment;
    T4Fragment t4Fragment;

    android.support.v4.app.FragmentTransaction ft;

    RelativeLayout maintab1_layout, maintab2_layout, maintab3_layout, maintab4_layout;
    ImageView maintab1_img, maintab2_img, maintab3_img, maintab4_img;
    View maintab1_line, maintab2_line, maintab3_line, maintab4_line;
    LinearLayout maintab;
    RelativeLayout maintitle;
    ViewPager mainpager;
    String TAB1 = "tab1";
    String TAB2 = "tab2";
    String TAB3 = "tab3";
    String TAB4 = "tab4";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        selectPage(0);
    }

    public void hideBottomAndTitle(){
        hideTitle();
        hideBottom();
    }

    public void hideBottom(){
        maintab.setVisibility(View.GONE);
    }

    public void hideTitle(){
        maintitle.setVisibility(View.GONE);
    }

    public void showBottomAndTitle(){
        showTitle();
        showBottom();
    }

    public void showBottom(){
        maintab.setVisibility(View.VISIBLE);
    }

    public void showTitle(){
        maintitle.setVisibility(View.VISIBLE);
    }

    /**
     * 找到Tabhost布局
     */
    public void setUpView() {
        tab_layout = (LinearLayout) this.findViewById(R.id.maintab);
        maintab1_layout = (RelativeLayout) this.findViewById(R.id.maintab1_layout);
        maintab2_layout = (RelativeLayout) this.findViewById(R.id.maintab2_layout);
        maintab3_layout = (RelativeLayout) this.findViewById(R.id.maintab3_layout);
        maintab4_layout = (RelativeLayout) this.findViewById(R.id.maintab4_layout);

        maintab1_img = (ImageView) this.findViewById(R.id.maintab1_img);
        maintab2_img = (ImageView) this.findViewById(R.id.maintab2_img);
        maintab3_img = (ImageView) this.findViewById(R.id.maintab3_img);
        maintab4_img = (ImageView) this.findViewById(R.id.maintab4_img);

        maintab1_line = this.findViewById(R.id.maintab1_line);
        maintab2_line = this.findViewById(R.id.maintab2_line);
        maintab3_line = this.findViewById(R.id.maintab3_line);
        maintab4_line = this.findViewById(R.id.maintab4_line);

        maintab1_layout.setOnClickListener(this);
        maintab2_layout.setOnClickListener(this);
        maintab3_layout.setOnClickListener(this);
        maintab4_layout.setOnClickListener(this);
        maintitle = (RelativeLayout)this.findViewById(R.id.maintitle);
        maintab = (LinearLayout)this.findViewById(R.id.maintab);

        FragmentPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        mainpager = (ViewPager)findViewById(R.id.mainpager);
        mainpager.setAdapter(adapter);
        mainpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                CURRENT_TAB = i;
                changeTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.maintab1_layout:
                CURRENT_TAB = 0;
                break;
            case R.id.maintab2_layout:
                CURRENT_TAB = 1;
                break;
            case R.id.maintab3_layout:
                CURRENT_TAB = 2;
                break;
            case R.id.maintab4_layout:
                CURRENT_TAB = 3;
                break;
            default:

                break;
        }
        selectPage(CURRENT_TAB);
    }

    private void selectPage(int curTab) {
        mainpager.setCurrentItem(curTab);
        changeTab(curTab);
    }

    private void changeTab(int curTab) {
        maintab1_img.setImageResource(curTab==0?R.drawable.tab_home_selected:R.drawable.tab_home_normal);
        maintab2_img.setImageResource(curTab==1?R.drawable.tab_feature_selected:R.drawable.tab_feature_normal);
        maintab3_img.setImageResource(curTab==2?R.drawable.tab_qlabs_selected:R.drawable.tab_qlabs_normal);
        maintab4_img.setImageResource(curTab==3?R.drawable.tab_explore_selected:R.drawable.tab_explore_normal);

        maintab1_line.setVisibility(View.GONE);
        maintab2_line.setVisibility(View.GONE);
        maintab3_line.setVisibility(View.GONE);
        maintab4_line.setVisibility(View.GONE);
        switch (curTab)
        {
            case 1:
                maintab2_line.setVisibility(View.VISIBLE);
                break;
            case 2:
                maintab3_line.setVisibility(View.VISIBLE);
                break;
            case 3:
                maintab4_line.setVisibility(View.VISIBLE);
                break;
            default:
                maintab1_line.setVisibility(View.VISIBLE);
                break;
        }
    }

    class MainPagerAdapter extends FragmentPagerAdapter {
        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            // 判断当前
            switch (position){
                case 1:
                    if (t2Fragment == null) {
                        t2Fragment =  new T2Fragment();
                    }
                    fragment = t2Fragment;
                    break;
                case 2:
                    if (t3Fragment == null) {
                        t3Fragment =  new T3Fragment();
                    }
                    fragment = t3Fragment;
                    break;
                case 3:
                    if (t4Fragment == null) {
                        t4Fragment =  new T4Fragment();
                    }
                    fragment = t4Fragment;
                    break;
                default:
                    if (t1Fragment == null) {
                        t1Fragment =  new T1Fragment();
                    }
                    fragment = t1Fragment;
                    break;
            }
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
