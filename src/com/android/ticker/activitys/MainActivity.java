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

public class MainActivity extends BaseFragmentActivity {
	
	private Fragment currentFragment;
//	private int mImageArray_s[] = {R.drawable.common_tab_schedule_s, R.drawable.common_tab_record_s, R.drawable.common_tab_my_s};
//	private int[] mImageArray_n = {R.drawable.common_tab_schedule_n, R.drawable.common_tab_record_n, R.drawable.common_tab_my_n};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	public void switchFragment(Fragment currentFragment, Fragment nextFragment, Boolean isShowBack) {
		this.currentFragment = currentFragment;
		enterFragment(nextFragment);
		
		if (isShowBack == true) {   //导航栏显示返回按钮
			mTopBarView.setBackButton(true, new onClickButtonLister() {
				
				@Override
				public void onClickButton() {
					// TODO Auto-generated method stub
					enterFragment(MainActivity.this.currentFragment);
				}
			});
		}
	}
	//返回上一页面
	public void backLastFragment(String name) {
		Bundle bundle = new Bundle();
		bundle.putString("params", name);
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

	
}
