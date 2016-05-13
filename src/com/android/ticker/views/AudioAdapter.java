package com.android.ticker.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AudioAdapter extends BaseAdapter {

	List<HashMap<String, Object>> mListItem; //text���ݺ�image source ID
	String[] mKeys;   //textview��text�ؼ��ʺ�image source ID�ؼ���
	int[] mSources;   //�����пؼ�id
	int mLayout;  //�����ļ�
	Context mContext;  
	LayoutInflater mLayoutInflater;

	public AudioAdapter(Context context, ArrayList<HashMap<String, Object>> list, int layout, String[] keys, int[] sources) {
		mLayoutInflater = LayoutInflater.from(context);
		mContext = context;
		mListItem = list;
		mKeys = keys;
		mLayout = layout;
		mSources = sources;
	}
	
	public void addList(ArrayList<HashMap<String, Object>> list) {
		mListItem = list;
	}
	
	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
	}



	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListItem.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(mLayout, null);
			holder = new ViewHolder();
			holder.mTitleTextView = (TextView) convertView.findViewById(mSources[0]);
			holder.mContentTextView = (TextView) convertView.findViewById(mSources[1]);
			holder.mImageView = (ImageView) convertView.findViewById(mSources[2]);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
			
		}
		
		if (mListItem.get(position).containsKey(mKeys[0])) {
			holder.mTitleTextView.setText((String)mListItem.get(position).get(mKeys[0]));
			holder.mTitleTextView.setVisibility(View.VISIBLE);
		} else {
			holder.mTitleTextView.setVisibility(View.GONE);
		}
		if (mListItem.get(position).containsKey(mKeys[1])) {
			holder.mContentTextView.setText((String)mListItem.get(position).get(mKeys[1]));
			holder.mContentTextView.setVisibility(View.VISIBLE);
		} else {
			holder.mContentTextView.setVisibility(View.GONE);
		}
		if (mListItem.get(position).containsKey(mKeys[2])) {
			int sourceId = (int) mListItem.get(position).get(mKeys[2]);
			holder.mImageView.setImageDrawable(mContext.getResources().getDrawable(sourceId));
			holder.mImageView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.d("AudioAdapter", "���click");
				}
			});
			holder.mImageView.setVisibility(View.VISIBLE);
		} else {
			holder.mImageView.setVisibility(View.GONE);
		}
		
		return convertView;
	}
	
	private class ViewHolder {
		public TextView mTitleTextView;
		public TextView mContentTextView;
		public ImageView mImageView;
	}

}
