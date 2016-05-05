package com.android.ticker.fragments;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.android.ticker.managers.QuiryTickerManage;
import com.android.ticker.views.AudioAdapter;
import com.example.ticker.R;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class QuiryTickerFragment extends Fragment {

	private ListView mListView;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.fragment_quiryticker, container, false);
		
//		QuiryTickerManage quiryTickerManage = new QuiryTickerManage();
		
		mListView = (ListView) view.findViewById(R.id.tickerListView);
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		updateList(list);
		
		AudioAdapter audioAdapter = new AudioAdapter(QuiryTickerFragment.this.getActivity(), list, R.layout.list_item_view, new String[]{"itemTitle", "itemText", "ItemImage"}, new int[]{R.id.TitleEditText, R.id.ContentEditText, R.id.imageView1});
		mListView.setAdapter(audioAdapter);
		
		return view;
	}
	
	private void updateList(ArrayList<HashMap<String, Object>> list) {
		String path = Environment.getExternalStorageDirectory().getAbsolutePath();
		File file = new File(path);
		File[] files = file.listFiles();
//		
//		for (int i = 0; i < files.length; i ++) {
//			File currentFile = files[i];
//			String currentName = currentFile.getName();
//			if (currentName.contains(".opus")) {
//				list.add(currentName);
//			}
//		}
		
		for (int i = 0; i < 2; i++) {
//			File currentFile = files[i];
			String currentName = "11.opus";
			if (currentName.contains(".opus")) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("ItemImage", R.drawable.common_back);
				map.put("itemTitle", currentName);
				map.put("itemText", "这是第" + i + "行");
				list.add(map);
			}
		}
	}
	
}