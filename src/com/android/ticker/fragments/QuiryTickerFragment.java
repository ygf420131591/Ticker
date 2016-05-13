package com.android.ticker.fragments;


import java.nio.channels.SelectableChannel;

import com.android.ticker.activitys.MainActivity;
import com.android.ticker.views.TopBarView.onClickButtonLister;
import com.example.ticker.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class QuiryTickerFragment extends Fragment implements OnClickListener {

	private ListView mListView;
	private TextView mSetOutTextView;
	private TextView mArriveTextView;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.fragment_quiryticker, container, false);
		
		String setOutStation = "上海";
		Bundle bundle = getArguments();
		if (bundle != null) {
			setOutStation = bundle.getString("setOutStation");
		}
		
		mSetOutTextView = (TextView) view.findViewById(R.id.SetOutTextView);
		mSetOutTextView.setText(setOutStation);
		mSetOutTextView.setOnClickListener(this);
		
		ImageView refreshImageView = (ImageView) view.findViewById(R.id.freshImageView);
		refreshImageView.setOnClickListener(this);
		
		mArriveTextView = (TextView) view.findViewById(R.id.ArrivalTextView);
		mArriveTextView.setOnClickListener(this);
		
//		QuiryTickerManage quiryTickerManage = new QuiryTickerManage();
		
//		mListView = (ListView) view.findViewById(R.id.tickerListView);
//		
//		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
//		updateList(list);
//		
//		AudioAdapter audioAdapter = new AudioAdapter(QuiryTickerFragment.this.getActivity(), list, R.layout.list_item_view, new String[]{"itemTitle", "itemText", "ItemImage"}, new int[]{R.id.TitleEditText, R.id.ContentEditText, R.id.imageView1});
//		mListView.setAdapter(audioAdapter);
		
		return view;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		View view = arg0;
		switch (view.getId()) {
		case R.id.SetOutTextView:
		case R.id.ArrivalTextView:
			enterStationList();
			break;
		case R.id.freshImageView:

			break;
		default:
			break;
		}
	}

	private void enterStationList() {
		StationListFragment fragment = new StationListFragment();
		MainActivity activity = (MainActivity) QuiryTickerFragment.this.getActivity();
		activity.switchFragment(this, fragment, true); 
	}
	
	
//	private void back() {
////		QuiryTickerFragment fragment = new QuiryTickerFragment();
//		MainActivity activity = (MainActivity) QuiryTickerFragment.this.getActivity();
//		activity.switchFragment(this, null);
//	}
	
//	private void updateList(ArrayList<HashMap<String, Object>> list) {
//		String path = Environment.getExternalStorageDirectory().getAbsolutePath();
//		File file = new File(path);
//		File[] files = file.listFiles();
//		
//		for (int i = 0; i < files.length; i ++) {
//			File currentFile = files[i];
//			String currentName = currentFile.getName();
//			if (currentName.contains(".opus")) {
//				list.add(currentName);
//			}
//		}
//		
//		for (int i = 0; i < 2; i++) {
////			File currentFile = files[i];
//			String currentName = "11.opus";
//			if (currentName.contains(".opus")) {
//				HashMap<String, Object> map = new HashMap<String, Object>();
//				map.put("ItemImage", R.drawable.common_back);
//				map.put("itemTitle", currentName);
//				map.put("itemText", "这是第" + i + "行");
//				list.add(map);
//			}
//		}
//	}
	
}