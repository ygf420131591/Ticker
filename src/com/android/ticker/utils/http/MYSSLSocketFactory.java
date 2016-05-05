package com.android.ticker.utils.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.params.HttpParams;

public class MYSSLSocketFactory extends SSLSocketFactory {

	SSLContext sslContext = SSLContext.getInstance("SSL");  
	
	public MYSSLSocketFactory(KeyStore truststore)
			throws NoSuchAlgorithmException, KeyManagementException,
			KeyStoreException, UnrecoverableKeyException {
		super(truststore);
		// TODO Auto-generated constructor stub
		X509TrustManager tm = new X509TrustManager() {
			
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType)
					throws CertificateException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType)
					throws CertificateException {
				// TODO Auto-generated method stub
				
			}
		};
	
		sslContext.init(null, new TrustManager[] { tm }, null);
	}

	

	@Override
	public Socket createSocket(Socket socket, String host, int port,
			boolean autoClose) throws IOException, UnknownHostException {
		// TODO Auto-generated method stub
		return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
	}



	@Override
	public Socket createSocket() throws IOException {
		// TODO Auto-generated method stub
		 return sslContext.getSocketFactory().createSocket();
	}
	
	

}
