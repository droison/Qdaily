package com.qdaily.ui;

import com.qdaily.BaiduMTJ.BaiduMTJFragmentActivity;
import com.qdaily.ui.fragment.FindFragment;
import com.qdaily.ui.fragment.HomeFragment;
import com.qdaily.ui.fragment.RecommendFragment;
import com.qdaily.ui.fragment.ResearchFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @version V1.0
 * @Description: 主Activity，底部TabHost选项卡
 * @Author bill
 * @date 2014-9-1
 */

public class MainActivity extends BaiduMTJFragmentActivity implements View.OnClickListener {

    LinearLayout tab_layout;
    int CURRENT_TAB = 0; // 设置常量
    HomeFragment homeFragment;
    RecommendFragment recommendFragment;
    ResearchFragment researchFragment;
    FindFragment findFragment;
    android.support.v4.app.FragmentTransaction ft;
    TextView maintab1, maintab2, maintab3, maintab4;
    LinearLayout maintab;
    RelativeLayout maintitle;
    String TAB1 = "tab1";
    String TAB2 = "tab2";
    String TAB3 = "tab3";
    String TAB4 = "tab4";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        initFragment(0);
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


    // 判断当前
    public void isTabHome() {
        if (homeFragment == null) {
            ft.add(R.id.realtabcontent, new HomeFragment(), TAB1);
        } else {
            ft.attach(homeFragment);
        }
    }

    public void isTabWall() {

        if (recommendFragment == null) {
            ft.add(R.id.realtabcontent, new RecommendFragment(), TAB2);
        } else {
            ft.attach(recommendFragment);
        }
    }

    public void isTabMessage() {

        if (researchFragment == null) {
            ft.add(R.id.realtabcontent, new ResearchFragment(), TAB3);
        } else {
            ft.attach(researchFragment);
        }
    }

    public void isTabMe() {
        if (findFragment == null) {
            ft.add(R.id.realtabcontent, new FindFragment(), TAB4);
        } else {
            ft.attach(findFragment);
        }
    }

    /**
     * 找到Tabhost布局
     */
    public void setUpView() {
        tab_layout = (LinearLayout) this.findViewById(R.id.maintab);
        maintab1 = (TextView) this.findViewById(R.id.maintab1);
        maintab2 = (TextView) this.findViewById(R.id.maintab2);
        maintab3 = (TextView) this.findViewById(R.id.maintab3);
        maintab4 = (TextView) this.findViewById(R.id.maintab4);
        maintab1.setOnClickListener(this);
        maintab2.setOnClickListener(this);
        maintab3.setOnClickListener(this);
        maintab4.setOnClickListener(this);
        maintitle = (RelativeLayout)this.findViewById(R.id.maintitle);
        maintab = (LinearLayout)this.findViewById(R.id.maintab);
    }

    @Override
    public void onClick(View view) {
        initFragment(view.getId());
    }

    private void initFragment(int viewId) {

        /** 碎片管理 */
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        homeFragment = (HomeFragment) fm.findFragmentByTag(TAB1);
        recommendFragment = (RecommendFragment) fm.findFragmentByTag(TAB2);
        researchFragment = (ResearchFragment) fm.findFragmentByTag(TAB3);
        findFragment = (FindFragment) fm.findFragmentByTag(TAB4);
        ft = fm.beginTransaction();

        /** 如果存在Detaches掉 */
        if (homeFragment != null)
            ft.detach(homeFragment);

        /** 如果存在Detaches掉 */
        if (recommendFragment != null)
            ft.detach(recommendFragment);

        /** 如果存在Detaches掉 */
        if (researchFragment != null)
            ft.detach(researchFragment);

        /** 如果存在Detaches掉 */
        if (findFragment != null)
            ft.detach(findFragment);

        switch (viewId) {
            case R.id.maintab1:
                isTabHome();
                CURRENT_TAB = 1;
                break;
            case R.id.maintab2:
                isTabWall();
                CURRENT_TAB = 2;
                break;
            case R.id.maintab3:
                isTabMessage();
                CURRENT_TAB = 3;
                break;
            case R.id.maintab4:
                isTabMe();
                CURRENT_TAB = 4;
                break;
            default:
                switch (CURRENT_TAB) {
                    case 1:
                        isTabHome();
                        break;
                    case 2:
                        isTabWall();
                        break;
                    case 3:
                        isTabMessage();
                        break;
                    case 4:
                        isTabMe();
                        break;
                    default:
                        isTabHome();
                        break;
                }
                break;
        }
        ft.commit();
    }
}
