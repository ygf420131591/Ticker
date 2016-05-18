package com.android.ticker.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import com.android.ticker.activitys.MainActivity;
import com.android.ticker.algorithm.SearchAlgorithm;
import com.android.ticker.views.AudioAdapter;
import com.android.ticker.views.SearchBar;
import com.example.ticker.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class StationListFragment extends Fragment implements TextWatcher, OnItemClickListener {
	
	private SearchAlgorithm mSearchAlgorithm;
	
	private SearchBar mSearchBar;
	private ListView mStationList;
	private AudioAdapter mAudioAdapter;
	ArrayList<HashMap<String, Object>> mDataSource;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.fragment_station_list, container, false);
		
		mSearchAlgorithm = SearchAlgorithm.instance();
		
		mStationList = (ListView) view.findViewById(R.id.StationList);
		
		mDataSource = new ArrayList<HashMap<String, Object>>();
		updateList();
		
		mAudioAdapter = new AudioAdapter(StationListFragment.this.getActivity(), mDataSource, R.layout.list_item_view, new String[]{"itemTitle", "itemText", "ItemImage"}, new int[]{R.id.TitleEditText, R.id.ContentEditText, R.id.imageView1});
		mStationList.setAdapter(mAudioAdapter);
		mStationList.setOnItemClickListener(this);
		
		mSearchBar = (SearchBar) view.findViewById(R.id.StationSearchBar);
		mSearchBar.addSearchTextChangedLister(this);
		return view;
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		Log.d("textChange", "after");
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		Log.d("textChange", "before" + arg0);
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		Log.d("textChange", "onChange" + s);
		int number = mSearchAlgorithm.searchString(s);
		
		updateList();
		mAudioAdapter.notifyDataSetChanged();
	}  
	
	private void updateList() {
		mDataSource.clear();
		ArrayList<String> result = mSearchAlgorithm.getResult();
		if (result != null) {
			for (int i = 0; i < result.size(); i++) {
				HashMap<String, Object>map = new HashMap<String, Object>();
				map.put("itemTitle", result.get(i));
				mDataSource.add(map);
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		int index = arg2;
		String stationName = null;
		if( mDataSource.get(index)  != null) {
			stationName = (String) mDataSource.get(index).get("itemTitle");
		}
		MainActivity activity = (MainActivity) StationListFragment.this.getActivity();
		activity.backLastFragment(stationName);
	}
	
}
