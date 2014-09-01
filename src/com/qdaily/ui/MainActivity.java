package com.qdaily.ui;

import com.qdaily.BaiduMTJ.BaiduMTJFragment;
import com.qdaily.BaiduMTJ.BaiduMTJFragmentActivity;
import com.qdaily.ui.R;
import com.qdaily.ui.fragment.Home_Fragment;
import com.qdaily.ui.fragment.Me_Fragment;
import com.qdaily.ui.fragment.Message_Fragment;
import com.qdaily.ui.fragment.Wall_Fragment;
import com.qdaily.util.DummyTabContent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;

/**
 * @Description: 主Activity，底部TabHost选项卡
 * 
 * @Author Hades
 * 
 * @date 2012-10-6
 * 
 * @version V1.0
 */

public class MainActivity extends BaiduMTJFragmentActivity {

	TabHost tabHost;
	TabWidget tabWidget;
	LinearLayout bottom_layout;
	int CURRENT_TAB = 0; // 设置常量
	Home_Fragment homeFragment;
	Wall_Fragment wallFragment;
	Message_Fragment messageFragment;
	Me_Fragment meFragment;
	android.support.v4.app.FragmentTransaction ft;
	RelativeLayout tabIndicator1, tabIndicator2, tabIndicator3, tabIndicator4;
	String TAB1 = "tab1";
	String TAB2 = "tab2";
	String TAB3 = "tab3";
	String TAB4 = "tab4";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findTabView();
		tabHost.setup();

		/** 监听 */
		TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {

				/** 碎片管理 */
				android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
				homeFragment = (Home_Fragment) fm.findFragmentByTag(TAB1);
				wallFragment = (Wall_Fragment) fm.findFragmentByTag(TAB2);
				messageFragment = (Message_Fragment) fm.findFragmentByTag(TAB3);
				meFragment = (Me_Fragment) fm.findFragmentByTag(TAB4);
				ft = fm.beginTransaction();

				/** 如果存在Detaches掉 */
				if (homeFragment != null)
					ft.detach(homeFragment);

				/** 如果存在Detaches掉 */
				if (wallFragment != null)
					ft.detach(wallFragment);

				/** 如果存在Detaches掉 */
				if (messageFragment != null)
					ft.detach(messageFragment);

				/** 如果存在Detaches掉 */
				if (meFragment != null)
					ft.detach(meFragment);

				/** 如果当前选项卡是home */
				if (tabId.equalsIgnoreCase(TAB1)) {
					isTabHome();
					CURRENT_TAB = 1;

					/** 如果当前选项卡是wall */
				} else if (tabId.equalsIgnoreCase(TAB2)) {
					isTabWall();
					CURRENT_TAB = 2;

					/** 如果当前选项卡是message */
				} else if (tabId.equalsIgnoreCase(TAB3)) {
					isTabMessage();
					CURRENT_TAB = 3;

					/** 如果当前选项卡是me */
				} else if (tabId.equalsIgnoreCase(TAB4)) {
					isTabMe();
					CURRENT_TAB = 4;
				} else {
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
						tabWidget.setVisibility(View.GONE);

						break;
					default:
						isTabHome();
						break;
					}

				}
				ft.commit();

			}

		};
		// 设置初始选项卡
		tabHost.setCurrentTab(0);
		tabHost.setOnTabChangedListener(tabChangeListener);
		initTab();
		/** 设置初始化界面 */
		tabHost.setCurrentTab(0);

	}

	// 判断当前
	public void isTabHome() {

		if (homeFragment == null) {
			ft.add(R.id.realtabcontent, new Home_Fragment(), TAB1);
		} else {
			ft.attach(homeFragment);
		}
	}

	public void isTabWall() {

		if (wallFragment == null) {
			ft.add(R.id.realtabcontent, new Wall_Fragment(), TAB2);
		} else {
			ft.attach(wallFragment);
		}
	}

	public void isTabMessage() {

		if (messageFragment == null) {
			ft.add(R.id.realtabcontent, new Message_Fragment(), TAB3);
		} else {
			ft.attach(messageFragment);
		}
	}

	public void isTabMe() {

		if (meFragment == null) {
			ft.add(R.id.realtabcontent, new Me_Fragment(), TAB4);
		} else {
			ft.attach(meFragment);
		}
	}

	/**
	 * 找到Tabhost布局
	 */
	public void findTabView() {

		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabWidget = (TabWidget) findViewById(android.R.id.tabs);
		LinearLayout layout = (LinearLayout) tabHost.getChildAt(0);
		TabWidget tw = (TabWidget) layout.getChildAt(1);

		tabIndicator1 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tab_indicator, tw, false);
		TextView tvTab1 = (TextView) tabIndicator1.getChildAt(1);
		ImageView ivTab1 = (ImageView) tabIndicator1.getChildAt(0);
		ivTab1.setBackgroundResource(R.drawable.maintab1);
		tvTab1.setText(R.string.maintab1);

		tabIndicator2 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tab_indicator, tw, false);
		TextView tvTab2 = (TextView) tabIndicator2.getChildAt(1);
		ImageView ivTab2 = (ImageView) tabIndicator2.getChildAt(0);
		ivTab2.setBackgroundResource(R.drawable.maintab2);
		tvTab2.setText(R.string.maintab2);

		tabIndicator3 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tab_indicator, tw, false);
		TextView tvTab3 = (TextView) tabIndicator3.getChildAt(1);
		ImageView ivTab3 = (ImageView) tabIndicator3.getChildAt(0);
		ivTab3.setBackgroundResource(R.drawable.maintab3);
		tvTab3.setText(R.string.maintab4);

		tabIndicator4 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tab_indicator, tw, false);
		TextView tvTab4 = (TextView) tabIndicator4.getChildAt(1);
		ImageView ivTab4 = (ImageView) tabIndicator4.getChildAt(0);
		ivTab4.setBackgroundResource(R.drawable.maintab4);
		tvTab4.setText(R.string.maintab4);
	}

	/**
	 * 初始化选项卡
	 * 
	 * */
	public void initTab() {

		TabHost.TabSpec tSpecHome = tabHost.newTabSpec(TAB1);
		tSpecHome.setIndicator(tabIndicator1);
		tSpecHome.setContent(new DummyTabContent(getBaseContext()));
		tabHost.addTab(tSpecHome);

		TabHost.TabSpec tSpecWall = tabHost.newTabSpec(TAB2);
		tSpecWall.setIndicator(tabIndicator2);
		tSpecWall.setContent(new DummyTabContent(getBaseContext()));
		tabHost.addTab(tSpecWall);

		TabHost.TabSpec tSpecCamera = tabHost.newTabSpec(TAB3);
		tSpecCamera.setIndicator(tabIndicator3);
		tSpecCamera.setContent(new DummyTabContent(getBaseContext()));
		tabHost.addTab(tSpecCamera);

		TabHost.TabSpec tSpecMessage = tabHost.newTabSpec(TAB4);
		tSpecMessage.setIndicator(tabIndicator4);
		tSpecMessage.setContent(new DummyTabContent(getBaseContext()));
		tabHost.addTab(tSpecMessage);

	}

}
