package com.android.ticker.managers.networks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceActivity.Header;

import com.android.ticker.utils.http.MYSSLSocketFactory;


public class HttpManager {
	
	private DefaultHttpClient mHttpClient;
	
	public interface HttpExecuteResp {
		public void httpExecuteResp(String data);
	}
	
	public interface HttpDownLoadResp {
		public void httpDownLoadResp(Bitmap bitMap);
	}
	
	public HttpManager() {
		
	}
	
	private DefaultHttpClient getDefaultHttpClient() throws Exception {
		
		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		trustStore.load(null, null);
		SSLSocketFactory sf = new MYSSLSocketFactory(trustStore);
		sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		
		HttpParams params = new BasicHttpParams();  
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);  
        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);  
        HttpConnectionParams.setConnectionTimeout(params, 10000);  
        HttpConnectionParams.setSoTimeout(params, 10000);  

		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		registry.register(new Scheme("https", sf, 443));
		
		ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
		DefaultHttpClient httpClient = new DefaultHttpClient(ccm, params);
		
		return httpClient;
	}
	
	public void getHttpRequest(final String url, HttpExecuteResp resp) {
		
		final HttpExecuteResp executeResp = resp;
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpGet request = new HttpGet(url);
//				HttpClient httpClient = new DefaultHttpClient();

				try {
					
					DefaultHttpClient httpClient = getDefaultHttpClient();
					
					HttpResponse response = httpClient.execute(request);
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						//回调
						if (executeResp != null) {
							String data = EntityUtils.toString(response.getEntity());
							executeResp.httpExecuteResp(data);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		thread.start();
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
	/*
	 * 
	 * */
	public void postHttpRequest(final String url, final String content, HttpExecuteResp response) {
		
		final HttpExecuteResp httpResponse = response;
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					HttpPost request = new HttpPost(url);

					request.addHeader("Connection", "keep-alive");
					request.addHeader("X_REQUESTED_WITH", "XMLHttpRequest");  
//					request.addHeader("Referer", "https://kyfw.12306.cn/otn/login/init");
//					request.addHeader("Origin", "https://kyfw.12306.cn");
//					request.addHeader("Accept", "*/*");
					
					ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
					nvps.add(new BasicNameValuePair("randCode", content));
					nvps.add(new BasicNameValuePair("rand", "sjrand"));
					request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
					
//					StringEntity entity = new StringEntity(content, "utf-8");
//					entity.setContentEncoding("UTF-8");
//					entity.setContentType("application/json");
//					request.setEntity(entity);
					
					HttpResponse response = mHttpClient.execute(request);
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						//回调
						if (httpResponse != null) {
							String data = EntityUtils.toString(response.getEntity());
							httpResponse.httpExecuteResp(data);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		thread.start();
	}
	
	public void downLoadHttpRequest(final String url, HttpDownLoadResp resp) {
		
		final HttpDownLoadResp executeResp = resp;
		
		Thread thread = new Thread(new Runnable() {
			
			private FileOutputStream fos;

			@Override
			public void run() {
				// TODO Auto-generated method stub

				
				try {

					mHttpClient = getDefaultHttpClient();
					
					HttpPost request = new HttpPost(url);
					request.addHeader("Connection", "keep-alive");

					URL currentURL = new URL(url);
					String  content = currentURL.getQuery();
					StringEntity entity = new StringEntity(content, "utf-8");
					entity.setContentEncoding("UTF-8");
					entity.setContentType("application/json");
					request.setEntity(entity);
					
					HttpResponse response = mHttpClient.execute(request);
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						//回调
						if (executeResp != null) {
			
							InputStream inputStream = response.getEntity().getContent();
							Bitmap bitMap = BitmapFactory.decodeStream(inputStream);
//							
//							File filePath = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + "/RandomCode.jpeg");
//							fos = new FileOutputStream(filePath);
//							byte[] data = new byte[4 * 1024];
//							int len = 0;
//							while ((len = inputStream.read(data)) != -1) {
//								Log.d("test", "len = " + len);
//								fos.write(data, 0, len);
//							}
							
							executeResp.httpDownLoadResp(bitMap);
						}
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		thread.start();
	}
	
}
