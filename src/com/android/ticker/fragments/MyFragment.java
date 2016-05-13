package com.android.ticker.fragments;


import com.android.ticker.managers.LoginManage;
import com.example.ticker.R;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MyFragment extends Fragment implements OnTouchListener, OnClickListener {

	private LoginManage mLoginManage;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.fragment_my, container, false);
		
		ImageButton login = (ImageButton) view.findViewById(R.id.LoginImageButton);
		login.setImageDrawable(view.getResources().getDrawable(R.drawable.common_login));
		login.setOnClickListener(this);
	
		ImageView randomCode = (ImageView) view.findViewById(R.id.randomCodeImage);
		randomCode.setOnTouchListener(this);
		
		Handler handler = new Handler();
		
		mLoginManage = new LoginManage();
		mLoginManage.getRandomCodeImage(handler, randomCode);
		return view; 
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		int x = (int) (event.getX() / 3);
		int y = (int) ((event.getY() - 90)  / 3);
		Log.d("randomCode", "x = " + x + "y = " + y);
		//TODO 
		mLoginManage.addPoint(x, y);
		return false;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		mLoginManage.checkRandomCode();
//		mLoginManage.login();
	}
	
}
