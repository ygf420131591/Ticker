package com.android.ticker.managers;


import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.android.ticker.managers.entitys.CheckLoginEntity;
import com.android.ticker.managers.entitys.LoginResultEntity;
import com.android.ticker.managers.entitys.PassengerInfoEntity;
import com.android.ticker.managers.entitys.RandomCodeEntity;
import com.android.ticker.managers.networks.HttpManager;
import com.android.ticker.managers.networks.HttpManager.HttpDownLoadResp;
import com.android.ticker.managers.networks.HttpManager.HttpExecuteResp;
import com.google.gson.Gson;

public class LoginManage {

	private class Point {
		private int x;
		private int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private HttpManager mHttpManager;
	private ArrayList<Point> mPoints; 
	private String mContent;
	private Gson gson;

	private ImageView mImageView;
	private Handler mHandle;
	
	public LoginManage() {
		mPoints = new ArrayList<LoginManage.Point>();
		mHttpManager = new HttpManager();
		gson = new Gson();
	}
	
	public void addPoint(int x, int y) {
		Point point = new Point(x, y);
		mPoints.add(point);
	}

	//获取验证码图片
//	https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=login&rand=sjrand&0.16828169995755204
//	注意：rand=sjrand  登录状态下验证码图片
//	     rand=randp 登录状态下验证码图片
	
	public void getRandomCodeImage(Handler handler, ImageView showImageView) {

		mImageView = showImageView;
		mHandle = handler;
		
//		mHttpManager = new HttpManager();
		double rand = Math.random();
		String url = "https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=login&rand=sjrand&" + rand;
		mHttpManager.downLoadHttpRequest(url, new HttpDownLoadResp() {
			
			@Override
			public void httpDownLoadResp(final Bitmap bitMap) {
				// TODO Auto-generated method stub
				mHandle.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						mImageView.setImageBitmap(bitMap);
					}
				});

			}
		});
	}
	
	//验证码
//	https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn
//	Accept:*/*
//	Accept-Encoding:gzip, deflate
//	Accept-Language:zh-CN,zh;q=0.8
//	Connection:keep-alive
//	Content-Length:40
//	Content-Type:application/x-www-form-urlencoded; charset=UTF-8
//	Cookie:__NRF=8A54FEB4D9269B8DF1C251409E7C2ADD; JSESSIONID=0A01D736C4C5CC38DC0E41E9035F64A6C760F54273; _jc_save_showIns=true; BIGipServerportal=3151233290.17183.0000; _jc_save_fromStation=%u5317%u4EAC%2CBJP; _jc_save_toStation=%u4E0A%u6D77%2CSHH; _jc_save_fromDate=2016-05-06; _jc_save_toDate=2016-05-04; _jc_save_wfdc_flag=dc; BIGipServerotn=920060170.50210.0000; current_captcha_type=Z
//	Host:kyfw.12306.cn
//	Origin:https://kyfw.12306.cn
//	Referer:https://kyfw.12306.cn/otn/login/init
//	User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36
//	X-Requested-With:XMLHttpRequest
//	content:randCode=238%2C69%2C92%2C131&rand=sjrand	
	
	private String packageContent() {
		String content = new String();
		for (int i = 0; i < mPoints.size(); i ++) {
			if (i != 0) {
				content += ",";
			}
			Point point = mPoints.get(i);
			String x = String.valueOf(point.x);
			String y = String.valueOf(point.y);
			content = content + x + "," + y ;
		}
		mPoints.clear();
		return content;
	}
	
	public void checkRandomCode() {
		
		String url = "https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn";
		
		mContent = packageContent();
		ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("randCode", mContent));
		nvps.add(new BasicNameValuePair("rand", "sjrand"));
		
		mHttpManager.postHttpRequest(url, nvps, new HttpExecuteResp() {
			
			@Override
			public void httpExecuteResp(String data) {
				// TODO Auto-generated method stub
				RandomCodeEntity entity = gson.fromJson(data, RandomCodeEntity.class);
				if (entity.getResult().equals("1")) {
					login();
				} else if (entity.getResult().equals("0")) {
					getRandomCodeImage(mHandle, mImageView);
				}
				
			}
		});
	}	
	//登录
