package com.android.ticker.managers;

import android.util.Log;

import com.android.ticker.managers.entitys.TickerInfo;
import com.android.ticker.managers.networks.HttpManager;
import com.android.ticker.managers.networks.HttpManager.HttpExecuteResp;
import com.google.gson.Gson;

public class QuiryTickerManage {

	public QuiryTickerManage() {
		
		HttpManager http = new HttpManager();
		String url = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2016-05-06&leftTicketDTO.from_station=ZZF&leftTicketDTO.to_station=HGH&purpose_codes=ADULT";
		http.getHttpRequest(url, new HttpExecuteResp() {
			
			@Override
			public void httpExecuteResp(String data) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				TickerInfo tickerInfo = gson.fromJson(data, TickerInfo.class);
				Log.d("HttpManager", data);
			}
		});
		
	}
	
}
