package com.android.ticker.fragments;


import com.android.ticker.managers.LoginManage;
import com.example.ticker.R;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.fragment_my, container, false);
	
		ImageView randomCode = (ImageView) view.findViewById(R.id.randomCodeImage);
		
//		randomCode.setImageBitmap(bm)
		
		LoginManage loginManage = new LoginManage();
		Handler handler = new Handler();
		
		loginManage.getRandomCodeImage(handler, randomCode);
		return view; 
	}
	
}
