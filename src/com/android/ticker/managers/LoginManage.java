package com.android.ticker.managers;


import java.util.ArrayList;

import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.ImageView;

import com.android.ticker.managers.networks.HttpManager;
import com.android.ticker.managers.networks.HttpManager.HttpDownLoadResp;
import com.android.ticker.managers.networks.HttpManager.HttpExecuteResp;

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
	
	public LoginManage() {
		mPoints = new ArrayList<LoginManage.Point>();
		mHttpManager = new HttpManager();
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

		final ImageView imageView = showImageView;
		final Handler handle = handler;
		
//		mHttpManager = new HttpManager();
		double rand = Math.random();
		String url = "https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=login&rand=sjrand&" + rand;
		mHttpManager.downLoadHttpRequest(url, new HttpDownLoadResp() {
			
			@Override
			public void httpDownLoadResp(final Bitmap bitMap) {
				// TODO Auto-generated method stub
				handle.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						imageView.setImageBitmap(bitMap);
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
		return content;
	}
	
	public void checkRandomCode() {
		
		String url = "https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn";
		
		String content = packageContent();
		mHttpManager.postHttpRequest(url, content, new HttpExecuteResp() {
			
			@Override
			public void httpExecuteResp(String data) {
				// TODO Auto-generated method stub
				
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
		
	}
	
	public interface LoginResponse {
		public void loginResponse();
	}

}
