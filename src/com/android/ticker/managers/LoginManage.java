package com.android.ticker.managers;


import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.ImageView;

import com.android.ticker.managers.networks.HttpManager;
import com.android.ticker.managers.networks.HttpManager.HttpDownLoadResp;

public class LoginManage {

	public LoginManage() {
		
	}

	//获取验证码图片
//	https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=login&rand=sjrand&0.16828169995755204
//	注意：rand=sjrand  登录状态下验证码图片
//	     rand=randp 登录状态下验证码图片
	
	
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
	
	public void getRandomCodeImage(Handler handler, ImageView showImageView) {

		final ImageView imageView = showImageView;
		final Handler handle = handler;
		
		HttpManager http = new HttpManager();
		double rand = Math.random();
		String url = "https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=login&rand=sjrand&" + rand;
		http.downLoadHttpRequest(url, new HttpDownLoadResp() {
			
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
	
	public interface LoginResponse {
		public void loginResponse();
	}

}
