package com.android.ticker.managers.entitys;

import javax.security.auth.PrivateCredentialPermission;

public class TickerInfo {

	private String validateMessagesShowId;
	private Boolean status;
	private int httpstatus;
	private TickerData[] data;
	private String[] messages;
	private ValidateMessage validateMessages;
	
	private class ValidateMessage {
		
	}
	
	private class TickerData {
		private  CarInfo queryLeftNewDTO;
		private String secretStr;
		private String buttonTextInfo;
	}
	private class CarInfo {
		private String train_no;   //编号
		private String station_train_code; //车次号
		private String start_station_telecode; //起点站编号
		private String start_station_name;  //起点站名字
		private String end_station_telecode; //终点站编号
		private String end_station_name; //终点站名字
		private String from_station_telecode; //搜索起点站编号
		private String from_station_name; //搜索起点站名字
		private String to_station_telecode; //搜索终点站编号
		private String to_station_name; //搜索终点站名字
		private String start_time;		//开始时间
		private String arrive_time;  //到达时间
		private String day_difference; //
		private String train_class_name; //列车列别名字
		private String lishi; 
		private String canWebBuy; //是否能网络购票
		private String lishiValue;
		private String yp_info;
		private String control_train_day;
        private String start_train_date;  //开始日期
        private String seat_feature; //座位特征
        private String yp_ex; 
        private String train_seat_feature;
        private String seat_types;
        private String location_code;
        private String from_station_no;
        private String to_station_no;
        private int control_day;
        private String sale_time;
        private String is_support_card;
        private String controlled_train_flag;
        private String controlled_train_message;
        private String gg_num;
        private String gr_num; //高级软卧
        private String qt_num; //其他
        private String rw_num; //软卧
        private String rz_num; //软座
        private String tz_num; //特等座
        private String wz_num;  //无座
        private String yb_num;  //
        private String yw_num;  //硬卧
        private String yz_num; //硬座
        private String ze_num;   //二等
        private String zy_num;  //一等
        private String swz_num; //商务座
	}
	
}
