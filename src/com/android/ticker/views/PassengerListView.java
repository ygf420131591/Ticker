package com.android.ticker.views;

import com.example.ticker.R;
import com.example.ticker.R.styleable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * TODO: document your custom view class.
 */
public class PassengerListView extends LinearLayout {

	
	public PassengerListView(Context context) {
//		super(context);  
		// TODO Auto-generated constructor stub
		this(context, null);
	}
	
	public PassengerListView(Context context, AttributeSet attrs) { 
		this(context, attrs,R.style.AppTheme);  
	}  
	  
	public PassengerListView(Context context, AttributeSet attrs, int defStyle) {  
		super(context, attrs, defStyle);  
		View view = LayoutInflater.from(context).inflate(R.layout.list_passenger_view, this, true);
	}  
	
	
}
