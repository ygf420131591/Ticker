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
		private String train_no;   //���
		private String station_train_code; //���κ�
		private String start_station_telecode; //���վ���
		private String start_station_name;  //���վ����
		private String end_station_telecode; //�յ�վ���
		private String end_station_name; //�յ�վ����
		private String from_station_telecode; //�������վ���
		private String from_station_name; //�������վ����
		private String to_station_telecode; //�����յ�վ���
		private String to_station_name; //�����յ�վ����
		private String start_time;		//��ʼʱ��
		private String arrive_time;  //����ʱ��
		private String day_difference; //
		private String train_class_name; //�г��б�����
		private String lishi; 
		private String canWebBuy; //�Ƿ������繺Ʊ
		private String lishiValue;
		private String yp_info;
		private String control_train_day;
        private String start_train_date;  //��ʼ����
        private String seat_feature; //��λ����
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
        private String gr_num; //�߼�����
        private String qt_num; //����
        private String rw_num; //����
        private String rz_num; //����
        private String tz_num; //�ص���
        private String wz_num;  //����
        private String yb_num;  //
        private String yw_num;  //Ӳ��
        private String yz_num; //Ӳ��
        private String ze_num;   //����
        private String zy_num;  //һ��
        private String swz_num; //������
	}
	
}
