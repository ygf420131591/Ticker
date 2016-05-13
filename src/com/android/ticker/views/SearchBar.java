package com.android.ticker.views;

import com.example.ticker.R;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class SearchBar extends RelativeLayout {
	
	public SearchBar(Context context) {
//		super(context);  
		// TODO Auto-generated constructor stub
		this(context, null);
	}
	
	public SearchBar(Context context, AttributeSet attrs) { 
		this(context, attrs,R.style.AppTheme);  
	}  
	  
	public SearchBar(Context context, AttributeSet attrs, int defStyle) {  
		super(context, attrs, defStyle);  
		View view = LayoutInflater.from(context).inflate(R.layout.search_bar, this, true);

	}
	
	public void addSearchTextChangedLister(TextWatcher lister) {
		EditText textView = (EditText) findViewById(R.id.searchTextView);
		textView.addTextChangedListener(lister);
	}

	
}
