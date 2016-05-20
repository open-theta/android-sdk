package net.adsplay.util;

import android.os.Build;

public class AdsPlayVastUtil {
	//https://d5.adsplay.net/vmap/ads?placement=302
	static final String[] baseDeliveryUrls = {		
		"http://d2.adsplay.net/vmap/ads",		
		"http://d4.adsplay.net/vmap/ads",
		"http://d6.adsplay.net/vmap/ads",
		};
	public static final String USER_AGENT = "Mozilla/5.0 (Linux; Android "+Build.VERSION.RELEASE+"; Nexus 5 Build/_BuildID_) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36";
	
	
	public static final int PLACEMENT_ID_DEV = 333;
	
	public static final int FPT_PLAY_LIVE_STREAM_IOS = 301;	
	public static final int FPT_PLAY_LIVE_STREAM_ANDROID_BOX = 303;
	public static final int FPT_PLAY_LIVE_STREAM_ANDROID_APP = 305;
	public static final int FPT_PLAY_LIVE_STREAM_ANDROID_SMART_TV = 307;
	
	public static final int FPT_PLAY_VOD_IOS = 302;	
	public static final int FPT_PLAY_VOD_ANDROID_BOX = 304;
	public static final int FPT_PLAY_VOD_ANDROID_APP = 306;	
	public static final int FPT_PLAY_VOD_ANDROID_SMART_TV = 308;
	
	public static String getVastUrlForDemo(String uuid){			
		return getVastUrl(uuid, PLACEMENT_ID_DEV);
	}
	
	public static String getVastUrl(String uuid, int placementId){			
		int t = (int) (System.currentTimeMillis()/1000L);		
		int s = baseDeliveryUrls.length;
		String baseUrl = baseDeliveryUrls[t % s];
		StringBuilder url = new StringBuilder(baseUrl);
		url.append("?placement=").append(placementId);		
		url.append("&uuid=").append(uuid);		
		url.append("&t=").append(t);
		return url.toString();
	}
}
