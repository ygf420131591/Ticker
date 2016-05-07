package com.android.ticker.managers.entitys;

public class PassengerInfoEntity {

	private String validateMessagesShowId;
	private Boolean status;
	private int httpstatus;
	private RespData data; 
	private String[] message;
	private ValidateMessages validateMessages;
	
	private class RespData {
		private Boolean isExist;
		private String exMsg;
		private String[] two_isOpenClick;
		private String[] other_isOpenClick;
		private PassengerInfo[] normal_passengers;
		private ValidateMessages validateMessages;
	}
	
	private class PassengerInfo {
		private String code;
		private String passenger_name;
		private String sex_code;
		private String sex_name;
		private String born_date;
		private String country_code;
		private String passenger_id_type_code;
		private String passenger_id_no;
		private String passenger_type;
		private String passenger_flag;
		private String passenger_type_name;
		private String mobile_no;
		private String phone_no;
		private String email;
		private String address;
		private String postalcode;
		private String first_letter;
		private String recordCount;
		private String total_times;
		private String index_id;
	}
	
	private class ValidateMessages {
		
	}
	
//data":{"isExist":true,"exMsg":"","two_isOpenClick":["93","95","97","99"],"other_isOpenClick":["91","93","98","99","95","97"],"normal_passengers":[{"code":"2","passenger_name":"应国锋","sex_code":"M","sex_name":"男","born_date":"1987-10-23 00:00:00","country_code":"CN","passenger_id_type_code":"1","passenger_id_type_name":"二代身份证","passenger_id_no":"330682198710232851","passenger_type":"1","passenger_flag":"0","passenger_type_name":"成人","mobile_no":"17706532371","phone_no":"","email":"yigufe@sina.com","address":"","postalcode":"","first_letter":"","recordCount":"6","total_times":"99","index_id":"0"},{"code":"5","passenger_name":"陈美荣","sex_code":"","born_date":"2013-05-22 16:20:00","country_code":"","passenger_id_type_code":"1","passenger_id_type_name":"二代身份证","passenger_id_no":"342826196602040822","passenger_type":"1","passenger_flag":"0","passenger_type_name":"成人","mobile_no":"","phone_no":"","email":"","address":"","postalcode":"","first_letter":"CMR","recordCount":"6","total_times":"99","index_id":"1"},{"code":"3","passenger_name":"邓兵","sex_code":"M","sex_name":"男","born_date":"1987-11-30 00:00:00","country_code":"CN","passenger_id_type_code":"1","passenger_id_type_name":"二代身份证","passenger_id_no":"340826198711300838","passenger_type":"1","passenger_flag":"0","passenger_type_name":"成人","mobile_no":"","phone_no":"","email":"","address":"","postalcode":"","first_letter":"DB","recordCount":"6","total_times":"99","index_id":"2"},{"code":"4","passenger_name":"邓忠义","sex_code":"","born_date":"2013-05-22 16:20:00","country_code":"","passenger_id_type_code":"1","passenger_id_type_name":"二代身份证","passenger_id_no":"342826196310040812","passenger_type":"1","passenger_flag":"0","passenger_type_name":"成人","mobile_no":"","phone_no":"","email":"","address":"","postalcode":"","first_letter":"DZY","recordCount":"6","total_times":"99","index_id":"3"},{"code":"6","passenger_name":"龙永刚","sex_code":"M","sex_name":"男","born_date":"1991-02-06 00:00:00","country_code":"CN","passenger_id_type_code":"1","passenger_id_type_name":"二代身份证","passenger_id_no":"342501199102062814","passenger_type":"1","passenger_flag":"0","passenger_type_name":"成人","mobile_no":"","phone_no":"","email":"","address":"","postalcode":"","first_letter":"LYG","recordCount":"6","total_times":"99","index_id":"4"},{"code":"1","passenger_name":"尤晓明","sex_code":"","born_date":"1900-01-01 00:00:00","country_code":"CN","passenger_id_type_code":"1","passenger_id_type_name":"二代身份证","passenger_id_no":"410311198810046528","passenger_type":"1","passenger_flag":"0","passenger_type_name":"成人","mobile_no":"","phone_no":"","email":"","address":"","postalcode":"","first_letter":"YXM","recordCount":"6","total_times":"99","index_id":"5"}],"dj_passengers":[]},"messages":[],"validateMessages":{}}
}
