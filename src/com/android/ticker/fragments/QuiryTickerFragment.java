package com.android.ticker.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.android.ticker.activitys.MainActivity;
import com.android.ticker.activitys.TIckerInfoActivity;
import com.android.ticker.algorithm.SearchAlgorithm;
import com.android.ticker.managers.QuiryTickerManage;
import com.android.ticker.managers.QuiryTickerManage.UpdateTickerRes;
import com.android.ticker.managers.entitys.QuiryConditonEntity;
import com.android.ticker.managers.entitys.TickerInfo;
import com.example.ticker.R;


import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QuiryTickerFragment extends Fragment implements OnClickListener, OnDateSetListener, UpdateTickerRes {

	private ListView mListView;
	private TextView mSetOutTextView;
	private TextView mArriveTextView;
	
	private TextView mDateTextView;
	
	private Boolean isDestination = false;
	private RelativeLayout mQuiryButton;
	
	private QuiryTickerManage mQuiryTickerManage;
	private SearchAlgorithm mSearchAlgorithm;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.fragment_quiryticker, container, false);
		
		mQuiryTickerManage = new QuiryTickerManage();
		mSearchAlgorithm = SearchAlgorithm.instance();
		
		mSetOutTextView = (TextView) view.findViewById(R.id.SetOutTextView);
		mSetOutTextView.setOnClickListener(this);
		
		ImageView refreshImageView = (ImageView) view.findViewById(R.id.freshImageView);
		refreshImageView.setOnClickListener(this);
		
		mArriveTextView = (TextView) view.findViewById(R.id.ArrivalTextView);
		mArriveTextView.setOnClickListener(this);
		
		mDateTextView = (TextView)view.findViewById(R.id.setoutDataContent);
		mDateTextView.setOnClickListener(this);
		
		mQuiryButton = (RelativeLayout)view.findViewById(R.id.quiryTickerButton);
		mQuiryButton.setOnClickListener(this);
	
		String result = "上海";
		Bundle bundle = getArguments();
		if (bundle != null && !isDestination) {
			result = bundle.getString("params");
			mSetOutTextView.setText(result);
		} else if (bundle != null && isDestination) {
			result = bundle.getString("params");
			mArriveTextView.setText(result);
		}
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
		case R.id.SetOutTextView:    //出发地
			isDestination = false;
			enterStationList();
			break;
		case R.id.ArrivalTextView:   //到达地
			isDestination = true;
			enterStationList();
			break;
		case R.id.freshImageView:     //刷新
			String text =  mArriveTextView.getText().toString();
			mArriveTextView.setText(mSetOutTextView.getText());
			mSetOutTextView.setText(text);
			break;
		case R.id.setoutDataContent:  //日期选择
			Calendar cal = Calendar.getInstance();
			new DatePickerDialog(QuiryTickerFragment.this.getActivity(), QuiryTickerFragment.this, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
			break;
		case R.id.quiryTickerButton: //查询
			quiryTicker();
			break;
		default:
			break;
		}
	}

	/**
	 * 查询车票
	 */
	private void quiryTicker() {
		QuiryConditonEntity entity = new QuiryConditonEntity();
		entity.setTrainData(mDateTextView.getText().toString());
		String setoutID = mSearchAlgorithm.getEnglishID(mSetOutTextView.getText().toString());
		String arriveID = mSearchAlgorithm.getEnglishID(mArriveTextView.getText().toString());
		entity.setFromStation(setoutID);
		entity.setToStation(arriveID);
		entity.setPurposeCodes("ADULT");
		mQuiryTickerManage.updateTicker(entity, this);
	}
	
	private void enterStationList() {
		StationListFragment fragment = new StationListFragment();
		MainActivity activity = (MainActivity) QuiryTickerFragment.this.getActivity();
		activity.switchFragment(this, fragment, true); 
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		String date = "" + year + "-" + monthOfYear + "-" + dayOfMonth;
		mDateTextView.setText(date);
	}

	/**
	 * 获取车票信息成功
	 * @param tickerInfo
	 */
	@Override
	public void updateTickerRes(TickerInfo tickerInfo) {
		// TODO Auto-generated method stub
		
		Intent intent = new Intent(QuiryTickerFragment.this.getActivity(), TIckerInfoActivity.class);
		QuiryTickerFragment.this.getActivity().startActivity(intent);
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