//	https://kyfw.12306.cn/otn/login/loginAysnSuggest		
//	Accept:*/*
//	Accept-Encoding:gzip, deflate
//	Accept-Language:zh-CN,zh;q=0.8
//	Connection:keep-alive
//	Content-Length:94
//	Content-Type:application/x-www-form-urlencoded; charset=UTF-8
//	Cookie:__NRF=8A54FEB4D9269B8DF1C251409E7C2ADD; JSESSIONID=0A01D736C4C5CC38DC0E41E9035F64A6C760F54273; _jc_save_showIns=true; BIGipServerportal=3151233290.17183.0000; _jc_save_fromStation=%u5317%u4EAC%2CBJP; _jc_save_toStation=%u4E0A%u6D77%2CSHH; _jc_save_fromDate=2016-05-06; _jc_save_toDate=2016-05-04; _jc_save_wfdc_flag=dc; BIGipServerotn=920060170.50210.0000; current_captcha_type=Z
//	Host:kyfw.12306.cn
//	Origin:https://kyfw.12306.cn
//	Referer:https://kyfw.12306.cn/otn/login/init
//	User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36
//	X-Requested-With:XMLHttpRequest
//	content:loginUserDTO.user_name=ygf420131591&userDTO.password=ygf111040682&randCode=238%2C69%2C92%2C131
	
	public void login() {
		String url = "https://kyfw.12306.cn/otn/login/loginAysnSuggest";
		
		String content = packageContent();
		ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("loginUserDTO.user_name", "ygf420131591"));
		nvps.add(new BasicNameValuePair("userDTO.password", "ygf111040682"));
		nvps.add(new BasicNameValuePair("randCode", mContent));
		
		mHttpManager.postHttpRequest(url, nvps, new HttpExecuteResp() {
			
			@Override
			public void httpExecuteResp(String data) {
				// TODO Auto-generated method stub
//				LoginResultEntity entity = gson.fromJson(data, LoginResultEntity.class);
				
				checkUserLogin();
			}
		});
	}
	
	public void checkUserLogin() {
		String url = "https://kyfw.12306.cn/otn/login/checkUser";
		mHttpManager.postHttpRequest(url, null, new HttpExecuteResp() {
			
			@Override
			public void httpExecuteResp(String data) {
				// TODO Auto-generated method stub
				CheckLoginEntity entity = gson.fromJson(data, CheckLoginEntity.class);
				if (entity.getFlag() == true) {
					getSavaPassagerInfo();
				}
			}
		});
	}
	
	//获取信息
	public void getSavaPassagerInfo() {
		String url = "https://kyfw.12306.cn/otn/confirmPassenger/getPassengerDTOs";
		mHttpManager.postHttpRequest(url, null, new HttpExecuteResp() {
		
			@Override
			public void httpExecuteResp(String data) {
				// TODO Auto-generated method stub
				PassengerInfoEntity entity = gson.fromJson(data, PassengerInfoEntity.class);
				getCheckRandomCode(mHandle, mImageView);
			}
		});
		
	}
	
	//获取验证码图片
//	https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=login&rand=sjrand&0.16828169995755204
//	注意：rand=sjrand  登录状态下验证码图片
//	     rand=randp 登录状态下验证码图片
	
	public void getCheckRandomCode(Handler handler, ImageView showImageView) {

		mImageView = showImageView;
		mHandle = handler;
		
//		mHttpManager = new HttpManager();
		double rand = Math.random();
		String url = "https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=login&rand=sjrand&" + rand;
		mHttpManager.downLoadHttpRequest(url, new HttpDownLoadResp() {
			
			@Override
			public void httpDownLoadResp(final Bitmap bitMap) {
				// TODO Auto-generated method stub
				mHandle.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						mImageView.setImageBitmap(bitMap);
					}
				});

			}
		});
	}
	
