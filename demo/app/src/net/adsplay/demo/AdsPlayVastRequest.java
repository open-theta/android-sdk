package net.adsplay.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.adsplay.util.AdsPlayVastUtil;
import net.adsplay.util.HttpTools;
import net.adsplay.util.VASTLog;
import net.adsplay.vast.VASTPlayer;
import net.adsplay.vast.model.VASTModel;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
 
public class AdsPlayVastRequest extends AsyncTask<Activity, Void, String> {
	private final static String TAG = AdsPlayVastRequest.class.getName();
	Activity activity;
	String url;
	VASTPlayer newPlayer;	
	String vastXML = "<?xml version=\"1.0\" encoding=\"utf-8\" ?><VAST version=\"2.0\"></VAST>";
	
	@Override
	protected String doInBackground(Activity... params) {		
		this.activity = params[0];
		String uuid = "";
		try {
			uuid = 	Installation.id(activity.getApplicationContext());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//this.url = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dlinear&correlator=";
//		this.url = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar%3Dpremidpostpod&cmsid=496&vid=short_onecue&correlator=";
		this.url = AdsPlayVastUtil.getVastUrl(uuid, AdsPlayVastUtil.FPT_PLAY_VOD_ANDROID_APP);
		VASTLog.i(TAG,  "VastURL: " + url);		
		return HttpTools.get(url);
	}
	
	
	protected void onPostExecute(String vastXMLContent)
	{    	
    	  Log.i(TAG, "onPostExecute.loadVideoWithData " + vastXMLContent.length());
    	  newPlayer = new VASTPlayer(activity,
    			  new VASTPlayer.VASTPlayerListener() {	
    		  
					@Override
					public void vastReady(List<VASTModel> models) {
						Log.d(TAG,"VAST Document is ready and we can play it now");
						
						if(models.size()>0){
							final VASTModel model = models.get(0);
							if(model != null){
								VASTLog.d("VASTModel",  "getImpressions: " + model.getImpressions().size());
								VASTLog.d("VASTModel",  "getTimeOffset: " + model.getTimeOffset());
								VASTLog.d("VASTModel",  "getPickedMediaFileURL: " + model.getPickedMediaFileURL());
								
								try {
									Date date = new SimpleDateFormat("HH:mm:ss").parse(model.getTimeOffset());
									newPlayer.play(model);
									VASTLog.d("VASTModel",  "date: " + date);
								} catch (Exception e) {					
								}
								
							} else {
								VASTLog.e(TAG, "model is NULL");
							}
						}						
					}	
					
					@Override
					public void vastError(int error) {
					    String message = "Unable to play VAST Document: Error: " + error;
					    VASTLog.e(TAG,  message);
					}					
					@Override
					public void vastClick() {
					    VASTLog.e(TAG, "VAST click event fired");
					}
					
					@Override
					public void vastComplete() {
						VASTLog.e(TAG, "VAST complete event fired");
						
					}
					
					@Override
					public void vastDismiss() {
						VASTLog.e(TAG, "VAST dismiss event fired");
					}
    	  });
    	  
    	  newPlayer.loadVideoWithData(vastXMLContent);
	}	
}
