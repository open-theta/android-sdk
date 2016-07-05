//
//  HttpTools.java
//
//  Copyright (c) 2014 Nexage. All rights reserved.
//

package net.adsplay.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import android.R;
import android.text.TextUtils;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class HttpTools {

	private static final String TAG = HttpTools.class.getName();

	public static void httpGetURL(final String url) {
		if (!TextUtils.isEmpty(url)) {
			new Thread() {
				@Override
				public void run() {
					HttpURLConnection conn = null;
					try {
						VASTLog.v(TAG, "connection to URL:" + url);
						URL httpUrl = new URL(url);

						HttpURLConnection.setFollowRedirects(true);
						conn = (HttpURLConnection) httpUrl.openConnection();
						conn.setConnectTimeout(5000);
						conn.setRequestProperty("Connection", "close");
						conn.setRequestMethod("GET");

						int code = conn.getResponseCode();
						VASTLog.v(TAG, "response code:" + code
								+ ", for URL:" + url);
					} catch (Exception e) {
						VASTLog.w(TAG, url + ": " + e.getMessage() + ":"
								+ e.toString());
					} finally {
						if (conn != null) {
							try {
								conn.disconnect();
							} catch (Exception e) {
							}
						}
					}
				}
			}.start();
		} else {
			VASTLog.w(TAG, "url is null or empty");
			
		}
	}
	
	static class HttpGet {
		OkHttpClient client;
		
		String url;

		public HttpGet(String url) {
			this.url = url;
			client = new OkHttpClient();
			client.setConnectTimeout(5, TimeUnit.SECONDS); // connect timeout
			client.setReadTimeout(5, TimeUnit.SECONDS);    // socket timeout
			
			try {
				// creating a KeyStore containing our trusted CAs
				String keyStoreType = KeyStore.getDefaultType();
				KeyStore keyStore = KeyStore.getInstance(keyStoreType);
				keyStore.load(null, null);
				//keyStore.setCertificateEntry("ca", ca);
				
				// creating a TrustManager that trusts the CAs in our KeyStore
				String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
				TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
				tmf.init(keyStore);
				
				// creating an SSLSocketFactory that uses our TrustManager
				SSLContext sslContext = SSLContext.getInstance("TLS");
				sslContext.init(null, tmf.getTrustManagers(), null);
				client.setSslSocketFactory(sslContext.getSocketFactory());			 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public String run()  {
			try {
				Request request = new Request.Builder().url(url).build();				
				Response response = client.newCall(request).execute();
				return response.body().string();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}

	public static String get(String url) {
		return new HttpGet(url).run();
	}
}