//	https://kyfw.12306.cn/otn/confirmPassenger/checkOrderInfo
//	cancel_flag:2
//	bed_level_order_num:000000000000000000000000000000
//	passengerTicketStr:O,0,1,尤晓明,1,410311198810046528,,N
//	oldPassengerStr:尤晓明,1,410311198810046528,1_
//	tour_flag:dc
//	randCode:36,122,181,128,261,128,186,60
//	_json_att:
//	REPEAT_SUBMIT_TOKEN:149f39aca99fe014f8353c7e6d3e1018 13aaecbda3ea665bf3e41928f3e6d08e
		
	
	//获取已经保存的乘客信息
	public void checkOrderInfo() {
		
		ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("cancel_flag", "2"));
		nvps.add(new BasicNameValuePair("bed_level_order_num", "000000000000000000000000000000"));
		nvps.add(new BasicNameValuePair("passengerTicketStr", "O,0,1,尤晓明,1,410311198810046528,,N"));
		nvps.add(new BasicNameValuePair("oldPassengerStr", "尤晓明,1,410311198810046528,1_"));
		nvps.add(new BasicNameValuePair("tour_flag", "dc"));
		nvps.add(new BasicNameValuePair("randCode", mContent));
		nvps.add(new BasicNameValuePair("_json_att", ""));
		nvps.add(new BasicNameValuePair("REPEAT_SUBMIT_TOKEN", "149f39aca99fe014f8353c7e6d3e1018"));
		
		String url = "https://kyfw.12306.cn/otn/confirmPassenger/checkOrderInfo";
		mHttpManager.postHttpRequest(url, null, new HttpExecuteResp() {
			
			@Override
			public void httpExecuteResp(String data) {
				// TODO Auto-generated method stub
				confirmOrderInfo();
			}
		});
	
	}
	
	//获取已经保存的乘客信息
	public void confirmOrderInfo() {
			
		ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("passengerTicketStr", "O,0,1,尤晓明,1,410311198810046528,,N"));
		nvps.add(new BasicNameValuePair("oldPassengerStr", "尤晓明,1,410311198810046528,1_"));
		nvps.add(new BasicNameValuePair("randCode", mContent));
		nvps.add(new BasicNameValuePair("purpose_codes", "00"));
		nvps.add(new BasicNameValuePair("key_check_isChange", "0C2D186D046C407904D3335780016EB89580F9475704B1BED2C8EA48"));
		nvps.add(new BasicNameValuePair("leftTicketStr","O055300707M0933001039174800024"));
		nvps.add(new BasicNameValuePair("train_locatio","P4"));
		nvps.add(new BasicNameValuePair("dwAll", "N"));
		nvps.add(new BasicNameValuePair("_json_att", ""));
		nvps.add(new BasicNameValuePair("REPEAT_SUBMIT_TOKEN", "149f39aca99fe014f8353c7e6d3e1018"));

		String url = "https://kyfw.12306.cn/otn/confirmPassenger/confirmSingleForQueue?module=cmgp";
		mHttpManager.postHttpRequest(url, null, new HttpExecuteResp() {
				
			@Override
			public void httpExecuteResp(String data) {
				// TODO Auto-generated method stub
					
			}
		});
		
	}
		
//		https://kyfw.12306.cn/otn/confirmPassenger/confirmSingleForQueue?module=cmgp
//		passengerTicketStr:O,0,1,应国锋,1,330682198710232851,17706532371,N
//		oldPassengerStr:应国锋,1,330682198710232851,1_
//		randCode:259,63,257,123,129,124,106,51
//		purpose_codes:00
//		key_check_isChange:0C2D186D046C407904D3335780016EB89580F9475704B1BED2C8EA48
//		leftTicketStr:O055300707M0933001039174800024
//		train_location:P4
//		roomType:00
//		dwAll:N
//		_json_att:
//		REPEAT_SUBMIT_TOKEN:ae6437b290dddddf813269248c928053
	
	public interface LoginResponse {
		public void loginResponse();
	}

}
