package com.android.ticker.managers;

import android.util.Log;

import com.android.ticker.managers.entitys.QuiryConditonEntity;
import com.android.ticker.managers.entitys.TickerInfo;
import com.android.ticker.managers.networks.HttpManager;
import com.android.ticker.managers.networks.HttpManager.HttpExecuteResp;
import com.google.gson.Gson;

public class QuiryTickerManage {

	private UpdateTickerRes mUpdateTickerRes;
	
	public QuiryTickerManage() {
		
//		HttpManager http = new HttpManager();
//		String url = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2016-05-06&leftTicketDTO.from_station=ZZF&leftTicketDTO.to_station=HGH&purpose_codes=ADULT";
//		http.getHttpRequest(url, new HttpExecuteResp() {
//			
//			@Override
//			public void httpExecuteResp(String data) {
//				// TODO Auto-generated method stub
//				Gson gson = new Gson();
//				TickerInfo tickerInfo = gson.fromJson(data, TickerInfo.class);
//				Log.d("HttpManager", data);
//			}
//		});
		
	}
	
	public void updateTicker(QuiryConditonEntity condition, UpdateTickerRes updateTickerRes) {
		
		mUpdateTickerRes = updateTickerRes;
		
		String url = "https://kyfw.12306.cn/otn/leftTicket/query?";
		url = url + "leftTicketDTO.train_date=" + condition.getTrainData() + "&";
		url = url + "leftTicketDTO.from_station=" + condition.getFromStation() + "&";
		url = url + "leftTicketDTO.to_station=" + condition.getToStation() + "&";
		url = url + "purpose_codes=" + condition.getPurposeCodes();
		
		HttpManager http = new HttpManager();
		http.getHttpRequest(url, new HttpExecuteResp() {
			
			@Override
			public void httpExecuteResp(String data) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				TickerInfo tickerInfo = gson.fromJson(data, TickerInfo.class);
				mUpdateTickerRes.updateTickerRes(tickerInfo);
				Log.d("HttpManager", data);
				
			}
		});
		
	}
	
	public interface UpdateTickerRes {
		public void updateTickerRes(TickerInfo tickerInfo); 
	}
	
}
