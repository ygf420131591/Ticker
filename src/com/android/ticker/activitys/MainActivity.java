package com.android.ticker.activitys;

import com.android.ticker.fragments.MyFragment;
import com.android.ticker.fragments.QuiryTickerFragment;
import com.android.ticker.fragments.QuiryOrderFragment;
import com.android.ticker.fragments.StationListFragment;
import com.android.ticker.views.NavTabButton;
import com.android.ticker.views.TopBarView;
import com.android.ticker.views.TopBarView.onClickButtonLister;
import com.example.ticker.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.widget.TabHost.TabSpec;

public class MainActivity extends FragmentActivity {
	
	private FragmentTabHost mTabHost;
	private TopBarView mTopBarView;
	
	private NavTabButton[] navButton; 
	private Class mFragmentArray[] = {QuiryTickerFragment.class, QuiryOrderFragment.class, MyFragment.class};
	private String mTextArray[] = {"车票预订", "订单查询", "我的12306"};
	
	private Fragment currentFragment;
//	private int mImageArray_s[] = {R.drawable.common_tab_schedule_s, R.drawable.common_tab_record_s, R.drawable.common_tab_my_s};
//	private int[] mImageArray_n = {R.drawable.common_tab_schedule_n, R.drawable.common_tab_record_n, R.drawable.common_tab_my_n};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		
	}
	
	private void initView() {
		
		mTopBarView = (TopBarView) findViewById(R.id.TabBarView);
		mTopBarView.setTitleText(mTextArray[0]);
		
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		
		int count = mFragmentArray.length;
		navButton = new NavTabButton[count];
		for (int i = 0; i < count; i++) {
			navButton[i] = new NavTabButton(this, i);
			navButton[i].setTitle(mTextArray[i]);
//			if (i == 0) {
//				navButton[i].setImageView(mImageArray_s[i]);
//			} else {
//				navButton[i].setImageView(mImageArray_n[i]);
//			}
			TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i]).setIndicator(navButton[i]);
			mTabHost.addTab(tabSpec, mFragmentArray[i], null);
		}
	}

	public void switchFragment(Fragment currentFragment, Fragment nextFragment, Boolean isShowBack) {
		this.currentFragment = currentFragment;
		enterFragment(nextFragment);
		
		if (isShowBack == true) {
			mTopBarView.setBackButton(true, new onClickButtonLister() {
				
				@Override
				public void onClickButton() {
					// TODO Auto-generated method stub
					enterFragment(MainActivity.this.currentFragment);
				}
			});
		}
	}
	
	public void backLastFragment(String name) {
		Bundle bundle = new Bundle();
		bundle.putString("setOutStation", name);
		currentFragment.setArguments(bundle);
		enterFragment(currentFragment);
	}
	
	private void enterFragment(Fragment fragment) {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
//		transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
		transaction.replace(R.id.realtabcontent, fragment);
		transaction.commit();	
		mTopBarView.setBackButton(false, null);
	}
	
	
	public void setSelectedIndicator(int index) {
		mTabHost.setCurrentTab(index);			//切换到相应的tab;
		mTopBarView.setTitleText(mTextArray[index]);  //重置顶部标题
		mTopBarView.setBackButton(false, null);
		if (index == 1) {
			mTopBarView.setNextButton(true, new onClickButtonLister() {
				
				@Override
				public void onClickButton() {
					// TODO Auto-generated method stub
//					Intent intent = new Intent(MainActivity.this, VoiceRecordActivity.class);
//					startActivity(intent);
				}
			});
		} else {
			mTopBarView.setNextButton(false, null);
			if (index == 0) {
				
			}
		}
		
		int count = mFragmentArray.length;
//		for (int i = 0; i < count; i++) {
//			navButton[i].setImageView(mImageArray_n[i]);
//		}
//		navButton[index].setImageView(mImageArray_s[index]);
	}

	
}
