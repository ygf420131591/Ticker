package com.android.ticker.views;

import com.example.ticker.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class SittingTypeView extends LinearLayout {

	public SittingTypeView(Context context) {
//		super(context);  
		// TODO Auto-generated constructor stub
		this(context, null);
	}
	
	public SittingTypeView(Context context, AttributeSet attrs) { 
		this(context, attrs,R.style.AppTheme);  
	}  
	  
	public SittingTypeView(Context context, AttributeSet attrs, int defStyle) {  
		super(context, attrs, defStyle);  
		View view = LayoutInflater.from(context).inflate(R.layout.list_sitting_type, this, true);
	}  
	

	
}
